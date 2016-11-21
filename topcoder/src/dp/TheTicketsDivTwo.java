package dp;

import java.util.Arrays;

public class TheTicketsDivTwo {
	public double find(int N, int M, int K) {
		double[][][] dp = new double[N + 1][N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][1][0] = 1;
		}
		for (int k = 1; k <= K; k++) {
			dp[1][1][k] = 1; // for n = 1
			for (int n = 2; n <= N; n++) {
				dp[n][1][k] = 1.0 / 6 + 0.5 * dp[n][n][k - 1]; // for m = 1
				// for m >= 2
				for (int m = 2; m <= n; m++) { // not q <= m 
					dp[n][m][k] = 0.5 * dp[n][m - 1][k - 1] + (1.0 / 3) * dp[n - 1][m - 1][k - 1];
				}
			}
		}
		return dp[N][M][K];
	}
	
	public static void main(String[] args) {
		TheTicketsDivTwo obj = new TheTicketsDivTwo();
//		System.out.println(obj.find(4,2,10));
//		System.out.println(obj.find2(4,2,10));
		
		System.out.println(obj.find(3, 1, 9));
		System.out.println(obj.find2(3, 1, 9));
		
//		System.out.println(obj.find(4,1, 9));
//		System.out.println(obj.find2(4,1, 9));
	}
	
	private double[][][] memo;
	public double find2(int n, int m, int k) {
		memo = new double[n + 1][n + 1][k + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		
		return recur(n, m, k);
	}
	private double recur(int n, int m, int k) {

		if (k == 0) {
			return m == 1 ? 1 : 0;
		}
		if (n == 1) {
			return 1;
		}
		if (memo[n][m][k] != -1)
			return memo[n][m][k];
		double result = 0;
		if (m == 1) {
			result = 1.0 / 6 + 0.5 * recur(n, n, k - 1);
		} else {
			result = recur(n - 1, m - 1, k - 1) / 3.0 + recur(n, m - 1, k - 1) / 2.0;
		}
		memo[n][m][k] = result;
		//System.out.printf("memo[%d][%d][%d] = %f, result = %f\n", n, m, k, memo[n][m][k], result);
		return result;
	}
	
	public double find3(int n, int m, int k) {
		if (k == 0) {
			return m == 1 ? 1 : 0;
		}
		if (n == 1) {
			return 1;
		}
		if (m == 1) {
			return 1.0 / 6 + 0.5 * find3(n, n, k - 1);
		} 
		
		return 0.5 * find3(n, m - 1, k - 1) + find3(n - 1, m - 1, k - 1) / 3.0; 
		
	}
}
