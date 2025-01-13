import java.util.*;

public class SumSubset{
	private static int target;
	private static List<Integer> numbers = Arrays.asList(3, 34, 4, 12, 5, 2);

	private static boolean isSumPossible(){
		boolean[][] memory = new boolean[numbers.size() + 1][target + 1];

		for(int i = 0; i < numbers.size() + 1; i++)
			memory[i][0] = true;

		for(int i = 1; i < numbers.size() + 1; i++){
			for(int j = 1; j < target + 1; j++){
				if(numbers.get(i - 1) <= j){
					memory[i][j] = memory[i - 1][j] || memory[i - 1][j - numbers.get(i - 1)];
					continue;
				}
				memory[i][j] = memory[i - 1][j];
			}
		}

		return memory[numbers.size()][target];
	}

	public static void main(String [] args){
		for(int i=0; i<100; i++){
			target = i;
			System.out.println(i + ": " + isSumPossible());
		}
	}
}