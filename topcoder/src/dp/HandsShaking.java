package dp;

public class HandsShaking {

	public static void main(String[] args) {
		HandsShaking obj = new HandsShaking();
		System.out.println(obj.countPerfect(8));
	}
	
	public long countPerfect(int n) {
		long [] dp = new long[n + 1];
		dp[0] = 1;
		for (int i = 2; i <= n; i += 2) {
			for (int j = 2; j <= i; j += 2) {
				dp[i] += dp[j - 2] * dp[i - j];
			}
		}
		return dp[n];
	}

}
