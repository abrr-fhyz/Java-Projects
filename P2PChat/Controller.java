import java.util.*;

public class Controller{

    private static void talk(Scanner sc, Peer peer){
        new Thread(() -> {
            while(true){
                String statement = sc.nextLine();
                peer.broadcast(statement); 
            }
        }).start();
    }

    private static void simulateConvo() {
        Random random = new Random();
        String ip = "127.0.0.1"; 
        String[] name1 = {"Alex", "Jordan", "Taylor", "Casey", "Riley"};
        String[] name2 = {"Smith", "Johnson", "Williams", "Brown", "Davis"};
        String[] messages = {
            "Hello world!", 
            "Hi there!", 
            "Hey, how's it going?", 
            "Greetings from my peer!", 
            "Good to see you online!",
            "How are you?", 
            "What's new?", 
            "How's the network today?", 
            "Can you hear me?", 
            "Got any interesting data?",
            "This is a test message.", 
            "P2P networking is fun!", 
            "Decentralized systems rule!", 
            "IPFS is the future.", 
            "Blockchain could use this.",
            "Latency seems low today.", 
            "Routing works perfectly!", 
            "Encrypt your messages!", 
            "Have you tried Tor?", 
            "Lets optimize bandwidth.",
            "Nice weather today!", 
            "Coffee break time!", 
            "Working on a new project.", 
            "Just upgraded my node.", 
            "Learning Java sockets.",
            "Debugging is life.", 
            "Need more RAM for this...", 
            "Git commit -am 'fix'", 
            "Compiling...", 
            "Stack Overflow saves me.",
            "Why do Java devs wear glasses? Because they can't C#!", 
            "Debugging: Removing the needles from the haystack.", 
            "I would tell you a UDP joke, but you might not get it.",
            "My code never has bugs—just undocumented features!", 
            "sudo make me a sandwich",
            "ALERT: High CPU usage!", 
            "Need help with NAT traversal!", 
            "DDoS incoming?", 
            "Backup your data NOW!", 
            "Security patch available!",
            "Goodbye!", 
            "Signing off.", 
            "Catch you later!", 
            "Peace out!", 
            "Shutting down my node.",
            "Leaving the network.", 
            "Be back in 5 mins.", 
            "Time to sleep.", 
            "Over and out.", 
            "EOF"
        };

        for (int i = 1; i < 20; i++) {
            final int port = 20000 + i; 
            new Thread(() -> {
                try {
                    String peerName = name1[random.nextInt(name1.length)] + name2[random.nextInt(name2.length)];
                    Peer peer = new Peer(peerName, ip, port);
                    peer.init();
                    for (int j = 0; j < 5; j++) {
                        Thread.sleep(700); 
                        peer.broadcast(messages[random.nextInt(messages.length)]);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    private static void manualConvo(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Initializing Peer... \n");
        System.out.print("Enter Peer Name: ");
        String peerName = sc.nextLine();
        System.out.print("Enter Peer IP: ");
        String ip = sc.nextLine();
        System.out.print("Enter Peer Port: ");
        int port = sc.nextInt();
        sc.nextLine();

        Peer peer = new Peer(peerName, ip, port);
        peer.init();
        talk(sc, peer);
    }
    public static void main(String [] args){
        manualConvo();
        //simulateConvo();
    }
}
