class Network{
	private Server[] listOfServers = new Server[101];
	private int noOfServers = 0;
	private int maxBandwith = 0;

	void addServer(Server server){
		listOfServers[noOfServers] = server;
		noOfServers += 1;
		if(maxBandwith < server.getBandwith()){
			maxBandwith = server.getBandwith();
		}
	}

	void findValidServer(Task task){
		boolean serverFound = false;
		if(task.getBandwith() > maxBandwith){
			System.out.println("|| No Server available: EXCEPTION:- MaxBandWidth exceeded");
			return;
		}
		while(!serverFound){
			for(Server server : listOfServers){
				if(server != null){
					if(server.isAvailable() && server.isValidTask(task)){
						server.setTask(task);
						serverFound = true;
						System.out.println("|| Server: " + server.getServerName() + "\t executing taskID:" + task.getName() + "\t\t||");
						break;
					}
				}
			}
		}
	}

	void initializeNetwork(){
		for(Server server : listOfServers){
			if(server != null)
				server.start();
		}
	}

	void waitForCompletion(){
		boolean allServersFree = false;
		while(!allServersFree){
			int freeServer = 0;
			int allServers = 0;
			for(Server server : listOfServers){
				if(server != null){
					allServers += 1;
					if(server.isAvailable()){
						freeServer += 1;
					}
				}
			}
			if(freeServer == allServers){
				allServersFree = true;
				break;
			}
		}
		shutdownNetwork();
	}

	private void shutdownNetwork(){
		for(Server server : listOfServers){
			if(server != null)
				server.shutdown();
		}
	}
}
