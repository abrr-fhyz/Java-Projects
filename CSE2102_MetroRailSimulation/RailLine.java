class RailLine{
	private Train[] listTrains;
	private Station startingLocation;
	public RailLine(String side){
		listTrains = new Train[10];
		for(int i=0; i<10; i++){
			listTrains[i] = new Train(side, i+1);
		}
	}

	void setStartingLocation(Station station){
		for(Train train: listTrains){
			train.setLocation(station);
		}
		this.startingLocation = station;
	}

	void runTrainsOnLine(){
		int trainNumber = 0;
		while(this.listTrains[trainNumber].getLocation() != this.startingLocation){
			trainNumber += 1;
		}
		this.listTrains[trainNumber].start();
	}
}