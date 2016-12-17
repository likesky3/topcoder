package dp;

public class BuySellStock {

	public static void main(String[] args) {
		BuySellStock obj = new BuySellStock();
		int[] prices = {7, 1, 4, 6, 3};
		System.out.println(obj.maxProfit(prices));
	}
	
	public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int minSoFar = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit = Math.max(prices[i] - minSoFar, profit);
            minSoFar = Math.min(minSoFar, prices[i]);
        }
        return profit;
    }

}
