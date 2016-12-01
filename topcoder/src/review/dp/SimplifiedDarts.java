package review.dp;

import java.util.Arrays;

public class SimplifiedDarts {

	public static void main(String[] args) {
		SimplifiedDarts obj = new SimplifiedDarts();
		System.out.println(obj.tryToWin(5, 2, 50, 25));
		System.out.println(obj.tryToWin2(5, 2, 50, 25));
	}
	
	public double tryToWin(int W, int N, int P1, int P2) {
		memo = new double[W + 1][N + 1];
		for (double[] a1 : memo)
			Arrays.fill(a1, -1);
		p1 = P1 / 100.0;
		p2 = P2 / 100.0;
		return recur(W, N) * 100;
	}
	
	private double[][] memo;
	private double p1, p2;
	private double recur(int w, int n) {
		if (w < 0) 
			return 1;
		if (memo[w][n] != -1)
			return memo[w][n];
		if (n == 0)
			return w <= 0 ? 1 : 0;
		memo[w][n] = Math.max(p1 * recur(w - 2, n - 1) + (1 - p1) * recur(w, n - 1), p2 * recur(w - 3, n - 1) + (1 - p2) * recur(w, n - 1));
		return memo[w][n];
	}
	
	public double tryToWin2(int W, int N, int P1, int P2) {
		double p1 = P1 * 0.01;
		double p2 = P2 * 0.01;
		double[][] dp = new double[W + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			dp[0][i] = 1;
		}
		for (int w = 1; w <= W; w++) {
			for (int n = 1; n <= N; n++) {
				dp[w][n] = p1 * dp[Math.max(w - 2, 0)][n - 1] + (1 - p1) * dp[w][n - 1];
				dp[w][n] = Math.max(dp[w][n],  p2 * dp[Math.max(w - 3, 0)][n - 1] + (1 - p2) * dp[w][n - 1]);
			}
		}
		return dp[W][N] * 100;
	}

}
