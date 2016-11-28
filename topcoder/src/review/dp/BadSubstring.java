package review.dp;

public class BadSubstring {

	public static void main(String[] args) {
		BadSubstring obj = new BadSubstring();
		System.out.println(obj.howMany(29));
	}
	
	private final int END_A = 0;
	private final int END_B = 1;
	private final int END_C = 2;
	public long howMany(int length) {
		if (length == 0) return 1;
		if (length == 1) return 3;
		long[][] dp = new long[length + 1][3];
		dp[1][END_A] = 1;
		dp[1][END_B] = 1;
		dp[1][END_C] = 1;
		for (int i = 2; i <= length; i++) {
			dp[i][END_A] = dp[i - 1][END_A] + dp[i - 1][END_B] + dp[i - 1][END_C];
			dp[i][END_B] = dp[i - 1][END_B] + dp[i - 1][END_C];
			dp[i][END_C] = dp[i - 1][END_A] + dp[i - 1][END_B] + dp[i - 1][END_C];
		}
		return dp[length][END_A] + dp[length][END_B] + dp[length][END_C];
	}

}
