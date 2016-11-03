package dp;

import java.util.Arrays;

public class ColorfulCupcakesDivTwo {

	public static void main(String[] args) {
		ColorfulCupcakesDivTwo obj = new ColorfulCupcakesDivTwo();
		System.out.println(obj.countArrangements1("ABCAB"));
//		System.out.println(obj.countArrangements("BCBABBACBABABCCCCCAABBAACBBBBCBCAAA"));
	}
	
	
	// wrong algorithm, 三位dp数组不能唯一的表示一个状态，比如在例子“ABCAB”中，求dp[3][0][2]， prev=A, pos0=A时会用到dp[4][0][1]
	// 前面计算过prev=C, first=A的dp[4][0][1]，实际上不能拿来用的，也就是说dp状态中必须有变量表示prev和pos0的color
	private int N;
	private int[][][] dp; // dp[i][a][b]: how many ways with a 'A' and b 'B' and N - (a+b) 'C' for the i to N -1 cupcakes, inclusively 
	private final int MOD = 1000000007;
	public int countArrangements1(String cupcakes){
		this.N = cupcakes.length();
		dp = new int[N][51][51];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		int a = 0, b = 0, c = 0;
		for (int i = 0; i < N; i++) {
			char color = cupcakes.charAt(i);
			if (color == 'A')
				a++;
			else if (color == 'B')
				b++;
			else 
				c++;
		}
		long res = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			int[] availableColors= {a, b, c};
			if (availableColors[i] > 0) {
				availableColors[i]--;
				sb.append((char)(i + 'A'));
				res += recur(1, availableColors, i, i, sb);
//				System.out.printf("i=%d, res=%d\n", i, res);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		res %= MOD;
		return (int)res;
	}
	
	private int recur(int i, int[] colors, int prevColor, int pos0Color, StringBuilder sb) {
		int a = colors[0];
		int b = colors[1];
		int c = colors[2];
//		System.out.printf("i=%d, a=%d, b=%d, c=%d, prevColor=%d, pos0Color=%d\n", i, a, b, c, prevColor, pos0Color);
		if (i == N) {
			if (a == 0 && b == 0 && c == 0) {
//				System.out.println(sb);
				return 1;
			} else {
				return 0;
			}
		}
		
		if (dp[i][a][b] != -1) {
			return dp[i][a][b];
		}
		
		long res = 0;
		for (int color = 0; color < 3; color++) {
			if (colors[color] > 0 && color != prevColor && (i < N - 1 || color != pos0Color)) {
				int[] colorsNew = {a, b, c};
				colorsNew[color]--; 
				sb.append((char)(color + 'A'));
				res += recur(i + 1, colorsNew, color, pos0Color, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		res %= MOD;
		dp[i][a][b] = (int)res;
		System.out.printf("dp[%d][%d][%d]=%d, a=%d, b=%d, c=%d, prev=%c, first=%c\n", i, a, b, dp[i][a][b], a, b, c,
				(char)(prevColor + 'A'), (char)(pos0Color + 'A'));
		return dp[i][a][b];
	}
	
	private int[][][][][] memo;
	private boolean[][][][][] visited;
	private int As, Bs, Cs;
	public int countArrangements(String cupcakes){
		for (int i = 0; i < cupcakes.length(); i++) {
			char color = cupcakes.charAt(i);
			if (color == 'A') As++;
			if (color == 'B') Bs++;
			if (color == 'C') Cs++;
		}
		memo = new int[As+1][Bs+1][Cs+1][3][3];
		visited = new boolean[As+1][Bs+1][Cs+1][3][3];
		int res = 0;
		if (As > 0) res += Do(1, 0, 0, 0, 0); res %= MOD;
		if (Bs > 0) res += Do(0, 1, 0, 1, 1); res %= MOD;
		if (Cs > 0) res += Do(0, 0, 1, 2, 2); res %= MOD;
		return res;
	}
	private int Do(int a, int b, int c, int first, int last) {
		if (a + b + c == As + Bs + Cs) {
			return first != last ? 1 : 0;
		}
		if (visited[a][b][c][first][last]) return memo[a][b][c][first][last];
		long res = 0;
		if (last == 0) {
			if (b < Bs) res += Do(a, b + 1, c, first, 1); res %= MOD;
			if (c < Cs) res += Do(a, b, c + 1, first, 2); res %= MOD;
		} else if (last == 1) {
			if (a < As) res += Do(a + 1, b, c, first, 0); res %= MOD;
			if (c < Cs) res += Do(a, b, c + 1, first, 2); res %= MOD;
		} else if (last == 2) {
			if (a < As) res += Do(a + 1, b, c, first, 0); res %= MOD;
			if (b < Bs) res += Do(a, b + 1, c, first, 1); res %= MOD;
		}
		memo[a][b][c][first][last] = (int) res;
		visited[a][b][c][first][last] = true;
		return (int) res;
	}
	
}
