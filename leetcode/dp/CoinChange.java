package dp;

import java.util.Arrays;

public class CoinChange {

	public static void main(String[] args) {
		CoinChange obj = new CoinChange();
		int[] coins = {1, 2, 5};
		System.out.println(obj.coinChange2(coins, 11));
	}
	
	public int coinChange(int[] coins, int amount) {
		int M = coins.length;
		int[][] dp = new int[M + 1][amount + 1];
		Arrays.fill(dp[0], Integer.MAX_VALUE);
		dp[0][0] = 0;
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < coins[i - 1]; j++) {
				dp[i][j] = dp[i - 1][j];
			}
			for (int j = coins[i - 1]; j <= amount; j++) {
				if(dp[i][j - coins[i - 1]] != Integer.MAX_VALUE)
					dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
			}
		}
		return dp[M][amount] == Integer.MAX_VALUE ? -1 : dp[M][amount];
	}
	
	public int coinChange2(int[] coins, int amount) {
		int M = coins.length;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= M; i++) {
			for (int j = coins[i - 1]; j <= amount; j++) {
				if (dp[j - coins[i - 1]] != Integer.MAX_VALUE) // without this, overflow at {2}, 3
					dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
			}
		}
		return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
	}
}
