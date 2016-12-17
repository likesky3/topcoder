package dp;

public class BombEnemy {

	public static void main(String[] args) {
		char[][] grid = {{'0', 'E', '0', '0'},{'E', '0', 'W', 'E'},{'0', 'E', '0', '0'}};
		BombEnemy obj = new BombEnemy();
		System.out.println(obj.maxKilledEnemies(grid));
	}
	public int maxKilledEnemies(char[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int maxCnt = 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            int[] left = new int[n]; // left[j]: how many enemies can be killed to the left of col j when planted at col j
            for (int j = 1; j < n; j++) {
                if (grid[i][j] != 'W') {
                    left[j] = left[j - 1] + (grid[i][j - 1] == 'E' ? 1 : 0);
//                    System.out.printf("left[%d][%d]=%d\n", i, j, left[j]);
                }
            }
            int[] right = new int[n];
            for (int j = n - 2; j >= 0; j--) {
                if (grid[i][j] != 'W') {
                    right[j] = right[j + 1] + (grid[i][j + 1] == 'E' ? 1 : 0);
//                    System.out.printf("right[%d][%d]=%d\n", i, j, right[j]);
                }
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    dp[i][j] = left[j] + right[j];
                    System.out.printf("dp[%d][%d]=%d\n", i, j, dp[i][j]);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            int[] up = new int[m];
            for (int i = 1; i < m; i++) {
                if (grid[i][j] != 'W') {
                    up[i] = up[i - 1] + (grid[i - 1][j] == 'E' ? 1 : 0);
                }
            }
            int[] down = new int[m];
            for (int i = m - 2; i >= 0; i--) {
                if (grid[i][j] != 'W') {
                    down[i] = down[i + 1] + (grid[i + 1][j] == 'E' ? 1 : 0);
                }
            }
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == '0') {
                    dp[i][j] += up[i] + down[i];
                    maxCnt = Math.max(dp[i][j], maxCnt);
                    System.out.printf("dp[%d][%d]=%d\n", i, j, dp[i][j]);
                }
            }
        }
        return maxCnt;
    }

}
