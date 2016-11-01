package dp;

import java.util.Arrays;

public class MarblesRegroupingHard {

	public static void main(String[] args) {
		MarblesRegroupingHard obj = new MarblesRegroupingHard();
		String[] boxes = {"77 97","8 0"};
		System.out.println(obj.minMoves(boxes));
	}
	
	public int minMoves(String[] boxes) {
		this.N = boxes.length;
		String[] box0 = boxes[0].split(" ");
		this.M = box0.length;
		this.dp = new int[N][1<<M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], -1);
		}
		this.board = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] tmp = boxes[i].split(" ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		this.total = new int[M];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				total[i] += board[j][i];
			}
		}
		return opt(N - 1, (1<<M) - 1);
	}
	
	private int[][] board;
	private int[] total;
	private int[][] dp;
	private int N; // number of boxes
	private int M; // number of colors
	private final int MAX = 6000000;
	private int opt(int upto, int unassigned) {
		if (upto == -1) {
			return unassigned == 0 ? 0 : MAX;
		}
		if (dp[upto][unassigned] != -1)
			return dp[upto][unassigned];
		// don't assgin the upto-th box to any color
		int res = opt(upto - 1, unassigned);
		// assign the upto-th box to some color
		for (int i = 0; i < M; i++) {
			if ((unassigned & (1 << i)) > 0) { 
				res = Math.min(res, total[i] - board[upto][i] + opt(upto - 1, unassigned ^ (1 << i)));
			}
		}
		dp[upto][unassigned] = res;
		return res;
	}
}
