class Task{
	private int taskID;
	private int taskBandwith;
	private boolean isExecuted = false;

	Task(int taskID, int taskBandwith){
		this.taskID = taskID;
		this.taskBandwith = taskBandwith;
	}

	void startStatus(){
		System.out.println("|| taskID:" + taskID + "\t is being processed\t\t||");
	}

	void finishStatus(){
		System.out.println("|| taskID:" + taskID + "\t successfully executed\t\t||");
	}

	void isExecuted(){
		this.isExecuted = true;
	}

	int getName(){
		return this.taskID;
	}

	int getBandwith(){
		return taskBandwith;
	}

	boolean executionStatus(){
		return this.isExecuted;
	}

}
