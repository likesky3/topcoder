package review.dp;

public class CarelessSecretary {

	public static void main(String[] args) {
		CarelessSecretary obj = new CarelessSecretary();
		System.out.println(obj.howMany(714, 9));
	}
	
	public int howMany(int N, int K) {
		long[][] dp = new long[N + 1][K + 1];
		for (int i = 0; i <= N; i++) {
			dp[i][0] = getFactor(i);
		}
		for (int i = 1; i <= N; i++) {
			dp[i][1] = ((i - 1) * getFactor(i - 1)) % MOD;
			int wrongNumLimit = Math.min(i, K);
			for (int j = 2; j <= wrongNumLimit; j++) {
				dp[i][j] = ((j - 1) * dp[i - 1][j - 2] + (i - j) * dp[i - 1][j - 1]) % MOD;
//				System.out.printf("dp[%d][%d]=%d\n", i, j, dp[i][j]);
			}
		}
		return (int)dp[N][K];
	}
	
	private final int MOD = 1000000007;
	private long getFactor(int n) {
		long result = 1;
		for (int i = 2; i <= n; i++) {
			result = result * i % MOD;
		}
		return result;
	}
}
