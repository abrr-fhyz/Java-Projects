import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class exchangePassengers{

	static int[] boardingTime(Station station, ArrayList<Passenger> trainOccupants, String side) {
		int[] inout = new int[2];
		inout[0] = 0; inout[1] = 0;
    	ArrayList<Passenger> stationOccupants = station.getCommuterList();
    	// Unload passengers from the train
    	Iterator<Passenger> iterator = trainOccupants.iterator();
    	while (iterator.hasNext()) {
        	Passenger person = iterator.next();
        	if (person.getDestination() == station) {
            	iterator.remove(); 
            	inout[0] ++;
        	}
    	}
    
    	// Load passengers onto the train
    	int peopleInside = trainOccupants.size();
    	for (Iterator<Passenger> it = stationOccupants.iterator(); it.hasNext() && peopleInside < 400;) {
        	Passenger person = it.next();
        	if (gettingOnTrain(person.getDestination(), station, side)) {
            	trainOccupants.add(person);
            	peopleInside++;
            	it.remove();
            	inout[1] ++;
        	}
    	}

    	return inout;
	}


	private static boolean gettingOnTrain(Station destination, Station current, String trainLine){
		if(trainLine.equals("Left")){
			Station temp = current;
			while(temp != null){
				if(destination == temp){
					return true;
				}
				temp = temp.getNextStation();
			}
		}
		else{
			Station temp = current;
			while(temp != null){
				if(destination == temp){
					return true;
				}
				temp = temp.getPrevStation();
			}
		}
		return false;
	}

}