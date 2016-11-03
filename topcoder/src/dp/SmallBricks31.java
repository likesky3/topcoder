package dp;

public class SmallBricks31 {

	public static void main(String[] args) {
		SmallBricks31 obj = new SmallBricks31();
//		System.out.println(obj.countWays(1, 4));
		System.out.println(obj.countWays(3, 2));
	}
	
	private int w;
	private int[][] dp;
	private final int MOD = 1000000007;
	public int countWays(int w, int h) {
		this.w = w;
		dp = new int[h + 1][1<<w];
		dp[0][(1<<w) - 1] = 1;
		recur(1, (1 << w) - 1, 0, 0);
		
		long res = 1;
		// we don't add dp[i][0] in the result, since dp[i][0] = dp[i-1][(1<<w) -1]
		for (int j = 1; j < (1<<w); j++) {
			res += dp[1][j];
//			System.out.printf("dp[%d][%d]=%d\n", h, j, dp[h][j]);
			res %= MOD;
		}
		for (int i = 2; i <= h; i++) {
			for (int j = 1; j < (1<<w); j++) {
				recur(i, j, 0, 0);
			}
			// below for loop cannot be merged into the prev one, since each dp[i][j] has not been finished yet
			// if merged, some dp[i][j] will be added repeatedly
			for (int j = 1; j < (1<<w); j++) {
				res += dp[i][j]; 
//				System.out.printf("h=%d, mask=%d, dp=%d\n", i, j, dp[i][j]);
			}
			res %= MOD;
		}
		return (int)res;
	}
	
	private void recur(int h, int prevMask, int k,  int currMask) {
		if (k == w) {
			dp[h][currMask] += dp[h - 1][prevMask];
			dp[h ][currMask] %= MOD;
//			System.out.printf("prevMask=%d, dp[%d][%d]=%d\n", prevMask, h, currMask, dp[h][currMask]);
			return;
		}
		if ((prevMask & (1 << k)) == 0) { // position k of below layer is empty
			recur(h, prevMask, k + 1, currMask);
			return;
		} else {
			recur(h, prevMask, k + 1, currMask); // not add any brick at pos k
			recur(h, prevMask, k + 1, currMask | (1 << k)); // add a 1x1 brick
			if (k + 1 < w && (prevMask & (1 << (k + 1))) > 0) {
				recur(h, prevMask, k + 2, currMask | (3 << k)); // add a 1x2 brick
			}
			if (k + 2 < w && (prevMask & (1 << (k + 2))) > 0) {
				recur(h, prevMask, k + 3, currMask | (7 << k)); // add a 1x3 brick
			}
		}
	}
}
