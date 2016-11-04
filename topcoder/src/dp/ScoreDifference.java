package dp;
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
	
	private int[] memo;
	private int[][] board;
	private final int MIN = -1000000;
	public int maximize(String[] board) {
		memo = new int[1<<16];
		Arrays.fill(memo, MIN);
		this.board = new int[4][4];
		for (int i = 0; i < 4; i++) {
			String[] tmp = board[i].split(" ");
			for (int j = 0; j < 4; j++) {
				this.board[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		return recur(0);
	}
	
	private int recur(int used) {
		if (used == (1 << 16) - 1)
			return 0;
		if (memo[used] != MIN)
			return memo[used];
		int res = MIN;
		for (int remove = 0; remove < 16; remove++) {
			if (((1<<remove) & used) == 0 && isValidMove(remove, used, new boolean[16])) {
				res = Math.max(res, board[remove / 4][remove % 4] - recur(used | (1 << remove)));
			}
		}
		memo[used] = res;
		return res;
	}
	
	private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	private boolean isValidMove(int remove, int used, boolean[] visited) {
		//System.out.printf("remove=%d, used=%s\n", remove, Integer.toBinaryString(used));
		if (visited[remove])
			return false;
		visited[remove] = true;
		int row = remove / 4;
		int col = remove % 4;
		for (int i = 0; i < 4; i++) {  
			int row2 = row + dy[i];
			int col2 = col + dx[i];
			if (row2 < 0 || row2 == 4 || col2 < 0 || col2 == 4) 
				return true;
			int next = row2 * 4 + col2;
			if ((used & (1 << next)) == 0)
				continue;
			if (isValidMove(next, used, visited))
				return true;
		}
		return false;
	}
}
