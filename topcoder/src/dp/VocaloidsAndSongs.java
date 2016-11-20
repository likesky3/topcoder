package dp;

import java.util.Arrays;

public class VocaloidsAndSongs {

	public static void main(String[] args) {
		VocaloidsAndSongs obj = new VocaloidsAndSongs();
		System.out.println(obj.count(3, 3, 1, 1));
		System.out.println(obj.count2(3, 3, 1, 1));
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

	public int count2(int s, int gumi, int ia, int mayu) {
		cache =new int[s + 1][gumi + 1][ia + 1][mayu + 1];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				for (int k = 0; k < cache[0][0].length; k++) {
					Arrays.fill(cache[i][j][k], -1);
				}
			}
		}
		cache[s][0][0][0] = 1;
		this.s = s;
		return recur(0, new int[]{gumi, ia, mayu});
	}
	
	private int[][][][] cache;
	private int s;
	private int recur(int i, int[] t) {
		int a = t[0], b = t[1], c = t[2];
//		System.out.printf("input: %d %d %d %d\n", i, a, b, c);
		if (cache[i][a][b][c] != -1) {
			return cache[i][a][b][c];
		}
		if (i == s) {
			return a == 0 && b == 0 && c == 0 ? 1 : 0;
		}
		long res = 0;
		for (int mask = 1; mask <= 7; mask++) {
			int[] t2 = new int[] {a, b, c};
			boolean ok = true;
			for (int k = 0; k < 3; k++) {
				if ((mask & (1 << k)) > 0) {
					t2[k]--;
					if (t2[k] < 0) {
						ok = false;
						break;
					}
				}
			}
			if (ok)
				res += recur(i + 1, t2);
		}
		res %= MOD;
		cache[i][a][b][c] = (int)res ;
		return (int)res;
	}
}
