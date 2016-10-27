package dp;

import java.util.Arrays;

public class VocaloidsAndSongs {

	public static void main(String[] args) {
		VocaloidsAndSongs obj = new VocaloidsAndSongs();
		System.out.println(obj.count(3, 1, 1, 1));
	}
	
	private final int MOD = 1000000007;
	private final int N = 51;
	private int[][][][] memo;
	public int count(int S, int gumi, int ia, int mayu) {
		memo = new int[N][N][N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					Arrays.fill(memo[i][j][k], -1);
				}
			}
		}
		return recur(S, gumi, ia, mayu);
	}

	private int recur(int s, int a, int b, int c) {
//		System.out.printf("s=%d, a=%d, b=%d, c =%d, memo=%d\n", s, a, b,c, memo[s][a][b][c]);
		int res = memo[s][a][b][c];
		if (res != -1) return res;
		if (s == 0)
			return a == 0 && b == 0 && c == 0 ? 1 : 0;
		res = 0;
		for (int na = a; na >= a - 1 && na >=0; na--) {
			for (int nb = b; nb >= b - 1 && nb >= 0; nb--) {
				for (int nc = c; nc >= c - 1 && nc >= 0; nc--) {
					if (na != a || nb != b || nc != c) {
						res = (res + recur(s - 1, na, nb, nc)) % MOD;
					}
				}
			}
		}
		memo[s][a][b][c] = res;
		return res;
	}

}
