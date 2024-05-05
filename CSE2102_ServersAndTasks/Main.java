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

		int noOfTasks = cin.nextInt();
		Task[] listOfTasks = new Task[noOfTasks];
		for(int idx = 0; idx < noOfTasks; idx++){
			int taskID = cin.nextInt();
			int taskBandwith = cin.nextInt();
			listOfTasks[idx] = new Task(taskID, taskBandwith);
		}

		network.initializeNetwork();
		network.findServers(listOfTasks, noOfTasks);
		network.waitForCompletion();
	} 
}
