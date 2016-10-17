package dp;

import java.util.Arrays;

public class UnsealTheSafe {
	// Version 1, by 00
	public long countPasswords(int N) {
		boolean[][] isNeighbor = getisNeighbor();
		long[][] dp = new long[N + 1][10]; // dp[i][j] : number of passwords with length i and the last digit is j;
		Arrays.fill(dp[1], 1);
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (isNeighbor[j][k]) {
						dp[i][j] += dp[i - 1][k];
					}
				}
			}
		}
		long count = 0;
		for (int i = 0; i < 10; i++) {
			count += dp[N][i];
		}
		return count;
	}
	
	boolean[][] getisNeighbor() {
		boolean[][] isNeighbor = new boolean[10][10];
		isNeighbor[0][7] = true;
		isNeighbor[1][2] = true;
		isNeighbor[1][4] = true;
		isNeighbor[2][1] = true;
		isNeighbor[2][3] = true;
		isNeighbor[2][5] = true;
		isNeighbor[3][2] = true;
		isNeighbor[3][6] = true;
		isNeighbor[4][1] = true;
		isNeighbor[4][5] = true;
		isNeighbor[4][7] = true;
		isNeighbor[5][2] = true;
		isNeighbor[5][4] = true;
		isNeighbor[5][6] = true;
		isNeighbor[5][8] = true;
		isNeighbor[6][3] = true;
		isNeighbor[6][5] = true;
		isNeighbor[6][9] = true;
		isNeighbor[7][0] = true;
		isNeighbor[7][4] = true;
		isNeighbor[7][8] = true;
		isNeighbor[8][5] = true;
		isNeighbor[8][7] = true;
		isNeighbor[8][9] = true;
		isNeighbor[9][6] = true;
		isNeighbor[9][8] = true;
		return isNeighbor;
	}
	
	// Version 2, outstanding
	private int[][] adj = new int[][]{{7}, {2, 4}, {1, 3, 5}, {2, 6}, {1, 5, 7}, {2, 4, 6, 8}, {3, 5, 9},
		{0, 4, 8}, {5, 7, 9}, {6, 8}};
	public long countPasswords2(int N) {
		long[] memo = new long[10];
		long[] curr = new long[10];
		Arrays.fill(memo, 1);
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k : adj[j]) {
					curr[j] += memo[k];
				}
			}
			for (int j = 0; j < 10; j++) {
				memo[j] = curr[j];
			}
			Arrays.fill(curr, 0);
		}
		long count = 0;
		for (long c : memo) {
			count += c;
		}
		return count;
	}
	public static void main(String[] args) {
		UnsealTheSafe obj = new UnsealTheSafe();
		System.out.println(obj.countPasswords2(3) == 74);
		System.out.println(obj.countPasswords2(1) == 10);
		System.out.println(obj.countPasswords2(2) == 26);
	}
}
