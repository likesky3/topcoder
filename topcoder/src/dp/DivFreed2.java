package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivFreed2 {

	public static void main(String[] args) {
		DivFreed2 obj = new DivFreed2();
		System.out.println(obj.count(10, 100000));
	}
	
	public int count(int n, int k) {
		// calculate divisors of each number
		Map<Integer, List<Integer>> divisors  = new HashMap<>();
		for (int i = 1; i <= k; i++) {
			divisors.put(i, new ArrayList<Integer>());
		}
		for (int i = 1; i <= k; i++) {
			for (int j = 2 * i; j <= k; j += i) {
				divisors.get(j).add(i);
			}
		}
		// start dp
		// dp[i][j]: number of good arrays which starts at j with length of i
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= k; i++) {
			dp[1][i] = 1;
		}
		for (int i = 2; i <= n; i++) {
			long sum = 0;
			for (int j = 1; j <= k; j++) {
				sum += dp[i - 1][j];
			}
			sum %= MOD; 
			// remove those not satisfy the need property
			for (int j = 1; j <= k; j++) {
				dp[i][j] = (int)sum;
				for (int p : divisors.get(j)) {
					dp[i][j] = (int)((long)dp[i][j] + MOD - dp[i - 1][p]) % MOD;
				}
			}
		}
		long result = 0;
		for (int i = 1; i <= k; i++) {
			result = (result + dp[n][i]) % MOD;
		}
		return (int)result;
	}
	private final int MOD = 1000000007;
}
