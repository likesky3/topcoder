package dp;

import java.util.Arrays;

public class BracketSequenceDiv2 {

	public static void main(String[] args) {
		BracketSequenceDiv2 obj = new BracketSequenceDiv2();
		System.out.println(obj.count("()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()"));
	}
	
	public int count(String s) {
		this.s = s;
		this.n = s.length();
		this.memo = new int[51][101];
		for (int i = 0; i <= 50; i++) {
			Arrays.fill(memo[i], -1);
		}
		int res = recur(0, 0);
//		for (int i = 0; i <= n; i++) {
//			for (int j = 0; j <=n; j++) {
//				System.out.printf("memo[%d][%d]=%d\t", i, j, memo[i][j]);
//			}
//			System.out.println();
//		}
		return res - 1;
	}
	private int[][] memo;
	private int n;
	private String s;
	private final int MOD = 1000000007;
	private int recur(int o, int i) {
		if (memo[o][i] != -1)
			return memo[o][i];
		long res = 0;
		if (i == n) {
			// we finished the string
			res = o == 0 ? 1 : 0;
		} else {
			if (o == 0) {
				res = 1;
			}
			// open a new (
			if (o + 1 <= n / 2) {
				int j = findNext(i, '(');
				if (j < n)
					res += recur(o + 1, j + 1);
			}
			// close a (
			if (o != 0) {
				int j = findNext(i, ')');
				if (j < n)
					res += recur(o - 1, j + 1);
			}
			res = res % MOD;
		}
		memo[o][i] = (int)res;
		return (int)res;
	}
	private int findNext(int i, char c) {
		while (i < n && s.charAt(i) != c) {
			i++;
		}
		return i;
	}
}
