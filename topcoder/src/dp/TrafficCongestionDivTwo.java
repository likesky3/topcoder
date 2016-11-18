package dp;

import java.util.Arrays;

public class TrafficCongestionDivTwo {

	public static void main(String[] args) {
		TrafficCongestionDivTwo obj = new TrafficCongestionDivTwo();
		System.out.println(obj.theMinCars(10));
	}
	
	public long theMinCars(int treeHeight) {
		if (treeHeight < 2)
			return 1;
		long[] dp = new long[treeHeight + 1];
		Arrays.fill(dp, 1);
		for (int i = 2; i <= treeHeight; i++) {
			for (int j = 0; j <= i - 2; j++) {
				dp[i] += 2 * dp[j];
			}
		}
		return dp[treeHeight];
	}

}
