import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Peer {
    private String peerIP;
    private String peerName;
    private int peerPort;
    private Map<String, Socket> peerList = new ConcurrentHashMap<>();
    private ServerSocket ssocket;
    private boolean inOperation;
    private int[] portRange = {20000, 20020};

    Peer(String name, String ip, int port) {
        this.peerName = name;
        this.peerIP = ip;
        this.peerPort = port;
        this.peerList = new ConcurrentHashMap<>();
        this.inOperation = true;
    }

    public void init() {
        startServer();
        System.out.println(peerName + " is ready at " + peerIP + ":" + peerPort);
        startSearching();
    }

    public void broadcast(String message) {
        Collection<Socket> peerListCopy = new ArrayList<>(peerList.values());
        boolean displayed = false;
        for (Socket peerSocket : peerListCopy) {
            if (peerSocket != null && !peerSocket.isClosed() && peerSocket.getPort() >= this.portRange[0] && peerSocket.getPort() <= this.portRange[1] ) {
                try {
                    DataOutputStream out = new DataOutputStream(peerSocket.getOutputStream());
                    if(!displayed){
                        System.out.println(this.peerName + " [You]: " + message);
                        displayed = true;
                    }
                    out.writeUTF(message);
                    out.flush();
                } catch (IOException e) {
                    removeConnection(peerSocket);
                }
            }
        }
    }

    private void startServer() {
        new Thread(() -> {
            try {
                ssocket = new ServerSocket(peerPort);
                while(inOperation) {
                    try {
                        Socket peerSocket = ssocket.accept();
                        handleIncomingPeer(peerSocket);
                    } catch (IOException e) {
                        if(inOperation) {
                            System.out.println("Could not accept message.");
                        }
                    }
                }
            } catch(IOException e) {
                System.out.println("Error initializing Peer.");
            }
        }).start();
    }

    private void startSearching() {
        new Thread(() -> {
            while(inOperation) {
                try {
                    for(int port = portRange[0]; port <= portRange[1]; port++) {
                        if(port != peerPort)
                            attemptConnection(port);
                    }
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    private void attemptConnection(int port) {
        try (Socket testSocket = new Socket()) {
            testSocket.connect(new InetSocketAddress(peerIP, port), 25);
            if(!isAlreadyConnected(this.peerIP, port)) {
                establishPeerConnection(port);
            }
        } catch (IOException e) {}
    }

    private void establishPeerConnection(int port) {
        new Thread(() -> {
            try {
                Socket peerSocket = new Socket(peerIP, port);
                DataOutputStream out = new DataOutputStream(peerSocket.getOutputStream());
                DataInputStream in = new DataInputStream(peerSocket.getInputStream());
                
                out.writeUTF(peerName);
                out.flush();
                
                String remotePeer = in.readUTF();
                out.writeUTF("ACK");
                out.flush();

                addConnection(peerSocket);
                
                System.out.println("Discovered peer: " + remotePeer + " on port " + port);
                monitorPeerConnection(peerSocket, remotePeer);
            } catch (IOException e) {}
        }).start();
    }   

    private void handleIncomingPeer(Socket peerSocket) {
        try {
            DataInputStream in = new DataInputStream(peerSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(peerSocket.getOutputStream());
            String remotePeer = in.readUTF();
            out.writeUTF(peerName);
            out.flush();
            String remoteIp = peerSocket.getInetAddress().getHostAddress();
            int remotePort = peerSocket.getPort();
            String ack = in.readUTF();
            if(ack.equals("ACK")) {
                if(!isAlreadyConnected(remoteIp, remotePort)){
                    addConnection(peerSocket);
                    System.out.println("Accepted connection from: " + remotePeer);
                    monitorPeerConnection(peerSocket, remotePeer);
                }
            }
        } catch (IOException e) {}
    }

    private void monitorPeerConnection(Socket socket, String peerName) {
        new Thread(() -> {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                while(inOperation) {
                    String message = in.readUTF();
                    System.out.println(peerName + " : " + message);
                }
            } catch (IOException e) {
                System.err.println("Connection Terminated with " + peerName);
                removeConnection(socket);
            }
        }).start();
    }

    private String getHashValue(String ip, int port) {
        return ip + ":" + port;
    }

    private boolean isAlreadyConnected(String ip, int port) {
        return peerList.containsKey(getHashValue(ip, port));
    }

    private void addConnection(Socket socket) {
        String key = getHashValue(socket.getInetAddress().getHostAddress(), socket.getPort());
        peerList.putIfAbsent(key, socket);
    }

    private void removeConnection(Socket socket) {
        String key = getHashValue(socket.getInetAddress().getHostAddress(), socket.getPort());
        peerList.remove(key);
    }

}
