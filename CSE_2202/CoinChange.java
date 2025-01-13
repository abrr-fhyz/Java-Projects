import java.util.*;

public class CoinChange{
	private static List<Integer> coins = Arrays.asList(1, 2, 3);

	private static int possibleChange(int amount){
		int[] memory = new int[amount + 1];

		memory[0] = 1;

		for(int coinValue : coins){
			for(int j = coinValue; j < amount + 1; j++){
				memory[j] += memory[j - coinValue];
			}
		}

		return memory[amount];
	}

	public static void main(String [] args){
		for(int i=0; i<100; i++){
			System.out.println(i + ": " + possibleChange(i));
		}
	}
}