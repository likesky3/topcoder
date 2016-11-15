package dp;

public class TaroCoins {

	public static void main(String[] args) {
		TaroCoins obj = new TaroCoins();
		System.out.println(obj.getNumber(47));
	}
	
	// 2^k = 2 * 2^(k-1), but 2 & (2^(k-2) + ...+ 2^0) = 2^k - 2
	long getNumber(long N) {
	    long[][] dp = new long[61][4]; // 2^60 > 10^18
	    for (int i = 0; i <= 60; i++) {
	    	for (int owned = 0; owned <= 3; owned++) {
	    		dp[i][owned] = 0;
	    		int r = owned + ((((long)1 << i) & N) > 0 ? 1 : 0);
	    		for (int u = 0; u <= 2 && u <= r; u++) {
	    			int newOwned = 2 * (r - u);
	    			if (newOwned <= 3) {
	    				if (i == 0) {
	    					dp[i][owned] += newOwned == 0 ? 1 : 0;
	    				} else {
	    					dp[i][owned] += dp[i - 1][newOwned];
	    				}
	    			}
//	    			System.out.printf("dp[%d][%d]=%d, r=%d, u=%d, newOwned=%d\n", i, owned, dp[i][owned], r, u, newOwned);
	    		}
	    		
	    	}
	    }
	    return dp[60][0];
	}
}
