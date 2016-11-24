package dp;

import java.util.Arrays;

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
	
	public String check2(String[] field, String[] moves) {
		// prepare information
		StringBuilder tmp = new StringBuilder();
		for (String s : moves) {
			tmp.append(s);
		}
		allMoves = tmp.toString().toCharArray();
		this.M = allMoves.length;
		int rowNum = field.length;
		int colNum = field[0].length(); 
		board = new char[rowNum][colNum];
		for (int i = 0; i < rowNum; i++) {
			board[i] = field[i].toCharArray();
		}
		memo = new int[M + 1][rowNum][colNum];
		for (int[][] a : memo) {
			for (int[] b : a) {
				Arrays.fill(b, -1);
			}
		}
		// search for the init position of Alice and Bob
		int r1 = 0, c1 = 0, r2 = 0, c2 = 0;
		boolean findAlice = false, findBob = false;
		for (int i = 0; i < rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				if (board[i][j] == 'A') {
					findAlice = true;
					r1 = i;
					c1 = j;
				} else if (board[i][j] == 'B') {
					findBob = true;
					r2 = i;
					c2 = j;
				}
				if (findAlice && findBob)
					break;
			}
			if (findAlice && findBob)
				break;
		}
		// start recurrence
		return canAliceWin(0, r1, c1, r2, c2) ? "Alice wins" : "Bob wins";
		
	}
	
	private char[] allMoves;
	private char[][] board;
//	private int M;
	private int[][][] memo;
	private int[] dx = {1, -1, 0, 0};
	private int[] dy = {0, 0, 1, -1};
	private boolean canAliceWin(int i, int r1, int c1, int r2, int c2) {
		if (memo[i][r2][c2] != -1) {
			return memo[i][r2][c2] == 1 ? true : false;
		}
		if (i == M) {
			return false;
		}
		int[] aliceNewPos = doMove(i, r1, c1); // in each turn, Alice go first, aliceNewPos is the position after Alice take one step
		if (aliceNewPos[0] == r2 && aliceNewPos[1] == c2) {
			memo[i][r2][c2] = 1;
			return true;
		}
		boolean aliceWin = true;
		for (int k = 0; k < dx.length; k++) {
			int bobRow = r2 + dy[k];
			int bobCol = c2 + dx[k];
			if (bobCol < 0 || bobCol >= board[0].length || bobRow < 0 || bobRow >= board.length || board[bobRow][bobCol] == '#')
				continue;
			if (bobRow == aliceNewPos[0] && bobCol == aliceNewPos[1]) // Alice would win if do such move
				continue;
			if (!canAliceWin(i + 1, aliceNewPos[0], aliceNewPos[1], bobRow, bobCol)) {
				aliceWin = false;
				break;
			}
		}
		
		memo[i][r2][c2] = aliceWin ? 1 : 2;
		return aliceWin;
	}
	
	private int[] doMove(int i, int row, int col) {
		char direction = allMoves[i];
		int nRow = row, nCol = col;
		switch (direction) {
		case 'U': nRow--; break;
		case 'D': nRow++; break;
		case 'L': nCol--; break;
		case 'R': nCol++; break;
		default:
		}
		return new int[]{nRow, nCol};
	}
}
