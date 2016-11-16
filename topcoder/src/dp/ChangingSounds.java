package dp;

public class ChangingSounds {

	public static void main(String[] args) {
		
	}
	
	public int maxFinal(int[] changeIntervals, int beginLevel, int maxLevel) {
		int N = changeIntervals.length;
		int[] high = new int[N + 1];
		int[] low = new int[N + 1];
		high[0] = beginLevel;
		low[0] = beginLevel;
		for (int i = 1; i <= N; i++) {
			int opt1 = high[i - 1] + changeIntervals[i - 1];
			if (opt1 > maxLevel) opt1 = -1;
			int opt2 = low[i - 1] + changeIntervals[i - 1];
			if (opt2 > maxLevel) opt2 = -1;
			int opt3 = high[i - 1] - changeIntervals[i - 1];
			if (opt3 < 0) opt3 = -1;
			int opt4 = low[i - 1] - changeIntervals[i - 1];
			if (opt4 < 0) opt4 = -1;
			high[i] = Math.max(opt1, Math.max(opt2, opt3));
			low[i] = Math.min(opt4, Math.min(opt2, opt3));
		}
		return high[N];
	}

	public int maxFinal2(int[] changeIntervals, int beginLevel, int maxLevel) {
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
}
