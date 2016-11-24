package review.dp;

import java.util.Arrays;

public class ColorfulGardenHard {
	public int count(String garden, String forbid) {
		g = garden.toCharArray();
		Arrays.sort(g);
		f = forbid.toCharArray();
		N = g.length;
		dp = new int[1 << N][27];
		for (int[] p : dp) {
			Arrays.fill(p, -1);
		}
		return recur((1 << N) - 1, (char)(26 + 'a'), 0);
	}
	
	char[] g, f;
	int[][] dp;
	int N;
	final int mod = 1000000007;
	private int recur(int remains, char last, int p) {
		if (dp[remains][last - 'a'] != -1) {
			return dp[remains][last - 'a'];
		}
		if (remains == 0)
			return dp[0][last - 'a'] = 1;
		long ans = 0;
		for (int i = 0; i < N; i++) {
			if (i > 0 && g[i] == g[i - 1] && (remains & (1 << (i - 1))) > 0)
				continue;
			if ((remains & (1 << i)) > 0 && g[i] != f[p] && g[i] != last) {
				ans += recur(remains - (1 << i), g[i], p + 1);	
			}
		}
		return dp[remains][last - 'a'] = (int)(ans % mod);
	}
	
	public static void main(String[] args) {
		ColorfulGardenHard obj = new ColorfulGardenHard();
		System.out.println(obj.count("aabbcc", "aabbcc"));
	}
}
