package review.dp;

public class SmallBricks31 {

	public static void main(String[] args) {

	}
	
	public int countWays(int w, int h) {
		this.w = w;
		dp = new int[h + 1][1 << w];
		dp[0][(1 << w) - 1] = 1;
		recur(1, (1 << w) - 1, 0, 0); // take special care of height 1
		
		long result = 1;
		for (int j = 1; j < 1 << w; j++) {
			result += dp[1][j];
		}
		
		for (int i = 2; i <= h; i++) {
			for (int j = 1; j < 1 << w; j++) {
				recur(i, j, 0, 0);
			}
			for (int j = 1; j < 1 << w; j++) {
				result += dp[i][j];
			}
			if (result > MOD) 
				result %= MOD;
 		}
		return (int)result;
	}
	
	private int[][] dp;
	private int w;
	private final int MOD = 1000000007;
	
	// calculate number of structure we can get at height h with prev row being prevMask and current row is currMask,
	// at this moment we are considering position k in current row
	private void recur(int h, int prevMask, int k, int currMask) {
		if (k == w) {
			dp[h][currMask] += dp[h][prevMask];
			if (dp[h][currMask] > MOD)
				dp[h][currMask] %= MOD;
			return;
		}
		if ((prevMask & (1 << k)) == 0) { // position k of the previous row is empty
			recur(h, prevMask, k + 1, currMask);
		} else {
			recur(h, prevMask, k + 1, currMask); // leave position k empty
			recur(h, prevMask, k + 1, currMask | (1 << k)); // put a 1*1 brick in position k
			if ((prevMask & (1 << (k + 1))) > 0) {
				recur(h, prevMask, k + 2, currMask | (3 << k)); // put a 1 * 2 brick
			}
			if ((prevMask & (1 << (k + 2))) > 0) {
				recur(h, prevMask, k + 3, currMask | (7 << k)); // put a 1 * 3 brick
			}
		}

	}

}
