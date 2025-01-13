import java.util.*;

public class RodCutting{

	private static List<Integer> price = Arrays.asList(1, 5, 8, 9, 10, 17, 17, 20);
	private static int[] memory = new int[price.size() + 1];

	private static int cutRod(int rodSize){
		if(rodSize == 0)
			return 0;
		int maxProfit = Integer.MAX_VALUE * -1;
		for(int i = 0; i < rodSize; i++)
			maxProfit = Math.max(maxProfit, price.get(i) + cutRod(rodSize - i - 1));
		return maxProfit;
	}

	private static int cutRodDp(){
		memory[0] = 0;
		for(int i = 1; i <= price.size(); i++){
			int maxProfit = Integer.MAX_VALUE * -1;
			for(int j = 0; j < i; j++)
				maxProfit = Math.max(maxProfit, price.get(j) + memory[i - j - 1]);
			memory[i] = maxProfit;
		}
		//for(int i=0; i<memory.length; i++)
		//	System.out.println(memory[i]);
		return memory[price.size()];
	}

	public static void main(String [] args){
		System.out.println(cutRod(price.size()));
		System.out.println(cutRodDp());
	}
}