package dp;

import java.util.Arrays;

public class CarelessSecretary {

	public static void main(String[] args) {
		CarelessSecretary obj = new CarelessSecretary();
		System.out.println(obj.howMany(714, 4));
		System.out.println(obj.howMany2(714, 4));
	}
	
	private final int MOD = 1000000007;
	public int howMany(int N, int K) {
		long[][] dp = new long[2][K + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= N; i++) {
			dp[i % 2][0] = getFactor(i);
			dp[i % 2][1] = (i - 1) * dp[(i-1) % 2][0] % MOD;
			int limit = Math.min(i, K);
			for (int j = 2; j <= limit; j++) {
				dp[i % 2][j] = (j - 1) * dp[(i-1) % 2][j - 2] + (i - j) * dp[(i-1) % 2][j - 1];
				dp[i % 2][j] %= MOD;
			}
		}
		return (int) dp[N % 2][K];
	}
	
	private long getFactor(int n) {
		long result = 1;
		for (int i = 1; i <= n; i++) {
			result *= i;
			result %= MOD;
		}
		return result;
	}
	
	// recursive version would timeout
	public int howMany2(int N, int K) {
		this.N = N;
		this.K = K;
		dp = new int[N];
		Arrays.fill(dp, -1);
		assigned = new boolean[N];
		return recur(0);
	}
	private int N, K;
	private int[] dp;
	private boolean[] assigned;
	private int recur(int i) {
		if (dp[i] != -1) return dp[i];
		if (i == K) return dp[i] = (int)getFactor(N - i);
		long result = 0;
		for (int j = 0; j < N; j++) {
			if (j != i && !assigned[j]) {
				assigned[j] = true;
				result += recur(i + 1);
				result %= MOD;
				assigned[j] = false;
			}
		}
		return (int)result;
	}

}
