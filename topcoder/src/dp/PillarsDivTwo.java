package dp;

import java.util.HashMap;
import java.util.Map;

public class PillarsDivTwo {

	public static void main(String[] args) {
		PillarsDivTwo obj = new PillarsDivTwo();
		int[] height = {3, 3, 3};
		System.out.println(obj.maximalLength(height, 2));
	}
	public double maximalLength(int[] height, int w) {
		int N = height.length;
		double[][] dp = new double[N + 1][101];
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j <= height[i - 1]; j++) {
				for (int k = 1; k <= height[i - 2]; k++) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Math.sqrt(w * w + (j - k) * (j - k)));
				}
			}
		}
		double res = 0;
		for (int i = 1; i <= height[N - 1]; i++) {
			res = Math.max(res, dp[N][i]);
		}
		return res;
	}
	
	private int N;
	private int[] h;
	private int w;
	private Map<String, Double> dp;
	public double maximalLength2(int[] height, int w) {
		N = height.length;
		h = height;
		this.w = w;
		dp = new HashMap<String, Double>();
		return Math.max(solve(1, 1), solve(1, height[0]));
	}
	private double solve(int i, int y) {
		if (i == N)
			return 0;
		if (dp.containsKey(i + " " + y))
			return dp.get(i + " " + y);
		double ans = Math.max(Math.sqrt(w * w + (y - 1) * (y - 1)) + solve(i + 1, 1),
				Math.sqrt(w * w + (y - h[i]) * (y - h[i])) + solve(i + 1, h[i]));
		dp.put(i + " " + y, ans);
		return ans;
	}
}
