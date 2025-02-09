import java.util.*;

class Tools{

	void cutRods(int price[]){
		int n = price.length;
		int dp[][] = new int[n+1][n+1];
		
		for(int i=0; i<n+1; i++){
			dp[i][0] = 0;
			dp[0][i] = 0;
		}

		for(int i=1; i<n+1; i++){
			for(int j=1; j<n+1; j++){
				if(j >= i)
					dp[i][j] = Math.max(dp[i-1][j], price[i-1] + dp[i][j-i]);
				else
					dp[i][j] = dp[i-1][j];

			}
		}

		System.out.println("Maximum Profit: " + dp[n][n]);
	}

	void rockClimb(int wall[][]){
		int rows = wall.length;
		int cols = wall[0].length;
		int dp[][] = new int[rows][cols];

		for(int i=0; i<cols; i++)
			dp[0][i] = wall[0][i];

		for(int i=1; i<rows; i++){
			for(int j=0; j<cols; j++){
				int prev = dp[i-1][j];

				if(j > 0)
					prev = Math.max(prev, dp[i-1][j-1]);
				if(j < cols-1)
					prev = Math.max(prev, dp[i-1][j+1]);

				dp[i][j] = wall[i][j] + prev;
			}
		}

		int y = rows-1;
		int xVal = Integer.MIN_VALUE;
		int x = 0;
		for(int i=0; i<cols; i++){
			if(xVal < dp[y][i]){
				xVal = dp[y][i];
				x = i;
			}
		}
		getPath(y, x, dp, wall);
	}

	private void getPath(int i, int j, int[][] dp, int[][] wall){
		if(i == 0)
			return;
		if(j == wall[0].length-1){
			if(dp[i-1][j] >= dp[i-1][j-1]) getPath(i-1, j, dp, wall);
			else getPath(i-1, j-1, dp, wall);
		}
		else if (j == 0){
			if(dp[i-1][j] >= dp[i-1][j+1]) getPath(i-1, j, dp, wall);
       		else getPath(i-1, j+1, dp, wall);
		}
		else{
			if(dp[i-1][j] >= dp[i-1][j-1] && dp[i-1][j] >= dp[i-1][j+1]) getPath(i-1, j, dp, wall);
			else if(dp[i-1][j-1] >= dp[i-1][j] && dp[i-1][j-1] >= dp[i-1][j+1]) getPath(i-1, j-1, dp, wall);
			else getPath(i-1, j+1, dp, wall);
		}

		System.out.println("(" + i + ", " + j + "): " + wall[i][j]);
	}

	void countChange(int coins[], int target){
		int[] dp = new int[target+1];
		dp[0] = 1;

		for(int coin : coins){
			for(int j=coin; j<target+1; j++){
				dp[j] += dp[j-coin];
			}
		}

		System.out.println("Number of Ways: " + dp[target]);
	}

	void findLCS(String s1, String s2){
		int n = s1.length();
		int m = s2.length();

		int dp[][] = new int[n+1][m+1];

		for(int i=0; i<n+1; i++){
			for(int j=0; j<m+1; j++){
				if(i == 0 || j == 0){
					dp[i][j] = 0;
					continue;
				}

				if(s1.charAt(i-1) == s2.charAt(j-1))
					dp[i][j] = 1 + dp[i-1][j-1];
				else
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}

		System.out.println("Longest Common Subsequence " + dp[n][m]);
		getLCS(dp, s1, s2);
	}

	private void getLCS(int[][] dp, String s1, String s2){
		String lcs = "";
		String temp = "";
		int i = s1.length();
		int j = s2.length();

		while(i > 0 && j > 0){
			if(s1.charAt(i-1) == s2.charAt(j-1)){
				temp += s1.charAt(i-1);
				i--;
				j--;
			}
			else if(dp[i-1][j] > dp[i][j-1]) i--;
			else j--;
		}
		for(int c=temp.length()-1; c>=0; c--)
			lcs += temp.charAt(c);
		System.out.println("LCS: " + lcs);
	}

	void knapsack(int[] weights, int[] profit, int capacity){
		int[][] dp = new int[weights.length+1][capacity+1];
		for(int i = 0; i <= weights.length; i++) 
        	dp[i][0] = 0;
    	for(int j = 0; j <= capacity; j++)
        	dp[0][j] = 0;

		for(int i=1; i<weights.length+1; i++){
			for(int j=1; j<capacity+1; j++){
				if(j >= weights[i-1])
					dp[i][j] = Math.max(dp[i-1][j], profit[i-1] + dp[i][j-weights[i-1]]);
				else
					dp[i][j] = dp[i-1][j];
			}
		}

		System.out.println("Maximum Profit: " + dp[weights.length][capacity]);
		getItems(dp, weights, profit, capacity);
	}

	private void getItems(int[][] dp, int[] weights, int[] profit, int capacity){
		int i = weights.length;
		int j = capacity;

		System.out.println("Items in knapsack: ");
		while(i > 0 && j > 0){
			if(dp[i][j] != dp[i-1][j]){
				System.out.println("item: " + i + ", weight: " + weights[i-1] + ", profit: " + profit[i-1]);
				j -= weights[i-1];
			}
			i--;
		}
	}

	void subSetSum(int[] nums, int target){
		boolean[][] dp = new boolean[nums.length+1][target+1];
		
		for(int i = 0; i <= nums.length; i++) 
        	dp[i][0] = true;
    
    	for(int j = 1; j <= target; j++)
        	dp[0][j] = false;

		for(int i=1; i<nums.length+1; i++){
			for(int j=0; j<target+1; j++){
				if(j >= nums[i-1])
					dp[i][j] = (dp[i-1][j] || dp[i-1][j-nums[i-1]]);
				else
					dp[i][j] = dp[i-1][j];
			}
		}

		System.out.println("Is Sum Possible: " + dp[nums.length][target]);
		getSubset(dp, nums, target);
	}

	private void getSubset(boolean[][] dp, int[] nums, int target){
		if(dp[nums.length][target]) {
    		int i = nums.length;
    		int j = target;
    		System.out.print("Subset elements: ");
    		while(i > 0 && j > 0) {
        		if(dp[i][j] != dp[i-1][j]) {
            		System.out.print(nums[i-1] + " ");
            		j -= nums[i-1];
        		}
        		i--;
    		}
    		System.out.println();
		}
	}

	//Kaldane's Algorithm (with dp)
	void maxSumInterval(int[] nums){
		int[] dp = new int[nums.length]; 
		dp[0]= nums[0];
		int globalMaxSum = nums[0];

		for(int i=1; i<nums.length; i++){
			dp[i] = Math.max(dp[i] + nums[i], nums[i]);
			globalMaxSum = Math.max(globalMaxSum, dp[i]);
		}

		System.out.println("Maximum Sum Interval: " + globalMaxSum);
	}

	void tripleLCS(String s1, String s2, String s3){
		int n = s1.length();
		int m = s2.length();
		int o = s3.length();

		int dp[][][] = new int[n+1][m+1][o+1];

		for(int i=0; i<=n; i++){
			for(int j=0; j<=m; j++){
				for(int k=0; k<=o; k++){
					if( i == 0 || j == 0 || k == 0){
						dp[i][j][k] = 0;
						continue;
					}

					if(s1.charAt(i-1) == s2.charAt(j-1) && s1.charAt(i-1) == s3.charAt(k-1))
						dp[i][j][k] = 1 + dp[i-1][j-1][k-1];
					else
						dp[i][j][k] = Math.max(Math.max(dp[i-1][j][k], dp[i][j-1][k]), dp[i][j][k-1]);
				}  
			}
		}	

		System.out.println(dp[n][m][o]);
		getTripleString(dp, s1, s2);
	}

	private void getTripleString(int dp[][][], String s1, String s2, String s3){
		String lcs = "";
		String temp = "";
		int i = s1.length();
		int j = s2.length();
		int k = s3.length();

		while( i>0 && j>0 && k>0){
			if(s1.charAt(i-1) == s2.charAt(j-1) && s1.charAt(i-1) == s3.charAt(k-1)){
				temp += s1.charAt(i-1);
				i--;
				j--;
				k--;
			}
			else if ( dp[i-1][j][k] > dp[i][j-1][k]) i--;
			else if ( dp[i][j-1][k] > dp[i-1][j][k]) j--;
			else k--;
		}

		for(int c=temp.length()-1; c>=0; c--)
			lcs += temp.charAt(c);

		System.out.println(lcs);
	}
}

public class PracticeDP{
	public static void main(String[] args) {
		
	}
}