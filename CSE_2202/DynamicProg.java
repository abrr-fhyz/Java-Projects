import java.util.*;

class Fibonacci{
	int maxLimit = 100;
	int[] memory;

	Fibonacci(){
		memory = new int[maxLimit];
		Arrays.fill(memory, -1);
		memory[0] = 1;
		memory[1] = 1;
	}

	void memo(int n){
		System.out.println(memoization(n-1));	
	}

	private int memoization(int idx){
		if(memory[idx] == -1)
			memory[idx] = memoization(idx-1) + memoization(idx-2);
		return memory[idx];
	}

	void tabulation(int idx){
		for(int i=2; i<idx; i++)
			memory[i] = memory[i-1] + memory[i-2];
		System.out.println(memory[idx-1]);
	}

}

class Knapsack{
	int[] weights;
	int[] values;
	int cap = 0;

	Knapsack(int[] weights, int[] values, int capacity){
		this.weights = weights;
		this.values = values;
		this.cap = capacity;
	}

	void finalProfit(){
		System.out.println(solve(weights.length, cap));
	}

	private int solve(int idx, int currCap){
		if(currCap == 0 || idx == 0)
			return 0;
		if(weights[idx-1] > currCap)
			return solve(idx - 1, currCap);

        return Math.max(solve(idx - 1, currCap), values[idx-1] + solve(idx - 1, currCap - weights[idx-1]));    
	}

}

class RockClimb{
	int[][] wall;
	int[][] memory;
	int[][] path;
	int rows = 0;
	int cols = 0;

	RockClimb(int[][] wall){
		this.wall = wall;
		rows = wall.length;
		cols = wall[0].length;
		memory = new int[rows][cols];
		path = new int[rows][cols];
	}

	void findPath() {
        for (int idx = 0; idx < cols; idx++) {
            memory[0][idx] = wall[0][idx];
            path[0][idx] = -1;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int maxPrev = memory[i - 1][j];
                int prevCol = j;

                if (j > 0 && memory[i - 1][j - 1] > maxPrev) {
                    maxPrev = memory[i - 1][j - 1];
                    prevCol = j - 1;
                }

                if (j < cols - 1 && memory[i - 1][j + 1] > maxPrev) {
                    maxPrev = memory[i - 1][j + 1];
                    prevCol = j + 1;
                }

                memory[i][j] = wall[i][j] + maxPrev;
                path[i][j] = prevCol;
            }
        }

        for(int j=0; j<rows; j++){
        	for (int i = 0; i < cols; i++)
            	System.out.print(memory[j][i] + " ");
        	System.out.println();
        }
        printResult();
    }

    private void printResult(){
    	int maxPath = 0;
        int maxCol = 0;
        for (int i = 0; i < cols; i++) {
            if (memory[rows - 1][i] > maxPath) {
                maxPath = memory[rows - 1][i];
                maxCol = i;
            }
        }
    	System.out.println("\n" + maxPath + "\nPath:");
        int[] resultPath = new int[rows];
        for (int i = rows - 1; i >= 0; i--) {
            resultPath[i] = maxCol;
            maxCol = path[i][maxCol];
        }
        for (int i = 0; i < rows; i++)
            System.out.print(wall[i][resultPath[i]] + (i == rows - 1 ? "" : " -> "));
        System.out.println();
    }
}

class LCS {
	String firstString;
	String secondString;
	String subString = "";
	int[][] memory;

	LCS(String s1, String s2){
		this.firstString = s1;
		this.secondString = s2;
		memory = new int[firstString.length() + 1][secondString.length() + 1];
	}

	void findLCS(){
		int m = firstString.length();
		int n = secondString.length();
		System.out.println(solve(m, n));
		computeSubString();
		System.out.println(subString);
	} 

	private int solve(int idx1, int idx2){
		if(idx1 == 0 || idx2 == 0)
			return 0;

		if(firstString.charAt(idx1-1) == secondString.charAt(idx2-1)){
			memory[idx1][idx2] = 1 + solve(idx1-1, idx2-1);
			return memory[idx1][idx2];
		}

		memory[idx1][idx2] = Math.max(solve(idx1, idx2-1), solve(idx1-1, idx2));
		return memory[idx1][idx2];
	}

	private void computeSubString(){
		this.subString = "";
		int idx1 = firstString.length();
		int idx2 = secondString.length();

		while(idx1>0 && idx2>0){
			if(firstString.charAt(idx1-1) == secondString.charAt(idx2-1)){
				this.subString += firstString.charAt(idx1-1);
				idx1--;
				idx2--;
				continue;
			}
			if(memory[idx1-1][idx2] > memory[idx1][idx2-1]){
				idx1--;
				continue;
			}
			idx2--;
		}
		reverseString();
	}

	private void reverseString(){
		String temp = "";
		for(int idx=subString.length()-1; idx>=0; idx--)
			temp += subString.charAt(idx);
		this.subString = temp;
	}
}

public class DynamicProg {


	public static void main(String [] args){
		Fibonacci fib = new Fibonacci();
		fib.memo(10);
		fib.tabulation(15);

		int profit[] = new int[] { 60, 100, 120 };
        int weight[] = new int[] { 10, 20, 30 };
        int cap = 50;
		Knapsack knap = new Knapsack(weight, profit, cap);
		knap.finalProfit();

		int[][] wall = {
    		{1, 2, 3},
    		{4, 5, 6},
    		{7, 8, 9}
		};
		RockClimb climb = new RockClimb(wall);
		climb.findPath();

		String s1 = "AGGTAB";
		String s2 = "GXTXAYB";
		LCS lcs = new LCS(s1, s2);
		lcs.findLCS();

	}
}