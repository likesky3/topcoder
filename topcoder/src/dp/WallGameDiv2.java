package dp;

import java.util.Arrays;

public class WallGameDiv2 {

	public static void main(String[] args) {
		WallGameDiv2 obj = new WallGameDiv2();
		String[] costs = {"011122", "xx9xx1"};
		System.out.println(obj.play(costs));
	}
	
	private int[][][] memo;
	private String[] costs;
	private int w;
	private int h;
	public int play(String[] costs) {
		this.costs = costs;
		h = costs.length;
		w = costs[0].length();
		memo = new int[h][w][3];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Arrays.fill(memo[i][j], -1);
			}
		}
		return worstPath(0, 0, 0);
	}
	
	// d=1: move left, d=2: move right, d=0: move down
	private int worstPath(int x, int y, int d) { 
		if (memo[y][x][d] != -1) {
			return memo[y][x][d];
		}
		
		int c = costs[y].charAt(x) - '0';
		int res;
		if (costs[y].charAt(x) == 'x') {
			// blocking cell
			res = Integer.MIN_VALUE;
		} else if (y == h - 1) {
			// base case, reach the last row
			res = c;
		} else {
			// move down
			res = c + worstPath(x, y + 1, 0);
			// move left
			if (d != 2 && x > 0) {
				res = Math.max(res, c + worstPath(x - 1, y, 1));
			}
			// move right
			if (d != 1 && x < w - 1) {
				res = Math.max(res, c + worstPath(x + 1, y, 2));
			}
		}
		memo[y][x][d] = res;
		return res;
	}
	
	private final int blockedCost = -250000;
	int[] dRow = {0, 0, -1};
	int[] dCol = {1, -1, 0};
	// wrong algorithm, when we calculate dp[i][j], the optimal path may comes from dp[i][j +1] which we haven't calculated
	// yet according to the algorithm, in order to make it correct, add one more dimension to track the previous cell,
	// mark the direction between the previous one and the current one is enough 
	public int play1(String[] costs) {
		int n = costs.length;
		int m = costs[0].length();
		char[][] board = new char[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			board[i] = costs[i].toCharArray();
		}
		int[][] dp = new int[n][m];
		for (int[] p : dp)
			Arrays.fill(p, blockedCost);
		dp[0][0] = 0;
		// process row 0
		for (int j = 1; j < m; j++) {
			if (board[0][j] != 'x') {
				dp[0][j] = dp[0][j - 1] + board[0][j] - '0';
			} else {
				break;
			}
		}
		// process row from 1 to n - 2
		for (int i = 1; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'x')
					continue;
				for (int k = 0; k < dRow.length; k++) {
					int prevRow = i + dRow[k];
					int prevCol = j + dCol[k];
					if (prevRow < 0 || prevCol < 0 || prevCol >= m)
						continue;
					dp[i][j] = Math.max(dp[i][j], dp[prevRow][prevCol]); 
				}
				if (dp[i][j] != blockedCost) {
					dp[i][j] += board[i][j] - '0';
				}
//				System.out.printf("dp[%d][%d]=%d\n", i, j, dp[i][j]);
			}
		}
		// process the last row
		int maxCost = 0;
		for (int j = 0; j < m; j++) {
			if (board[n - 1][j] != 'x' && dp[n - 2][j] != blockedCost) {
				maxCost = Math.max(maxCost, dp[n - 2][j] + board[n - 1][j] - '0');
			}
		}
		return maxCost;
	}

	private final int UP = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	
	// time: O(n*m*m)
	// space: O(n*m)
	public int play2(String[] costs) {
		int n = costs.length;
		int m = costs[0].length();
		// dp[i][j][UP]: max cost with the prev being upwards
		// dp[i][j][LEFT]: max cost with the prev being left
		// dp[i][j][RIGHT]: max cost with the prev being right
		int[][][] dp = new int[n][m][3];
		for (int[][] a : dp) {
			for (int[] b : a) {
				Arrays.fill(b, Integer.MIN_VALUE);
			}
		}
		// dp2[i][j]: max cost from [0][0] to [i][j]
		int[][] dp2 = new int[n][m];
		for (int[] a : dp2) {
			Arrays.fill(a, Integer.MIN_VALUE);
		}
		// base case
		for (int i = 0; i < 3; i++) {
			dp[0][0][i] = 0;
		}
		dp2[0][0] = 0;
		
		// start dp: process row 1 to n - 2
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				if (costs[i].charAt(j) == 'x')
					continue;
				int currCost = costs[i].charAt(j) - '0';
				// calculate cost with the prev beging upwards first
				if (i > 0) {
					dp[i][j][UP] = dp2[i - 1][j] + currCost; 
				}
				// calculate cost with the prev being left
				if (j > 0)
					dp[i][j][LEFT] = Math.max(dp[i][j - 1][LEFT], dp[i][j - 1][UP]) + currCost;
			}
			
			if (i > 0) {
//				dp[i][m - 1][RIGHT] = dp2[i - 1][m - 1]; // not needed, since each time calculate dp[i][j][RIGHT] would consider dp[i][j + 1][UP]
				for (int j = m - 2; j >= 0; j--) {
					if (costs[i].charAt(j) == 'x')
						continue;
					// calculate cost with the prev being right
					dp[i][j][RIGHT] = Math.max(dp[i][j + 1][RIGHT], dp[i][j + 1][UP]) + costs[i].charAt(j) - '0';
				}
			}
			for (int j = 0; j < m; j++) {
				dp2[i][j] = Math.max(dp[i][j][UP], Math.max(dp[i][j][LEFT], dp[i][j][RIGHT]));
			}
		}
		int maxCost = 0;
		for (int j = 0; j < m; j++) {
			if (costs[n - 1].charAt(j) != 'x') {
				maxCost = Math.max(maxCost, dp2[n - 2][j] + costs[n - 1].charAt(j) - '0');
			}
		}
		return maxCost;
	}
	
	// time: O(n*m*m)
	// space: O(n*m*m)
	public int play3(String[] costs) {
		int n = costs.length;
		int m = costs[0].length();
		// dp[i][out][in]: max cost when arrived in row i from col 'in' in row i - 1, and go to row i + 1 from 'out'
		int[][][] dp = new int[n][m][m];
		for (int[][] a2 : dp) {
			for (int[] a1: a2) {
				Arrays.fill(a1, Integer.MIN_VALUE);
			}
		}
		dp[0][0][0] = 0;
		// dp2[i][j]: max cost when go out of row i from col j
		int[][] dp2 = new int[2][m];
		for (int[] a1: dp2) {
			Arrays.fill(a1, Integer.MIN_VALUE);
		}
		dp2[0][0] = 0;
		// process row 0, each cell in row 0 can only be arrived from cell[0][0]
		for (int out = 1; out < m; out++) {
			if (costs[0].charAt(out) != 'x') {
				dp[0][out][0] = dp[0][out - 1][0] + costs[0].charAt(out) - '0';
				dp2[0][out] = dp[0][out][0];
			} else {
				break;
			}
		}
		// process the rows 1 --- n - 2
		for (int i = 1; i < n - 1; i++) {
			for (int out = 0; out < m; out++) {
				if (costs[i].charAt(out) == 'x')
					continue;
				// c[beg][end]: cost from cell[i][beg] to cell[i][end]
				int[][] c = new int[m][m];
				for (int beg = 0; beg < m; beg++) {
					c[beg][beg] = costs[i].charAt(beg) == 'x' ? Integer.MIN_VALUE : costs[i].charAt(beg) - '0';
					for (int end = beg + 1; end < m; end++) {
						if (c[beg][end - 1] == Integer.MIN_VALUE || costs[i].charAt(end) == 'x') {
							c[beg][end] = Integer.MIN_VALUE;
						} else {
							c[beg][end] = c[beg][end - 1] + costs[i].charAt(end) - '0';
						}
						c[end][beg] = c[beg][end];
					}
				}
				int maxCost = Integer.MIN_VALUE; // max cost when go out at 'out'
				for (int in = 0; in < m; in++) {
					if (dp2[(i - 1) % 2][in] != Integer.MIN_VALUE && c[in][out] != Integer.MIN_VALUE) { // check if reachable
						dp[i][out][in] = dp2[(i - 1) % 2][in] + c[in][out];
						maxCost = Math.max(maxCost, dp[i][out][in]);
					}
				}
				dp2[i % 2][out] = maxCost;
//				System.out.printf("dp2[%d][%d]=%d\n", i, out, dp2[i][out]);
			}
		}
		// process the last row
		int maxCost = 0;
		for (int out = 0; out < m; out++) {
			if (costs[n - 1].charAt(out) != 'x')
				maxCost = Math.max(maxCost, dp2[(n - 2) % 2][out] + costs[n - 1].charAt(out) - '0');
		}
		
		return maxCost;
	}
}
