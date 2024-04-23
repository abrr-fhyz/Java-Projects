class MRTLine{
	private Station firstStation;
	private Station currentStation;
	private Station lastStation;
	private RailLine leftLine = new RailLine("Left");
	private RailLine rightLine = new RailLine("Right");
	private int numberOfStations = 0;
	private static int numberOfTrainPairs = 0;

	void addStation(String name){
		Station station = new Station(name);
		if(this.firstStation == null){
			this.firstStation = station;
			this.currentStation = station;
			this.lastStation = station;
		}
		else{
			this.currentStation.setNextStation(station);
			station.setPrevStation(this.currentStation);
			this.currentStation = station;
			this.lastStation = station;
		}
		numberOfStations += 1;
	} 

	Station getFirstStation(){
		return this.firstStation;
	}

	void showStations(){
		Station temp = this.firstStation;
		while(temp != null){
			temp.printStation();
			temp = temp.getNextStation();
		}
	}

	void runTrains(int numOfTrainPairs){
		this.numberOfTrainPairs = numOfTrainPairs;
		leftLine.setStartingLocation(firstStation);
		rightLine.setStartingLocation(lastStation);
		Watch.watchSetting(numOfTrainPairs, numberOfStations);
		Watch.startCountingTime();
		for(int i = 0; i < numOfTrainPairs; i++){
			leftLine.runTrainsOnLine();
			rightLine.runTrainsOnLine();
			try {
            	Thread.sleep(600);
        	} catch (InterruptedException e) {}
		}
	}
}