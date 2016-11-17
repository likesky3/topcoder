package dp;

import java.util.Arrays;

public class CuttingBitString {

	public static void main(String[] args) {
		CuttingBitString obj = new CuttingBitString();
		//System.out.println(obj.getmin("1000101011"));
		System.out.println(obj.getmin("1101100011010111001001101011011100010111011110101"));
	}
	
	public int getmin(String s) {
		int N = s.length();
		// dp[i]: the minimum cuts to make each part power of 5
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		for (int i = 1; i <= N; i++) {
			if (isPowerOf5(s.substring(0, i))) {
				dp[i] = 1;
			} else {
				for (int j = 1; j < i; j++) {
					if (dp[j] != Integer.MAX_VALUE && isPowerOf5(s.substring(j, i))) {
						dp[i] = Math.min(dp[i], dp[j] + 1);
					}
				}
			}
//			System.out.printf("dp[%d]=%d\n", i, dp[i]);
		}
		return dp[N] == Integer.MAX_VALUE ? -1 : dp[N];
	}
	
	private boolean isPowerOf5(String s) { 
		if (s.charAt(0) == '0')
			return false;
		long num = 0;
		for (int i = 0; i < s.length(); i++) {
			num = num * 2 + s.charAt(i) - '0';
		}
		if (num % 10 == 0)
			return false;
		while (num % 5 == 0)
			num /= 5;
		return num == 1;
	}
}
