import java.util.Scanner;
import java.util.ArrayList;










class ArmyGroup{
	private int armyGroupStrength;
	private ArrayList<Army> groupOfArmies;

	ArmyGroup(int soldiers) {
		this.armyGroupStrength = soldiers;
		groupOfArmies = new ArrayList<>();
		divideArmy();
	}

	void divideArmy() {
		while(this.armyGroupStrength >= 35000) {
			Army thisArmy = new Army(35000);
			this.groupOfArmies.add(thisArmy);
			this.armyGroupStrength -= 35000;
		}
		Army thisArmy = new Army(armyGroupStrength);
		this.groupOfArmies.add(thisArmy);
	}

	Army getLargestArmy() {
		Army blankArmy = new Army(0);

		for(Army thisArmy: groupOfArmies){
			if(!thisArmy.defeated()){
				if(blankArmy.getTroops() < thisArmy.getTroops()) {
					blankArmy = thisArmy;
				}
			}
		}
		return blankArmy;
	}

}










class Army{
	private int armyStrength;
	private boolean isDefeated = false;

    Army(int soldiers) {
    	this.armyStrength = soldiers;
    }

    int getTroops(){
    	return this.armyStrength;
    }

    void sustainCasualties(int dead){
    	this.armyStrength -= dead;
    }

    boolean defeated(){
    	return this.isDefeated;
    }

    void surrender(){
    	this.isDefeated = true;
    }
}










class Battlefield{
	private static boolean warIsOver = false;
	private static boolean warIsWon = false;
	private static char allegiance = ' ';

	static void beginWar(Army napoleon, ArmyGroup russian, ArmyGroup germanic, ArmyGroup english) {
		while(!warIsOver){
			Army finalOpponent = findLargestArmy(russian, germanic, english);
			if(warIsOver){
				break;
			}
			engageCombat(napoleon, finalOpponent);
			finalOpponent.surrender();
		}
	}

	static Army findLargestArmy(ArmyGroup russian, ArmyGroup germanic, ArmyGroup english) {
		Army rus = russian.getLargestArmy();
		Army aus = germanic.getLargestArmy();
		Army eng = english.getLargestArmy();
		Army finalOpponent = getLargestArmy(rus, aus, eng);
		return finalOpponent;
	}

	private static Army getLargestArmy(Army army1, Army army2, Army army3) {
		Army outputArmy = new Army(0);
		if( ( army1.getTroops() >= army2.getTroops() ) && ( army1.getTroops() >= army3.getTroops() ) ){
			allegiance = 'R';
			outputArmy = army1;
		}
		if( ( army2.getTroops() >= army1.getTroops() ) && ( army2.getTroops() >= army3.getTroops() ) ){
			allegiance = 'G';
			outputArmy = army2;
		}
		if( ( army3.getTroops() >= army2.getTroops() ) && ( army3.getTroops() >= army1.getTroops() ) ){
			allegiance = 'E';
			outputArmy = army3;
		}

		if(outputArmy.getTroops() == 0){
			warIsOver = true;
			warIsWon = true;
		}

		return outputArmy;
	}

	static void engageCombat(Army napoleon, Army coalition) {
		int frenchForces = napoleon.getTroops();
		int coalitionForces = coalition.getTroops();
		double ratio = (double) coalitionForces / frenchForces ;
		if(ratio <= 1) {
			Solution.newBattleFought(allegiance, coalitionForces, frenchForces);
			double val = ratio * 500;
			int losses = (int) val;
			napoleon.sustainCasualties(losses);
			return;
		}
		if(ratio > 1.5) {
			warIsOver = true;
			return;
		}
		Solution.newBattleFought(allegiance, coalitionForces, frenchForces);
		double val = ratio * 1500;
		int losses = (int) val;
		napoleon.sustainCasualties(losses);
	}

	static void isWarWon(){
		if(warIsWon){
			System.out.println("Viva la France!");
		}
		else{
			System.out.println("Decisive Coalition Victory.");
		}
	}

}










class BattleRecord{
	private char allegiance;
	private int coalitionForces;
	private int frenchForces;

	BattleRecord(char allegiance, int coalitionForces, int frenchForces) {
		this.frenchForces = frenchForces;
		this.coalitionForces = coalitionForces;
		this.allegiance = allegiance;
	}

	void printBattleData() {
		System.out.println(this.allegiance + " " + this.coalitionForces + " vs " + this.frenchForces);
	}
}










public class Solution{
	static ArrayList<BattleRecord> battlesFought;

	static void newBattleFought(char allegiance, int coalitionForces, int frenchForces){
		BattleRecord battle = new BattleRecord(allegiance, coalitionForces, frenchForces);
		battlesFought.add(battle);
	}

	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		battlesFought = new ArrayList<>();

		int soldiers = sc.nextInt();
		Army imperialFrance = new Army(soldiers);
		soldiers = sc.nextInt();
		ArmyGroup russianEmpire = new ArmyGroup(soldiers);
		soldiers = sc.nextInt();
		ArmyGroup prussiaAustria = new ArmyGroup(soldiers);
		soldiers = sc.nextInt();
		ArmyGroup greatBritain = new ArmyGroup(soldiers);

		Battlefield.beginWar(imperialFrance, russianEmpire, prussiaAustria, greatBritain);
		Battlefield.isWarWon();
		for(BattleRecord battle: battlesFought){
			battle.printBattleData();
		}
	}
}