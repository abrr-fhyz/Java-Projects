import java.util.ArrayList;

class Train extends Thread{
	private Station currentLocation;
	private String side;
	private int trainNumber;
	private ArrayList<Passenger> occupants;

	@Override
    public void run() {
        if (side.equals("Left")) {
            runUp();
        } else {
            runDown();
        }
    }

	public Train(String side, int num){
		this.side = side;
		this.trainNumber = num;
		this.occupants = new ArrayList<>();
	}

	void setLocation(Station firstStation){
		this.currentLocation = firstStation;
	}

	Station getLocation(){
		return this.currentLocation;
	}

	private double wait (int val){
		try {
            Thread.sleep((long)(val * 62.5));
        } catch (InterruptedException e) {}
       	return Watch.getTime();
	}

	void runUp(){
		double time = Watch.getTime();
		System.out.printf("||\t\t\tTrain %c%d starts at %.1fmins\t\t\t||\n", side.charAt(0), trainNumber, time);
		while(currentLocation != null){
			int[] inout = exchangePassengers.boardingTime(currentLocation, this.occupants, this.side);
			System.out.printf("||%c%d\t ---- %24s : %.1fmins\t+%d\t-%d\n", side.charAt(0), trainNumber, currentLocation.getName(), time, inout[1], inout[0]);
			currentLocation = currentLocation.getNextStation();
			if(currentLocation == null)
				break;
			time = wait(8);
		}
		System.out.printf("||\t\t\tTrain %c%d stops at %.1fmins\t\t\t||\n", side.charAt(0), trainNumber, time);
	}

	void runDown(){
		double time = Watch.getTime();
		System.out.printf("||\t\t\tTrain %c%d starts at %.1fmins\t\t\t||\n", side.charAt(0), trainNumber, time);
		while(currentLocation != null){
			int[] inout = exchangePassengers.boardingTime(currentLocation, this.occupants, this.side);
			System.out.printf("||%c%d\t ---- %24s : %.1fmins\t+%d\t-%d\n", side.charAt(0), trainNumber, currentLocation.getName(), time, inout[1], inout[0]);
			currentLocation = currentLocation.getPrevStation();
			if(currentLocation == null)
				break;
			time = wait(10);
		}
		System.out.printf("||\t\t\tTrain %c%d stops at %.1fmins\t\t\t||\n", side.charAt(0), trainNumber, time);
	}
}