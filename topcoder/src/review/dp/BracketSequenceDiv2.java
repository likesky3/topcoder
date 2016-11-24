package review.dp;

import java.util.Arrays;

public class BracketSequenceDiv2 {

	public static void main(String[] args) {
		BracketSequenceDiv2 obj = new BracketSequenceDiv2();
		System.out.println(obj.count("(())())"));
	}
	
	public int count(String s) {
		this.s = s;
		n = s.length();
		memo = new int[n / 2 + 1][n + 1];
		for (int[] m : memo) {
			Arrays.fill(m, -1);
		}
		return recur(0, 0) - 1;
	}
	private String s;
	private int n;
	private int[][] memo;
	private int recur(int o, int i) {
		if (memo[o][i] != -1)
			return memo[o][i];
		if (i == n)
			return o == 0 ? 1 : 0;
		int result = 0;
		if (o == 0) {
			result = 1;
		}
		// open a new (
		if (o < n / 2) {
			int j = findNext(i, '(');
			if (j < n)
				result += recur(o + 1, j + 1);
		}
		// close a (
		if (o > 0) {
			int j = findNext(i, ')');
			if (j < n)
				result += recur(o - 1, j + 1);
		}
		return memo[o][i] = result;
	}
	
	private int findNext(int i, char c) {
		while (i < n) {
			if (s.charAt(i) == c)
				return i;
			i++;
		}
		return n;
	}

}
