package dp;

public class BuySellStockIV {

	public static void main(String[] args) {
		BuySellStockIV obj = new BuySellStockIV();
		int[] prices = {1, 12, 3, 4, 10};
		System.out.println(obj.maxProfit(3, prices)); 
	}
	
	public int maxProfit(int k, int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;
		if (k >= prices.length)
			return maxProfitInfinite(prices);
		int N = prices.length;
		int[] sellOneLess = new int[N + 1];
		int[] sell= new int[N + 1];
		int[] buy = new int[N + 1];
		for (int i = 1; i <= k; i++) {
			sell[0] = 0;
			buy[0] = Integer.MIN_VALUE;
			for (int j = 1; j <= N; j++) {
				sell[j] = Math.max(buy[j - 1] + prices[j - 1], sell[j - 1]);
				buy[j] = Math.max(sellOneLess[j - 1] - prices[j - 1], buy[j - 1]);
			}
			for (int j = 0; j <= N; j++) {
				sellOneLess[j] = sell[j];
			}
		}
		return sell[N];
	}
	
	public int maxProfit2(int k, int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;
		if (k >= prices.length)
			return maxProfitInfinite(prices);
		int N = prices.length;
		int[][] buy = new int[k + 1][N + 1];
		int[][] sell = new int[k + 1][N + 1];
		for (int i = 1; i <= k; i++) {
			sell[i][0] = 0;
			buy[i][0] = Integer.MIN_VALUE;
			for (int j = 1; j <= N; j++) {
				sell[i][j] = Math.max(buy[i][j - 1] + prices[j - 1], sell[i][j - 1]);
				buy[i][j] = Math.max(sell[i - 1][j - 1] - prices[j - 1], buy[i][j - 1]);
			}
		}
		return sell[k][N];
	}
	
	public int maxProfitInfinite(int[] prices) {
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			profit += Math.max(prices[i] - prices[i - 1], 0);
		}
		return profit;
	}

}
