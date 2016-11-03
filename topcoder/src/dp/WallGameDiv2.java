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
}
