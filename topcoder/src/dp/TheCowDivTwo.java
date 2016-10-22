package dp;

public class TheCowDivTwo {

	public static void main(String[] args) {
		TheCowDivTwo obj = new TheCowDivTwo();
		System.out.println(obj.find(7, 4));
	}
	
	private final int MOD = 1000000007;
	public int find(int N, int K) {
		// dp[p][k][s] means the number of sets we can get from (p, p+1, ..., N-1), 
		// each set is size of k and the sum of elements in the set is s
		int[][][] dp = new int[2][K + 1][N];
		for (int p = N; p >= 0; p--) {
			for (int k = 0; k <= K; k++) {
				for (int s = 0; s < N; s++) {
					if (k == 0)
						dp[p%2][k][s] = s == 0 ? 1 : 0;
					else if (p == N)
						dp[p%2][k][s] = 0;
					else
						dp[p%2][k][s] = dp[(p + 1) % 2][k - 1][(s - p + N) % N]
								+ dp[(p + 1) % 2][k][s];
					dp[p%2][k][s] %= MOD;
				}
			}
		}
		return dp[0][K][0];
	}

}
