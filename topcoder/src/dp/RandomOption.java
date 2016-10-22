package dp;

import java.util.Arrays;

public class RandomOption {
	public double getProbability(int keyCount, int[] badLane1, int[] badLane2) {
		n = keyCount;
		banned = new boolean[15][15];
		for (int i = 0; i < badLane1.length; i++) {
			banned[badLane1[i]][badLane2[i]] = true;
			banned[badLane2[i]][badLane1[i]] = true;
		}
		// memo[remaining][last]: the probability that the permutation of the remaining lanes doesn't have banned pair,
		// the "last" is the item picked for the previous position
		memo = new double[1 << n][n + 1];
		for (int i = 0; i < (1 << n); i++)
			Arrays.fill(memo[i], -1);
		return recur((1 << n) - 1, n);
	}
	
	private int n;
	private double[][] memo;
	private boolean[][] banned;
	private double recur(int mask, int last) {
		if (memo[mask][last] != -1)
			return memo[mask][last];
		double ans = 0;
		int t = 0;
		for (int x = 0; x < n; x++) {
			// x is in the remaining set and is not banned with last
			if ((mask & (1 << x)) > 0) {
				if (!banned[last][x]) {
					int nmask = mask - (1 << x);
					ans += recur(nmask, x);
				}
				t++;
			}
		}
		if (t == 0)
			memo[mask][last] = 1;
		else
			memo[mask][last] = ans / t;
		return memo[mask][last];
	}
	
	public static void main(String[] args) {
		RandomOption obj = new RandomOption();
		int[] badLane1 = {0};
		int[] badLane2 = {1};
		System.out.println(obj.getProbability(5, badLane1, badLane2));
	}
}
