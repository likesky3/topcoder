package dp;

public class SimplifiedDarts {

	public static void main(String[] args) {
		SimplifiedDarts obj = new SimplifiedDarts();
		System.out.println(obj.tryToWin(1, 5, 50, 50));
	}
	
	public double tryToWin(int W, int N, int P1, int P2) {
		if (N == 0 || W > 3 * N)
			return 0;
		if (W == 0)
			return 1;
		double p1 = P1 / 100.0;
		double p2 = P2 / 100.0;
		double[][] dp = new double[N + 1][W + 1];
		dp[1][1] = Math.max(p1, p2);
		if (W >= 2)
			dp[1][2] = Math.max(p1, p2); 
		for (int n = 0; n <= N; n++) {
			dp[n][0] = 1;
		}
		for (int n = 2; n <= N; n++) {
			if (W >= 2)
				dp[n][2] = Math.max(p1 + (1 - p1) * dp[n-1][2], p2 + (1-p2) * dp[n-1][2]);
			dp[n][1] = Math.max(p1 + (1 - p1) * dp[n-1][1], p2 + (1-p2) * dp[n-1][1]);
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= W; j++) {
				double aShort = p1 * dp[i - 1][j - 2] + (1 - p1) * dp[i - 1][j];
				double aLong = p2 * dp[i - 1][j - 3] + (1 - p2) * dp[i - 1][j]; 
				dp[i][j] = Math.max(aShort, aLong);
			}
		}
		return dp[N][W] * 100;
	}
}
