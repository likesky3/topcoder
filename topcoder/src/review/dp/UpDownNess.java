package review.dp;

public class UpDownNess {
	public int count(int N, int K) {
		if (N < 3) {
			if (K == 0)
				return N;
			else
				return 0;
		}
		// dp[i][j]: the number of low-high-low triple is j in the first i numbers
		long[][] dp = new long[N + 1][K + 1];
		dp[1][0] = 1;
		dp[2][0] = 2;
		for (int i = 2; i < N; i++) {
			for (int p = 0; p <= i; p++) {
				int newAdded = p * (i - p);
				for (int k = 0; k <= K - newAdded; k++) {
					dp[i + 1][k + newAdded] += dp[i][k];
					if (dp[i + 1][k + newAdded] >= mod)
						dp[i + 1][k + newAdded] %= mod;
				}
			}
		}
		return (int)dp[N][K];
	}
	
	private final int mod = 1000000007;
	public static void main(String[] args) {
		UpDownNess obj = new UpDownNess();
		System.out.println(obj.count(3, 1));
		System.out.println(obj.count(3, 0));
		System.out.println(obj.count(4, 3));
		System.out.println(obj.count(19, 19));
	}
}
