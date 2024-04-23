import java.util.Random;

abstract class Initializer{
	protected static String[] listStationsMRT6 = {"Uttara North", "Uttara Center", "Uttara South", "Pallabi", "Mirpur 11", "Mirpur 10", "Kazipara", "Shewrapara", "Agargaon", "Bijoy Sharani", "Farmgate", "Kawran Bazaar", "Shahbagh", "Dhaka University", "Secretariat", "Motijheel", "Kamlapur"};
	protected static String[] listStationsMRT1 = {"Hemayetpur", "Baliapur", "Bilamalia", "Amin Bazar", "Gabtoli", "DarusSalam", "Mirpur 1", "Mirpur 10", "Mirpur 14", "Kochukhet", "Banani", "Gulshan 2", "NotunBazaar", "Vatara"};
	private static int mrtLine = 0;

	static void setUpMRT6(MRTLine mrt6){
		mrtLine = 6;
		for(String station : listStationsMRT6){
			mrt6.addStation(station);
		}
	}

	static void setUpMRT1(MRTLine mrt1){
		mrtLine = 1;
		for(String station : listStationsMRT1){
			mrt1.addStation(station);
		}
	}

	static void generateCommuters(int commuters, MRTLine mrt){
		System.out.println("Generating Commuters....");
		int numOfStation = 0;
		if(mrtLine == 1) numOfStation = listStationsMRT1.length;
		if(mrtLine == 6) numOfStation = listStationsMRT6.length;
		Station start = mrt.getFirstStation();
		Random rd = new Random();
		for(int i=0; i<commuters; i++){
			int oldStation = rd.nextInt(numOfStation);
			int newStation = rd.nextInt(numOfStation);
			Station oldS = start;
			Station newS = start;
			while(oldStation -- > 0){
				oldS = oldS.getNextStation();
			}
			while(newStation -- > 0){
				newS = newS.getNextStation();
			}
			Passenger commuter = new Passenger(oldS, newS);
			oldS.addCommuter(commuter);
		}
		System.out.println(commuters + " Commuters Generated!!");
	}
}