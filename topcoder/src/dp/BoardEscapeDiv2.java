package dp;

public class BoardEscapeDiv2 {
	public String findWinner(String[] s, int k) {
		// build board[][]
		char[][] board = new char[s.length][s[0].length()];
		int x = -1, y = -1;
		for (int i = 0; i < s.length; i++) {
			board[i] = s[i].toCharArray();
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'T') {
					x = i;
					y = j;
				}
			}
		}
		memo = new int[51][51][101];
		boolean firstPlayerWin = dfs(board, k, x, y);
		return firstPlayerWin ? "Alice" : "Bob";
	}
	
	private int[][][] memo;
	private int[] deltax = {0, 0, 1, -1};
	private int[] deltay = {1, -1, 0, 0};
	private boolean dfs(char[][] board, int k, int x, int y) {
		if (k == 0)
			return false;
		if (memo[x][y][k] != 0)
			return memo[x][y][k] == 1 ? true : false;
		for (int i = 0; i < 4; i++) {
			int nx = x + deltax[i];
			int ny = y + deltay[i];
			if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == '#')
				continue;
			if (board[nx][ny] == 'E') {
				memo[x][y][k] = 1;
				return true;
			}
			board[x][y] = '.';
			if (!dfs(board, k - 1, nx, ny)) {
				memo[x][y][k] = 1;
				return true;
			}
			board[x][y] = 'T';
		}
		memo[x][y][k] = -1;
		return false;
	}
	
	public static void main(String[] args) {
		String[] s = {".#.", "#T#", ".#."};
		BoardEscapeDiv2 obj = new BoardEscapeDiv2();
		System.out.println(obj.findWinner(s, 99));
		
		s = new String[]{"TE"};
		System.out.println(obj.findWinner(s, 3));
		
		s = new String[] {"T.#", "#.E"};
		System.out.println(obj.findWinner(s, 3));
		
		s = new String[] {"#E...",
						   "#...E",
						   "E.T#.",
							"..#.."};
		System.out.println(obj.findWinner(s, 13));
	}
}
