package review.dp;

import java.util.Arrays;

public class SpellCardsEasy {

	public static void main(String[] args) {
		SpellCardsEasy obj = new SpellCardsEasy();
		int[] level = {1,2,2,3,1,4,2};
		int[] damage = {113,253,523,941,250,534,454};
		System.out.println(obj.maxDamage2(level, damage));
		
		level = new int[]{1,2,1,1,3,2,1};
		damage = new int[]{10,40,10,10,90,40,10};
		System.out.println(obj.maxDamage2(level, damage));
	}
	
	// fail at test #6
	public int maxDamage1(int[] level, int[] damage) {
		int N = level.length;
		int[] dp = new int[N + 1];
		for (int i = N - 1; i >= 0; i--) {
			dp[i] = dp[i + 1];
			if (N - i >= level[i]) {
				dp[i] = Math.max(damage[i] + dp[i + level[i]], dp[i]);
				System.out.printf("dp[%d]=%d\n", i, dp[i]);
			} 
		}
		return dp[0];
	}

	// greedy: fail at level={2, 1, 1}, damage={40, 40, 10}, expect value is 80
	public int maxDamage2(int[] level, int[] damage) {
		N = level.length;
		boolean[] removed = new boolean[N];
		int result = 0;
		for (int i = 0; i < N; i++) {
			int max = -1;
			for (int j = 0; j < N; j++) {
				if (!removed[j] && (max == -1 || damage[j] > damage[max]) 
						&& isValidToRemove(j, level[j], removed)) {
					max = j;
				}
			}
			if (max != -1) {
				result += damage[max];
				int cnt = 0;
				for (int j = max; j < N; j++) {
					if (!removed[j]) {
						removed[j] = true;
						cnt++;
						if (cnt == level[max])
							break;
					}
				}
			} else {
				break;
			}
//			System.out.printf("level[%d]=%d, damage[%d]=%d, result=%d\n", max, level[max], max, damage[max], result);
		}
		return result;
	}
	private int N;
	private boolean isValidToRemove(int curr, int level, boolean[] removed) {
		int i = curr + 1; 
		int cnt = 1;
		while (i < N && cnt < level) {
			if (removed[i++])
				continue;
			cnt++;
		}
		return cnt == level;
	}
	
	public int maxDamage(int[] level, int[] damage) {
		this.n = level.length;
		dp = new int[n + 1][n + 1];
		for (int[] a1 : dp) {
			Arrays.fill(a1, -1);
		}
		this.level = level;
		this.damage = damage;
		return recur(0, 0);
	}
	
	private int n;
	private int[][] dp;
	private int[] level;
	private int[] damage;
	private int recur(int p, int owed) {
		if (dp[p][owed] != -1)
			return dp[p][owed];
		if (n - p == owed) // no card should be used any more
			return 0; 
		// not use card p
		int result = recur(p + 1, Math.max(owed - 1, 0));
		// use card p
		if (n - p >= owed + level[p]) { // enough cards left to use card p
			result = Math.max(result, damage[p] + recur(p + 1, owed + level[p] - 1));
		}
		dp[p][owed] = result;
		return result;
	}
}
