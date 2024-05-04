import java.util.Scanner;

public class Main{

	static Server formatText(String data){
		String[] subparts = data.split(" ");
		int bandwidth = 0;
        try {
            bandwidth = Integer.parseInt(subparts[1]);
        } catch (NumberFormatException e) {}
        Server server = new Server(subparts[0], bandwidth);
        return server;
	}

	public static void main(String [] args){
		Scanner cin = new Scanner(System.in);
		Network network = new Network();
		int noOfServers = cin.nextInt();
		cin.nextLine();
		for(int idx = 0; idx < noOfServers; idx++){
			String data = cin.nextLine();
			Server newServer = formatText(data);
			network.addServer(newServer);
		}
		network.initializeNetwork();
		int noOfTasks = cin.nextInt();
		for(int idx = 0; idx < noOfTasks; idx++){
			int taskID = cin.nextInt();
			int taskBandwith = cin.nextInt();
			Task task = new Task(taskID, taskBandwith);
			network.findValidServer(task);
		}
		network.waitForCompletion();
	} 
}