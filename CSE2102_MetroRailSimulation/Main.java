import java.util.Scanner;


public class Main{
	public static void main(String [] args){
		MRTLine metroRail = new MRTLine();
		Scanner sc = new Scanner(System.in);
		String railNumber = sc.nextLine();
		if(railNumber.equals("mrt1")){
			Initializer.setUpMRT1(metroRail);
		}
		if(railNumber.equals("mrt6")){
			Initializer.setUpMRT6(metroRail);
		}
		int num_of_trains = sc.nextInt();
		Initializer.generateCommuters(num_of_trains * 2500, metroRail);
		metroRail.runTrains(num_of_trains);
	}
}