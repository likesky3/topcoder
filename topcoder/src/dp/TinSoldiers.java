package dp;

import java.util.Arrays;

public class TinSoldiers {

	public static void main(String[] args) {
		TinSoldiers obj = new TinSoldiers();
		int[] rankCounts = {2, 2, 1};
		System.out.println(obj.lineUps(rankCounts));
		System.out.println(obj.lineUps2(rankCounts));
	}
	
	public int lineUps(int[] rankCounts) {
		int totalSoldiers = 0;
		for (int i : rankCounts) {
			totalSoldiers += i;
		}
		int[][] comb = new int[totalSoldiers + 1][totalSoldiers + 1];
		for (int i = 1; i <= totalSoldiers; i++) {
			comb[i][0] = comb[i][i] = 1;
			for (int j = 1; j < i; j++) {
				comb[i][j] = comb[i - 1][j] + comb[i - 1][j - 1];
			}
		}
		long permNum = 1;
		int n = totalSoldiers;
		for (int i : rankCounts) {
			permNum *= comb[n][i];
			n -= i;
		}
//		System.out.printf("permNum=%d\n", permNum);
		int palinNum = getPalinNum(rankCounts);
		return (int) ((permNum - palinNum) / 2 + palinNum);
	}
	
	private int getPalinNum(int[] r) {
		boolean allLined = true;
		int notLinedNum = 0; 
		for (int n : r) {
			if (n > 0) { 
				allLined = false;
				notLinedNum++;
				if (notLinedNum > 1)
					break;
			}
		}
		if (allLined || notLinedNum == 1) 
			return 1;
		int res = 0;
		for (int i = 0; i < r.length; i++) {
			if (r[i] >= 2) {
				int[] nr = Arrays.copyOf(r, r.length);
				nr[i] -= 2;
				res += getPalinNum(nr);
			}
		}
		return res;
	}
	
	// Version 2, ref others
	public int lineUps2(int[] rankCounts) {
		// get total number of soldiers
		int T = 0;
		int odds = 0;
		for (int i : rankCounts) {
			T += i;
			odds += i % 2;
		}
		// prepare combination[][]
		long[][] comb = new long[T + 1][T + 1];
		comb[0][0] = 1;
		for (int i = 1; i <= T; i++) {
			comb[i][0] = comb[i][i] = 1;
			for (int j = 1; j < i; j++) {
				comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
			}
		}
		// get total number of permutations, not ignore reflection
		long perm = 1;
		int tmpT = T;
		for (int i = 0; i < rankCounts.length; i++) {
			perm *= comb[tmpT][rankCounts[i]];
			tmpT -= rankCounts[i];
		}
		// get number of palindrome 
		long palin = 0;
		if (T % 2 == odds) {
			palin = 1;
			tmpT = T / 2;
			for (int i = 0; i < rankCounts.length; i++) {
				System.out.printf("i=%d, comb[%d][%d]=%d\n", i, tmpT, rankCounts[i] / 2, comb[tmpT][rankCounts[i] / 2]);
				palin *= comb[tmpT][rankCounts[i] / 2];
				tmpT -= rankCounts[i] / 2;
			}
		}
		System.out.printf("perm=%d, palin=%d, T=%d, odds=%d\n", perm, palin, T, odds);
		return (int)((perm + palin) / 2);
		
	}
}
