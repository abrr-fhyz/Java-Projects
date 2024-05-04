class Task{
	private int taskID;
	private int taskBandwith;

	Task(int taskID, int taskBandwith){
		this.taskID = taskID;
		this.taskBandwith = taskBandwith;
	}

	int getBandwith(){
		return taskBandwith;
	}

	void startStatus(){
		System.out.println("|| taskID:" + taskID + "\t is being processed\t\t||");
	}

	void finishStatus(){
		System.out.println("|| taskID:" + taskID + "\t successfully executed\t\t||");
	}

	int getName(){
		return this.taskID;
	}

}
