package dp;

public class BuySellStockIII {

	public static void main(String[] args) {
		BuySellStockIII obj = new BuySellStockIII();
		int[] prices = {1, 2, 3, 10, 6, 7, 2, 5};
		System.out.println(obj.maxProfit(prices));
	}
	
	public int maxProfit(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;
		int N = prices.length;
		int[] sellUpto = new int[N]; // max profit when sell before or at day i
		int min = prices[0];
		for (int i = 1; i < N; i++) {
			sellUpto[i] = Math.max(sellUpto[i - 1], prices[i] - min);
			min = Math.min(prices[i], min);
		}
		int[] buyStartFrom = new int[N]; // max profit when buy after or at day i
		int max = prices[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			buyStartFrom[i] = Math.max(max - prices[i], buyStartFrom[i]);
			max = Math.max(prices[i], max);
		}
		int profit = 0;
		for (int i = 0; i < N; i++) {
			profit = Math.max(profit, sellUpto[i] + buyStartFrom[i]);
		}
		return profit;
	}

}
