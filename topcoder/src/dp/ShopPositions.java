package dp;

import java.util.Arrays;

public class ShopPositions {
	private int[][][] dp;
	private int n;
	private int m;
	private int[] C;
	public int maxProfit(int n, int m, int[] c) {
		this.n = n;
		this.m = m;
		this.C = c;
		dp = new int[31][31][31];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		int res = 0;
		for (int a = 0; a <= m; a++) {
			for (int b = 0; b <= m; b++) {
				int p = 0;
				if (a != 0) {
					p = a * C[0 * 3 * m + (a + b) - 1];
				}
				if (n >= 2)
					p += f(a, b, 2);
				res = Math.max(res, p);
			}
		}
		return res;
	}
	
	// the max profit we get starting at i with the prev 2 buildings of a and b.
	private int f(int a, int b, int i) {
		if (dp[a][b][i] != -1)
			return dp[a][b][i];
		int res = 0;
		int p = 0;
		if (i == n) {
			if (b != 0)
				p = b * C[(n - 1) * 3 * m + (a + b) - 1];
			res = p;
		} else {
			for (int c = 0; c <= m; c++) {
				p = b * C[(i - 1) * 3 * m + (a + b + c) - 1];
				p += f(b, c, i + 1);
				res = Math.max(res, p);
			}
		}
		dp[a][b][i] = res;
		return res;
	}
	
	public static void main(String[] args) {
		int[] c = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 1, 1, 1, 1, 1};
		ShopPositions obj = new ShopPositions();
		System.out.println(obj.maxProfit(1, 5, c));
		
	}
}
