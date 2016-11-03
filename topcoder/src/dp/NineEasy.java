package dp;

import java.util.Arrays;

public class NineEasy {

	public static void main(String[] args) {
		NineEasy obj = new NineEasy();
		int[] d = {1, 3, 2};
		System.out.println(obj.count(2, d));
	}
	
	private int N;
	private int M;
	private int[] d;
	private int[] power9 = new int[6];
	private int[][] dp = new int[9*9*9*9*9][21];
	private final int MOD = 1000000007;
	public int count(int N, int[] d) {
		this.N = N;
		this.M = d.length;
		this.d = d;
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		power9[0] = 1;
		for (int i = 1; i <= 5; i++) {
			power9[i] = 9 * power9[i - 1];
		}
		return recur(0, 0);
	}
	
	// current state is mask(s(N-1),...,s2,s1,s0), we try to fill the digits in position greater than or equal to p
	private int recur(int mask, int p) {
		if (p == M)
			return mask == 0 ? 1 : 0;
		if (dp[mask][p] != -1)
			return dp[mask][p];
		int res = 0;
		for (int i = 0; i <= 9; i++) {
			int mask2 = 0;
			for (int j = N - 1; j >= 0; j--) {
				int bit = (mask / power9[j]) % 9;
				if ((d[p] & (1 << j)) > 0) {
					bit = (bit + i) % 9;
				}
				mask2 = mask2 * 9 + bit;
			}
			res += recur(mask2, p + 1);
			if (res > MOD)
				res -= MOD;
		}
		dp[mask][p] = res;
		return res;
	}
}
