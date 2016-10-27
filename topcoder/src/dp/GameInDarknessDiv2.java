package dp;

public class GameInDarknessDiv2 {

	public static void main(String[] args) {
		GameInDarknessDiv2 obj = new GameInDarknessDiv2();
		String[] field = {"A.B..",
				 "##.##",
		 "##.##"};
		String[] moves = {"RRDUR"};
		System.out.println(obj.check(field, moves));
	}
	
	private int h, w, M;
	private int[] ax;
	private int[] ay;
	private boolean[][][] visited;
	private String[] field;
	private final int MAX = 2501;
	public String check(String[] field, String[] moves) {
		// init some variables
		h = field.length;
		w = field[0].length();
		StringBuilder allMoves = new StringBuilder();
		for (String move : moves) {
			allMoves.append(move);
		}
		M = allMoves.length();
		ax = new int[MAX];
		ay = new int[MAX];
		visited = new boolean[51][51][2501];
		this.field = field;
		
		// find the start position of Alice and Bob
		int bx = 0, by = 0;
		char[][] board = new char[h][w];
		for (int i = 0; i < h; i++) {
			board[i] = field[i].toCharArray();
			for (int j = 0; j < w; j++) {
				if (board[i][j] == 'A'){
					ax[0] = j;
					ay[0] = i;
				} else if (board[i][j] == 'B') {
					bx = j;
					by = i;
				}
			}
		}
		int[] pos1 = moves(ax[0], ay[0], allMoves.charAt(0));
		if (pos1[0] == bx && pos1[1] == by){
			return "Alice wins";
		}
		ax[0] = pos1[0];
		ay[0] = pos1[1];
		// calculate ax, ay after each turn, so that we know Alice's position at a given time
		for (int i = 1; i < M; i++){
			int[] newpos = moves(ax[i - 1], ay[i - 1], allMoves.charAt(i));
			ax[i] = newpos[0];
			ay[i] = newpos[1];
		}
		// perform the search, find all nodes reachable from (bx, by, 0)
		dfs(bx, by, 0);
		
		for (int i = 0; i <= h; i++) {
			for (int j = 0; j <= w; j++) {
				if (visited[i][j][M - 1])
					return "Bob wins";
			}
		}
		return "Alice wins";
	}
	
	private int[] moves(int x, int y, char move) { 
		int nx = x, ny = y;
		switch (move) {
		case 'U':
			ny--;
			break;
		case 'D':
			ny++;
			break;
		case 'R':
			nx++;
			break;
		case 'L':
			nx--;
			break;
		default:
			break;
		}
		return new int[]{nx, ny};
	}
	
	char[] movesOpt = {'U', 'D', 'R', 'L'};
	private void dfs(int bx, int by, int t) {
		if (!visited[bx][by][t]) {
			visited[bx][by][t] = true;
			// still need to move
			if (t + 1 < M) {
				// for each move option
				for (char c : movesOpt) {
					int[] newpos = moves(bx, by, c);
					int nx = newpos[0];
					int ny = newpos[1];
					// if the move is to a non block cell
					if (0 <= nx && nx < w && 0 <= ny && ny < h && field[ny].charAt(nx) != '#') {
						// if the move not move to Alice's current and next position
						if ((nx != ax[t] || ny != ay[t]) && (nx != ax[t + 1] || ny != ay[t + 1])) {
							dfs(nx, ny, t + 1);
						}
					}
				}
			}
		}
	}
}
