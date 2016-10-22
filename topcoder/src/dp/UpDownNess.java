package dp;

public class UpDownNess {

	public static void main(String[] args) {
		UpDownNess obj = new UpDownNess();
		System.out.println(obj.count(4, 3));
	}
	
	private final int MOD = 1000000007;
	public int count(int N, int K) {
		// dp[n][k]: the number of qualified triples among the first n numbers with exactly k triples 
		int[][] dp = new int[N + 1][K + 1];
		for (int n = 1; n < 3 && n <= N; n++) {
			dp[n][0] = 1;
			for (int i = 1; i <= n; i++) {
				dp[n][0] = dp[n][0] * i;
			}
		}
		for (int n = 3; n <= N; n++) {
			for (int k = 0; k <= K; k++) {
				for (int i = 1; i <= n; i++) {
					int newAdd = (i - 1) * (n - i);
					if (k >= newAdd) {
						dp[n][k] = (int)(((long)dp[n][k] + dp[n - 1][k - newAdd]) % MOD);
//						System.out.printf("i=%d, dp[n][k]=dp[%d][%d]=%d, dp[n-1][k-newAdd]=dp[%d][%d]=%d\n", 
//								i, n, k, dp[n][k], n - 1, k - newAdd, dp[n-1][k-newAdd]);
					} else {
//						System.out.printf("n=%d, k<newAdd, k=%d, newAdd=%d, i=%d\n", n, k, newAdd, i);
					}
					
				}
			}
		}
		return dp[N][K];
	}
}
