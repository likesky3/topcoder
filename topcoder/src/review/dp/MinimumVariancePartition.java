package review.dp;

import java.util.Arrays;

public class MinimumVariancePartition {

	public static void main(String[] args) {
		MinimumVariancePartition obj = new MinimumVariancePartition();
		int[] mixedSamples = {1, 3, 4, 4};
		int k = 3;
		System.out.println(obj.minDev(mixedSamples, k));
	}
	
	public double minDev(int[] mixedSamples, int k) {
		Arrays.sort(mixedSamples);
		this.mixedSamples = mixedSamples;
		this.N = mixedSamples.length;
		this.k = k;
		dp = new double[N][k + 1];
		for (double[] a1 : dp) {
			Arrays.fill(a1, -1);
		}
		return recur(0, 1);
	}
	
	private int[] mixedSamples;
	private int N;
	private int k;
	private double[][] dp;
	private double recur(int curr, int i) {
		if (dp[curr][i] != -1)
			return dp[curr][i];
		if (i == k)
			return calcVar(curr, N - 1);
		double res = Long.MAX_VALUE;
		for (int j = curr; j < N - (k - i); j++) {
			res = Math.min(res, calcVar(curr, j) + recur(j + 1, i + 1));
		}
		dp[curr][i] = res;
		return res;
	}
	
	private double calcVar(int from, int to) {
		int sum = 0;
		for (int i = from; i <= to; i++) {
			sum += mixedSamples[i];
		}
		double mean = sum * 1.0 / (to - from + 1);
		double variance = 0;
		for (int i = from; i <= to; i++) {
			variance += (mixedSamples[i] - mean) * (mixedSamples[i] - mean);
		}
		variance /= (to - from + 1);
		return variance;
	}
}
