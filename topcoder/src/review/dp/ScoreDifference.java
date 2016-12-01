package review.dp;

import java.util.Arrays;

public class ScoreDifference {

	public static void main(String[] args) {
		ScoreDifference obj = new ScoreDifference();
		String[] board = {"6 8 1 16",
				 "10 15 9 3",
				 "2 5 7 14",
				 "13 12 11 4"};
		System.out.println(obj.maximize(board));
	}
	
	public int maximize(String[] board) {
		// parse the board information
		scores = new int[4][4];
		for (int i = 0; i < 4; i++) {
			String[] tmp = board[i].split(" ");
			for (int j = 0; j < 4; j++) {
				scores[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		dp = new int[1 << 16];
		Arrays.fill(dp, -1);
		return recur((1 << 16) - 1); // initially all cells are in the board
	}
	
	private int[][] scores;
	private int[] dp;
	private final int INF = -1000;
	private int recur(int remains) { // if the i-th bit in remains is 1, it says the i-th cell is still in the board
		if (dp[remains] != -1)
			return dp[remains];
		if (remains == 0)
			return 0;
		int res = INF;
		for (int i = 0; i < 16; i++) { // check each cell
			if ((remains & (1 << i)) > 0 && isValidMove(i, remains, new boolean[16])) {
				res = Math.max(res, scores[i / 4][i % 4] - recur(remains ^ (1 << i)));
			}
		}
		return dp[remains] = res;
	}
	
	int[] dx = {0, 0, 1, -1};
	int[] dy = {1, -1, 0, 0};
	private boolean isValidMove(int move, int remains, boolean[] visited) {
		if (visited[move])
			return false;
		visited[move] = true;
		for (int i = 0; i < 4; i++) {
			int nrow = move / 4 + dx[i];
			int ncol = move % 4 + dy[i];
			if (nrow < 0 || nrow == 4 || ncol < 0 || ncol == 4) // out of the board
				return true;
			int next = nrow * 4 + ncol;
			if ((remains & (1 << next)) > 0) // the cell is still occupied, can not move into it
				continue;
			if (isValidMove(next, remains, visited)) // dfs to check
				return true;
		}
		return false;
	}

}
