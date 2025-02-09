import java.util.*;

class Matrix{
	int rows;
	int cols;

	Matrix(int x, int y){
		rows = x;
		cols = y;
	}
}

public class MatrixChain{

	private static String expression = "";

	static void findParenthesis(List<Matrix> matrices){
		int n = matrices.size();
		int p[] = new int[n+1];

		p[0] = matrices.get(0).rows;
        for (int i=0; i<n; i++)
            p[i + 1] = matrices.get(i).cols;


		int dp[][] = new int[n+1][n+1];
		int key[][] = new int[n+1][n+1];

		for(int i=1; i<=n; i++)
			dp[i][i] = 0;

		for(int l=2; l<=n; l++){
			for(int i=1; i<=n-l+1; i++){
				int j = i+l-1;
				dp[i][j] = Integer.MAX_VALUE;
				for(int k=i; k<j; k++){
					//dp[i][j] = Integer.min(dp[i][j], dp[i][k] + dp[k+1][j] + p[i-1]*p[k]*p[j]);
					int cost = dp[i][k] + dp[k+1][j] + p[i-1]*p[k]*p[j];
					if(cost < dp[i][j]){
						dp[i][j] = cost;
						key[i][j] = k;
					}
				}
			}
		}

		System.out.println("Minimum Cost: " + dp[1][n]);
		printMatrix(dp, n);
		getExpression(key, 1, n);
		printExp();
	}

	static void printMatrix(int[][] dp, int n){
		System.out.println("Cost Matrix: ");
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				if(i > j)
					System.out.print("X ");
				else
					System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
	}

	static void getExpression(int[][] key, int i, int j){
		if(i == j){
			expression += "a" + i;
			return;
		}
		else{
			expression += "(";
			getExpression(key, i, key[i][j]);
			getExpression(key, key[i][j]+1, j);
			expression += ")";
		}
	}

	static void printExp(){
		String finalStr = "";
		System.out.print("Required Expression: ");
		for(int i=1; i<expression.length()-1; i++){
			finalStr += expression.charAt(i);
			if(expression.charAt(i) > 47 && expression.charAt(i) < 58 && expression.charAt(i+1) != ')')
				finalStr += " * ";
			if(expression.charAt(i) == ')' && i != expression.length()-2)
				finalStr += " * ";
		}
		System.out.println(finalStr);
	}


	public static void main(String [] args){
		List<Matrix> matrices = new ArrayList<>();
		Matrix a1 = new Matrix(4, 10);
		Matrix a2 = new Matrix(10, 3);
		Matrix a3 = new Matrix(3, 12);
		Matrix a4 = new Matrix(12, 20);
		Matrix a5 = new Matrix(20, 7);
		matrices.add(a1);
		matrices.add(a2);
		matrices.add(a3);
		matrices.add(a4);
		matrices.add(a5);
		findParenthesis(matrices);
	}
}