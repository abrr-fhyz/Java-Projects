class Server extends Thread{
	public String serverName;
	private int serverBandwith;
	private boolean isProcessing;
	private boolean serverOperational = true;
	private boolean processTask = false;
	private Task currentTask = null;

	Server(String serverName, int serverBandwith){
		this.serverName = serverName;
		this.serverBandwith = serverBandwith;
		isProcessing = false;
	}

	@Override
    public void run() {
        System.out.println("|| Server: " + serverName + "\t has been initialized\t\t||");
        while (serverOperational) {
            synchronized (this) {
                if (processTask && currentTask != null) {
                    executeTask();
                    processTask = false;
                }
            }
        }
        System.out.println("|| Server: " + serverName + "\t has been terminated\t\t||");
    }

	void executeTask(){
		Task task = this.currentTask;
		this.isProcessing = true;
		int taskBandwith = task.getBandwith();
		try{
			task.startStatus();
			Thread.sleep(taskBandwith);
			task.finishStatus();
		} catch (InterruptedException e){}
		this.isProcessing = false;
	}

	void setTask(Task task){
		this.currentTask = task;
		this.processTask = true;
	}

	void shutdown(){
		this.serverOperational = false;
	}

	boolean isValidTask(Task task){
		int taskBandwith = task.getBandwith();
		if(serverBandwith >= taskBandwith){
			return true;
		}
		return false;
	}

	boolean isAvailable(){
		return !isProcessing;
	}

	int getBandwith(){
		return this.serverBandwith;
	}

	String getServerName(){
		return this.serverName;
	}
}