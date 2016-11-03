package dp;

import java.util.Arrays;

public class AliceBobShuffle {

	public static void main(String[] args) {
		AliceBobShuffle obj = new AliceBobShuffle();
		int[] AliceStart= {1, 2, 1, 2, 1, 4, 10, 1, 1, 1, 1, 1, 1, 1, 2, 1, 4, 5, 1, 2, 4, 3, 1, 2, 1, 2, 2, 1, 1, 3, 2, 5, 3, 1, 2, 1, 2, 1, 3, 2, 3, 1, 3, 2, 2, 1, 1, 3, 1, 2};
		int[] BobStart = {1, 1, 2, 3, 1, 2, 1, 1, 2, 1, 1, 2, 1, 1, 2, 6, 1, 2, 1, 1, 1, 1, 1, 7, 3, 1, 3, 2, 4, 3, 1, 1, 3, 1, 1, 2, 1, 1, 2, 3, 1,6, 2, 3, 1, 2, 2, 1};
		int[] AliceEnd = {1, 2, 1, 1, 2, 1, 4, 1, 10, 2, 3, 1, 1, 1, 2, 2, 1, 4, 5, 1, 2, 3, 1, 2, 2, 1, 3, 1, 2, 1, 5, 3, 2, 1, 1, 3, 1, 1, 3, 1, 2, 1, 2, 1, 3, 1, 2, 1, 1, 6};
		int[] BobEnd = {1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 6, 1, 4, 1, 2, 2, 1, 1, 1, 1, 3, 7, 1, 1, 3, 2, 4, 2, 3, 2, 3, 1, 3, 2, 1, 1, 3, 2, 2, 3, 1, 2, 2, 1};
		System.out.println(obj.countWays(AliceStart, BobStart, AliceEnd, BobEnd));
	}
	
	public int countWays(int[] AliceStart, int[] BobStart, int[] AliceEnd, int[] BobEnd) {
		this.AliceStart = AliceStart;
		this.AliceEnd = AliceEnd;
		this.BobStart = BobStart;
		this.BobEnd = BobEnd;
		this.dp = new int[51][51][51][51];
		for (int i = 0; i <= 50; i++) {
			for (int j = 0; j <= 50; j++) {
				for (int k = 0; k <= 50; k++) {
					Arrays.fill(dp[i][j][k], -1);
				}
			}
		}
		return recur(AliceStart.length, BobStart.length, AliceEnd.length, BobEnd.length);
	}
	
	private final int MOD = 1000000007;
	private int[] AliceStart;
	private int[] AliceEnd;
	private int[] BobStart;
	private int[] BobEnd;
	private int[][][][] dp;
	private int recur(int a, int b, int c, int d) {
		if (dp[a][b][c][d] != -1)
			return dp[a][b][c][d];
		if (a == 0 && b == 0 && c == 0 && d == 0)
			return 1;
		long res = 0;
		if (a > 0) {
			if (c > 0 && AliceStart[a - 1] == AliceEnd[c - 1]) {
				res += recur(a - 1, b, c - 1, d);
			}
			if (d > 0 && AliceStart[a - 1] == BobEnd[d - 1]) {
				res += recur(a - 1, b, c, d - 1);
			}
		}
		if (b > 0) {
			if (c > 0 && BobStart[b - 1] == AliceEnd[c - 1]) {
				res += recur(a, b - 1, c - 1, d);
			}
			if (d > 0 && BobStart[b - 1] == BobEnd[d - 1]) {
				res += recur(a, b - 1, c, d - 1);
			}
		}
		res %= MOD;
		dp[a][b][c][d] = (int)res;
		return dp[a][b][c][d];
	}
}
