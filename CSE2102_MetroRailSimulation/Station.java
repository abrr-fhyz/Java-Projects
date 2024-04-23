import java.util.ArrayList;

class Station{
	private String name;
	private Station nextStation = null;
	private Station prevStation = null;
	private ArrayList<Passenger> commuters;

	public Station(String name){
		this.name = name;
		this.commuters = new ArrayList<>();
	}

	void addCommuter(Passenger passenger){
		this.commuters.add(passenger);
	}

	ArrayList<Passenger> getCommuterList(){
		return this.commuters;
	}


	void setPrevStation(Station prevStation){
		this.prevStation = prevStation;
	}
	void setNextStation(Station nextStation){
		this.nextStation = nextStation;
	}
	void printStation(){
		System.out.println(this.name + " Station");
	}
	String getName(){
		String fullName = name + " Station";
		return fullName;
	}
	String getName(int x){
		return this.name;
	}
	Station getNextStation(){
		return this.nextStation;
	}
	Station getPrevStation(){
		return this.prevStation;
	}
}