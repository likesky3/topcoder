package review.dp;

import java.util.Arrays;

public class MarblesRegroupingHard {

	public static void main(String[] args) {
		MarblesRegroupingHard obj = new MarblesRegroupingHard();
		String[] boxes = {"77 97","8 0"};
		System.out.println(obj.minMoves(boxes));
	}
	
	public int minMoves(String[] boxes) {
		N = boxes.length;
		String[] box0 = boxes[0].split(" ");
		M = box0.length;
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] tmp = boxes[i].split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		total = new int[M];
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				total[j] += board[i][j];
			}
		}
		dp = new int[N][(1 << M)];
		for (int[] p : dp) {
			Arrays.fill(p, -1);
		}
		return recur(N - 1, (1 << M) - 1);
	}
	
	private int[][] board;
	private int[] total;
	private int[][] dp;
	private int N; // num of boxes
	private int M; // num of colors
	private final int INF = 50001;
	
	private int recur(int upto, int unassigned) {
		if (upto == -1) {
			return unassigned == 0 ? 0 : INF;
		}
		if (dp[upto][unassigned] != -1) {
			return dp[upto][unassigned];
		}
		// not assign any color to the upto-th box
		int res = recur(upto - 1, unassigned);
		// assign some color to the upto-th box
		for (int i = 0; i < M; i++) {
			if ((unassigned & (1 << i)) > 0) { // find a color which has not been assigned yet
				res = Math.min(res, total[i] - board[upto][i] + recur(upto - 1, unassigned ^ (1 << i)));
			}
		}
		dp[upto][unassigned] = res;
		return res;
	}

}
