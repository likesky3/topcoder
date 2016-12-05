package dp;

import java.util.*;

public class ChangingSounds {

	public static void main(String[] args) {
		int[] changeIntervals = {1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 89, 9, 5, 34, 2, 34, 2, 42, 23, 23, 234, 234, 234, 5, 3, 3, 2, 4, 3, 34};
		int beginLevel = 40;
		int maxLevel = 301;
		ChangingSounds obj = new ChangingSounds();
		System.out.println(obj.maxFinal(changeIntervals, beginLevel, maxLevel));
		System.out.println(obj.maxFinal2(changeIntervals, beginLevel, maxLevel));
	}
	
	public int maxFinal(int[] changeIntervals, int beginLevel, int maxLevel) {
		int N = changeIntervals.length;
		boolean[][] canHave = new boolean[N + 1][maxLevel + 1];
		canHave[0][beginLevel] = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= maxLevel; j++) {
				if (canHave[i][j]) {
					if (j + changeIntervals[i] <= maxLevel) {
						canHave[i + 1][j + changeIntervals[i]] = true;
					}
					if (j - changeIntervals[i] >= 0) { 
						canHave[i + 1][j - changeIntervals[i]] = true;
					}
				}
			}
		}
		int finalLevel = -1;
		for (int i = maxLevel; i >= 0; i--) {
			if (canHave[N][i]) {
				finalLevel = i;
				break;
			}
		}
		return finalLevel;
	}

	public int maxFinal2(int[] changeIntervals, int beginLevel, int maxLevel) {
		int N = changeIntervals.length;
		// Not wise to use ArrayList here, since there would be so many duplicates that may even cause out of memory
//		List<Integer> prevReachables= new ArrayList<>(maxLevel);
//		List<Integer> currReachables = new ArrayList<>(maxLevel);
		Set<Integer> prevReachables= new HashSet<>(maxLevel);
		Set<Integer> currReachables = new HashSet<>(maxLevel);
		prevReachables.add(beginLevel);
		for (int i = 0; i < N; i++) {
			boolean okToContinue = false;
			for (int level : prevReachables) {
				if (level + changeIntervals[i] <= maxLevel) {
					currReachables.add(level + changeIntervals[i]);
					okToContinue = true;
				}
				if (level - changeIntervals[i] >= 0) {
					currReachables.add(level - changeIntervals[i]);
					okToContinue = true;
				}
			}
			if (!okToContinue)
				return -1;
			prevReachables.clear(); 
			prevReachables.addAll(currReachables);
			currReachables.clear();
		}
		int result = 0;
		for (int level : prevReachables) {
			result = Math.max(result, level);
		}
		return result;
	}
}
