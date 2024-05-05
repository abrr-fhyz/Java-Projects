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

	void findServers(Task[] listOfTasks, int noOfTasks){
		boolean allTasksCompleted = false;
		while(!allTasksCompleted){
			int tasksCompleted = 0;
			for(Task task : listOfTasks){
				if(task != null && !task.executionStatus()){
					findServerForTask(task);
				}
				if(task != null && task.executionStatus()){
					tasksCompleted += 1;
				}
			}
			if(tasksCompleted == noOfTasks){
				allTasksCompleted = true;
				break;
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

	private void findServerForTask(Task task){
		if(task.getBandwith() > maxBandwith){
			System.out.println("|| EXCEPTION: maxBandwith EXCEEDED for \ttaskID:" + task.getName());
			task.isExecuted();
			return;
		}

		for(Server server : listOfServers){
			if(server != null){
				if(server.isAvailable() && server.isValidTask(task)){
					server.setTask(task);
					System.out.println("|| Server: " + server.getServerName() + "\t executing \ttaskID:" + task.getName() + "\t||");
					return;
				}
			}
		}
	}

	private void shutdownNetwork(){
		for(Server server : listOfServers){
			if(server != null)
				server.shutdown();
		}
	}
}
