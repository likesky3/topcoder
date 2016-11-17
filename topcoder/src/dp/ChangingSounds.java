package dp;

public class ChangingSounds {

	public static void main(String[] args) {
		
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
}
