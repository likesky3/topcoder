package review.dp;

import java.util.Arrays;

public class NineEasy {

	public static void main(String[] args) {
		NineEasy obj = new NineEasy();
		int N = 5;
		int[] d = {1,2,4,8,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		System.out.println(obj.count(N, d));
	}
	
	public int count(int N, int[] d) {
		this.N = N;
		this.M = d.length;
		this.d = d;
		power[0] = 1;
		for (int i = 1; i < 5; i++) {
			power[i] = 9 * power[i - 1];
		}
		for (int[] p : dp) {
			Arrays.fill(p, -1);
		}
		return recur(0, 0);
	}
	
	private int N;
	private int M;
	private int[] d;
	private int[] power = new int[5];
	private int[][] dp = new int[9 * 9 * 9 * 9 * 9][21];
	private final int MOD = 1000000007;
	private int recur(int mask, int p) {
		if (p == M) {
			return mask == 0 ? 1 : 0;
		}
		if (dp[mask][p] != -1) {
			return dp[mask][p];
		}
		long result = 0;
		for (int i = 0; i <= 9; i++) { // try each digit in p position
			// update each question's state
			int mask2 = 0;
			for (int j = N - 1; j >= 0; j--) {
				int bit = (mask / power[j]) % 9;
				if ((d[p] & (1 << j)) > 0) { // question j read digit p
					bit = (bit + i) % 9;
				}
				mask2 = mask2 * 9 + bit;
			}
			result += recur(mask2, p + 1);
			if (result > MOD)
				result %= MOD; }
		dp[mask][p] = (int)result;
		return (int)result;
	}
}
