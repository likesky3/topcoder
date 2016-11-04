package dp;

import java.util.Arrays;

public class MinimumVariancePartition {

	public static void main(String[] args) {
		MinimumVariancePartition obj = new MinimumVariancePartition();
		int[] mixedSamples = {42,234,10,1,123,545,436,453,74,85,34,999};
//		int[] mixedSamples = {42,234,10,1,123};
		System.out.println(obj.minDev(mixedSamples, 5));
	}
	// dp[i][k]: split mixtedSamples[i...N) into k pieces in the way that sum of variance is minimum
	// reprensent the least sum of variances that can be obtained from the sequence that starts at index i, 
	// splitting into k pieces
	// dp[i][k] = 0, if i >=N 
	// dp[i][k] = inf, if i < N && k == 0
	// dp[i][k] = min(variance(i,j) + dp[j + 1][k-1])
	public double minDev(int[] mixedSamples, int k) {
		Arrays.sort(mixedSamples);
		this.A = mixedSamples;
		int N = mixedSamples.length;
		double[][] dp = new double[N + 1][k + 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		int[][] sum = new int[N][N];
		for (int i = 0; i < N; i++) {
			sum[i][i] = mixedSamples[i];
			for (int j = i + 1; j < N; j++) {
				sum[i][j] = sum[i][j - 1] + mixedSamples[j];
			}
		}
		for (int i = N - 1; i >=0; i--) {
			for (int K = 1; K <= k; K++) {
				for (int j = i; j < N; j++) {
					double mean = 1.0 * sum[i][j] / (j - i + 1);
					double variance = calculateVar(i, j, mean) / (j - i + 1);
					dp[i][K] = Math.min(dp[i][K], variance + dp[j + 1][K - 1]);
				}
			}
		}
		return dp[0][k];
	}
	private int[] A;
	private double calculateVar(int beg, int end, double mean) {
		double res = 0;
		for (int i = beg; i <= end; i++) {
			res += (A[i] - mean) * (A[i] - mean);
		}
		return res;
	}
}
