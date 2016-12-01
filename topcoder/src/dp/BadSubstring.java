package dp;

public class BadSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BadSubstring obj = new BadSubstring();
		System.out.println(obj.howMany(29));
		System.out.println(Long.MAX_VALUE);
		System.out.println(Math.pow(3, 44));
		System.out.println(Math.pow(3, 29));
	}
	
	// why wrong?
	public long howMany1(int length) {
		if (length == 0)
			return 1;
		long[] dp = new long[length + 1];
		dp[0] = 1;
		for (int i = 1; i <= length; i++) {
			long validNum = 0;
			for (int j = 0; j <= i - 2; j++) {
				validNum += dp[j] * dp[i - 2 - j];
			}
			dp[i] = (long)Math.pow(3, i) - validNum;
		}
		return dp[length];
	}
	
	public long howMany(int length) {
		if (length == 0)
			return 1;
		long[] A = new long[length + 1]; //A[i]: how many badstring end with 'a'
		long[] N = new long[length + 1]; //N[i]: how many badstring not end with 'a'
		A[1] = 1;
		N[1] = 2;
		for (int i = 2; i <= length; i++) {
			A[i] = A[i - 1] + N[i - 1];
			N[i] = 2 * N[i - 1] + A[i - 1];
		}
		return A[length] + N[length];
	}
	
	private final int END_A = 0;
	private final int END_B = 1;
	private final int END_C = 2;
	public long howMany2(int length) {
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
