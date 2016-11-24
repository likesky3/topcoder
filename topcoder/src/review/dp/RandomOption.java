package review.dp;

import java.util.Arrays;

public class RandomOption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RandomOption obj = new RandomOption();
		int[] badLane1 = {0};
		int[] badLane2 = {1};
		System.out.println(obj.getProbability(5, badLane1, badLane2));
	}
	
	public double getProbability(int keyCount, int[] badLane1, int[] badLane2) {
		n = keyCount;
		memo = new double[1 << n][n + 1];
		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}
		banned = new boolean[15][15];
		for (int i = 0; i < badLane1.length; i++) {
			banned[badLane1[i]][badLane2[i]] = true;
			banned[badLane2[i]][badLane1[i]] = true;
		}
		return recur((1<<n) - 1, n);
	}
	private int n;
	private double[][] memo;
	private boolean[][] banned;
	private double recur(int remains, int last) {
		if (memo[remains][last] != -1)
			return memo[remains][last];
		double ans = 0;
		int t = 0;
		for (int i = 0; i < n; i++) {
			if ((remains & (1 << i)) > 0) {
				t++;
				if (!banned[i][last]) {
					ans += recur(remains - (1 << i), i);
				}
			}
		}
		if (t == 0) 
			return memo[remains][last] = 1;
		else
			return memo[remains][last] = ans / t;
	}

}
