package review.dp;

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
	
	public String check(String[] field, String[] moves) {
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
	private int M;
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
