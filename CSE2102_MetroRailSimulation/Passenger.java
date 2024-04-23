class Passenger{
	private Station currentStation;
	private Station destinationStation;

	Passenger(Station current, Station destination){
		this.currentStation = current;
		this.destinationStation = destination; 
	}

	Station getDestination(){
		return this.destinationStation;
	}
}
