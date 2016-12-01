package dp;
import java.util.*;


public class SpellCardsEasy {

	public static void main(String[] args) {
		int[] level = {2, 1, 1};
		int[] damage = {10, 10, 1};
		SpellCardsEasy obj = new SpellCardsEasy();
		System.out.println(obj.maxDamage(level, damage));
	}
	
	public int maxDamage(int[] level, int[] damage) {
		this.n = level.length;
		dp = new int[51][51];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1 );
		}
		this.level = level;
		this.damage = damage;
		return f(0, 0);
	}
	
	private int n;
	private int[][] dp;
	private int[] level, damage;
	private int f(int p, int remain) {
		if (dp[p][remain] != -1)
			return dp[p][remain];
		if (n - p == remain) // no card should be used
			return 0;
		int res = f(p + 1, Math.max(remain - 1, 0)); // not use card p
		
		// use card p
		if (n - p >= remain + level[p]) {
			res = Math.max(res, damage[p] + f(p + 1, remain + level[p] - 1));
		}
		dp[p][remain] = res;
		return res;
	}
	
	// would timeout
	public int maxDamage1(int[] level, int[] damage) {
		return recur(level, damage, new boolean[level.length]);
	}
	
	private int recur(int[] level, int[] damage, boolean[] visited) {
		int res = 0;
		for (int i = 0; i < level.length; i++) {
			if (visited[i]) continue;
			System.out.println("loop i=" + i);
			int counter = 1;
			for (int j = i + 1; j < level.length; j++) {
				// possibly counter == level[i] when as immediately as we enter the loop
				if (counter == level[i]) {
					break;
				}
				if (!visited[j])
					counter++;
			}
			if (counter == level[i]) {
				List<Integer> idxes = new ArrayList<>();
				for (int k = i; k < level.length; k++) {
					if (!visited[k]) {
						idxes.add(k);
						visited[k] = true;
						counter--;
					}
					if (counter == 0) break;
				}
				res = Math.max(res, damage[i] + recur(level, damage, visited));

				for (int idx : idxes) {
					visited[idx] = false;
				}
			}
		}
		return res;
	}

}
