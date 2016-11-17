package dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MutalisEasy {

	public static void main(String[] args) {
		MutalisEasy obj = new MutalisEasy();
		int[] x = {2};
		System.out.println(obj.minimalAttacks(x));
	}

	public int minimalAttacks(int[] x) {
		dp = new HashSet<>();
		recur(x, 0);
		return minNum;
	}
	
	private int[][] delta = {{9, 3, 1}, {9, 1, 3}, {3, 1, 9}, {3, 9, 1}, {1, 3, 9}, {1, 9, 3}};
	private int minNum = Integer.MAX_VALUE;
	private Set<int[]> dp;
	// would timeout
	private void recur(int[] x, int attackNum) {
		if (dp.contains(x)) // since each int[] x is a new object, the memo doesn't work
			return;
		boolean damagedAll = true;
		for (int i : x) {
			if (i > 0) damagedAll = false;
			System.out.printf("%d\t", i);
		}
		System.out.println("attackNum=" + attackNum);
		if (damagedAll) {
			minNum = Math.min(minNum, attackNum);
			return;
		}
		for (int i = 0; i < delta.length; i++) {
			int[] x2 = new int[x.length];
			for (int j = 0; j < x.length; j++) {
				x2[j] = Math.max(x[j] - delta[i][j], 0);
			}
			recur(x2, attackNum + 1);
		}
		dp.add(x);
	}
	
	private static final int INF = 1000;
	private int[][] perm = new int[][]{{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};
	private int[][][] cache = new int[61][61][61];
	public int minimalAttacks2(int[] x) {
		for (int[][] arr2 : cache) {
			for (int[] arr1 : arr2)
				Arrays.fill(arr1, INF);
		}
		cache[0][0][0] = 0;
		int[] input = new int[3];
		for (int i = 0; i < x.length; i++)
			input[i] = x[i];
		return dp(input);
	}
	private int dp(int[] x) {
		cleanUp(x);
		int a = x[0], b = x[1], c = x[2];
		if (cache[a][b][c] != INF)
			return cache[a][b][c];
		
		int result = INF;
		for (int[] p : perm) {
			result = Math.min(result, 1 + dp(new int[]{a - p[0], b - p[1], c - p[2]}));
		}
		return cache[a][b][c] = result;
	}
	private void cleanUp(int[] x) {
		x[0] = Math.max(0, x[0]);
		x[1] = Math.max(0, x[1]);
		x[2] = Math.max(0, x[2]);
//		Arrays.sort(x);
	}
}
