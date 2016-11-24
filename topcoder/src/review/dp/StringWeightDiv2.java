package review.dp;

import java.util.Arrays;

public class StringWeightDiv2 {

	public static void main(String[] args) {
		StringWeightDiv2 obj = new StringWeightDiv2();
		System.out.println(obj.comb(5, 2));
		System.out.println(obj.countMinimums(50));
		System.out.println(obj.countMinimums2(50));
	}
	
	private final int mod = 1000000009;
	// method 1: math
	public int countMinimums(int L) {
		if (L <= 26) {
			long res = 1;
			for (int i = 0; i < L; i++) {
				res = res * (26 - i) % mod;
			}
			return (int) res;
		} else {
			long perm = 1;
			for (int i = 1; i <= 26; i++) {
				perm = (perm * i) % mod;
			}
			return (int)(comb(L - 1, 25) * perm % mod);
		}
	}
	
	private int comb(int a, int b) {
		long[][] dp = new long[a + 1][b + 1];
		for (int i = 0; i <= a; i++) {
			dp[i][0] = 1;
		}
		// dp[a][b] = dp[a - 1][b] + dp[a - 1][b - 1]
		for (int i = 1; i <= a; i++) {
			for (int j = 1; j <= b; j++) {
				dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
				dp[i][j] %= mod;
			}
		}
		return (int)dp[a][b];
	}

	// method 2: dp
	public int countMinimums2(int L) {
		if (L <= 26) {
			long res = 1;
			for (int i = 0; i < L; i++) {
				res = res * (26 - i) % mod;
			}
			return (int)res;
		} else {
			dp = new int[27][L + 1];
			for (int i = 0; i < 27; i++) {
				Arrays.fill(dp[i], -1);
			}
			return (int)recur(26, L);
		}
	}
	
	// different number of strings which composed by a letters and length is L, satisfying two properties:
	// each letter must be used at least once
	// same letter should be used consecutively
	private long recur(int a, int L) {
		if (dp[a][L] != -1)
			return dp[a][L];
		if (L == 0)
			return a == 0 ? 1 : 0;
		if (a == 1)
			return 1;
		long result = 0;
		for (int i = 1; i <= L; i++) {
			result = (result + a * recur(a - 1, L - i)) % mod;
		}
		dp[a][L] = (int)result;
		return result;
	}
	private int[][] dp;
}
