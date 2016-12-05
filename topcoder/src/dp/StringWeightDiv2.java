package dp;

import java.util.Arrays;


public class StringWeightDiv2 {

	public static void main(String[] args) {
		StringWeightDiv2 obj = new StringWeightDiv2();
		System.out.printf("res=%d, pass=%b\n", obj.countMinimums(50), obj.countMinimums(50) == 488801539);
//		System.out.printf("res=%d, pass=%b\n", obj.countMinimums(1), obj.countMinimums(1) == 26);
//		System.out.printf("res=%d, pass=%b\n", obj.countMinimums(2), obj.countMinimums(2) == 26 * 25);
//		System.out.printf("res=%d, pass=%b\n", obj.countMinimums(13), obj.countMinimums(13) == 949597241);
	}
	
	public int countMinimums(int L) {
		if (L <=26) {
			long res = 1;
			for (int i = 26 - L + 1; i <= 26; i++) {
				res = (res * i) % MOD;
			}
			return (int) res;
		} else {
			memo = new int[27][L + 1];
			for (int i = 0; i < 27; i++)
				Arrays.fill(memo[i], -1);
			// all letters must appear at least once, all instances of each letter must be consecutive
			return (int) onceConsecutive(26, L);
		}
	}
	
	public long onceConsecutive(int a, int L) {
		if (memo[a][L] != -1)
			return memo[a][L];
		if (L == 0)
			return a == 0 ? 1 : 0;
		if (a == 1)
			return 1;
		
		long res = 0;
		for (int i = 1; i <= L; i++) {
			res += (a * onceConsecutive(a - 1, L - i)) % MOD;
		}
		res %= MOD;
		memo[a][L] = (int) res;
		return res;
	}
	
	public int countMinimums3(int L) {
		if (L <= 26) {
			long res = 1;
			for (int i = 0; i < L; i++) {
				res = (res * (26 - i)) % MOD;
			}
			return (int)res;
		} else {
			// dp[i][j]: number of ways to compose a string of length i with j letters
			long[][] dp = new long[L + 1][27];
			dp[0][0] = 1;
			for (int i = 1; i <= L; i++) {
				for (int j = 1; j <= 26; j++) {
					dp[i][j] = (dp[i - 1][j - 1] * j + dp[i - 1][j]) % MOD;
				}
			}
			return (int)dp[L][26];
		}
	}
	
	private final int  MOD = 1000000009;
	private int[][] memo; // memo[a][L] is number of ways of building a string of length L using an alphabet with a letters.
	
	
}
