package dp;

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
		System.out.println(obj.find(4, 2, 10));
	}
	
}
