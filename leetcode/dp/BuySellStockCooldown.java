package dp;

import java.util.Arrays;

public class BuySellStockCooldown {

	public static void main(String[] args) {
		BuySellStockCooldown obj = new BuySellStockCooldown();
		int[] prices = {1, 2, 3, 10};
		System.out.println(obj.maxProfit(prices));
		System.out.println(obj.maxProfit2(prices));
	}

	final int BUY = 0;
	final int SELL = 1;
	final int COOL = 2;
	public int maxProfit(int[] prices) {
		int N = prices.length;
		int[][] dp = new int[N + 1][3];
		for (int[] arr1 : dp) {
			Arrays.fill(arr1, Integer.MIN_VALUE);
		}
		dp[0][BUY] = Integer.MIN_VALUE;
		dp[0][SELL] = 0;
		dp[0][COOL] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < i; j++) {
				dp[i][BUY] = Math.max(dp[i][BUY], dp[j][COOL] - prices[i - 1]);
				dp[i][SELL] = Math.max(dp[i][SELL], dp[j][BUY] + prices[i - 1]);
				dp[i][COOL] = Math.max(dp[i][COOL], dp[j][SELL]);

//				System.out.printf("dp[%d][BUY]=%d\t, dp[%d][COOL] - prices[%d]=%d - %d = %d\n", 
//						i, dp[i][BUY], j, i - 1, dp[j][COOL], prices[i - 1], dp[j][COOL] - prices[i - 1]);
//				System.out.printf("dp[%d][SELL]=%d\t, dp[%d][BUY] + prices[%d]=%d + %d = %d\n", 
//						i, dp[i][SELL], j, i - 1, dp[j][BUY], prices[i - 1], dp[j][BUY] + prices[i - 1]);
//				System.out.printf("dp[%d][COOL]=%d\t, dp[%d][SELL]=%d\n\n", 
//						i, dp[i][COOL], j, dp[j][SELL]);
			}
		}
		return Math.max(dp[N][SELL], dp[N][COOL]);
	}
	
	public int maxProfit2(int[] prices) {
		int prevSell = 0, sell = 0, prevBuy = Integer.MIN_VALUE, buy = Integer.MIN_VALUE;
		for (int price : prices) {
			prevBuy = buy;
			buy = Math.max(prevSell - price, prevBuy);
			prevSell = sell;
			sell = Math.max(prevBuy + price, prevSell);
		}
		return sell;
	}
}
