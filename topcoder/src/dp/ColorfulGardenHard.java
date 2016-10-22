package dp;

import java.util.HashMap;
import java.util.Map;

public class ColorfulGardenHard {

	public static void main(String[] args) {
		ColorfulGardenHard obj = new ColorfulGardenHard();
//		System.out.println(obj.count("abcdefghijklmno", "zzzzzzzzzzzzzzz"));
		System.out.println(obj.count("aabbab", "zzzzzz"));
//		System.out.println(obj.count("ab", "zz"));
	}
	
	public 	int count(String garden, String forbid) {
		if (garden.length() == 1)
			return garden.charAt(0) != forbid.charAt(0) ? 1 : 0;
		int N = garden.length();
		// dp[mask][last]: number of strings satisfying:
		// current use state of letters in garden is represented as mask, and the last char whose index is "last"
		int mask = (1 << N) - 1;
		int[][] dp = new int[mask + 1][N];
		boolean[] used = new boolean[26];
		for (int i = 0; i < N; i++) {
			if (used[garden.charAt(i) - 'a'])
				continue;
			used[garden.charAt(i) - 'a'] = true;
			if (garden.charAt(i) != forbid.charAt(0))
				dp[1 << i][i] = 1;
		}
		for (int currBits = 1; currBits < mask; currBits++) {
			for (int last = 0; last < N; last++) {
				used = new boolean[26];
				for (int next = 0; next < N; next++) {
					if ((currBits & (1 << next)) > 0) // next has been used before
						continue;
					if (used[garden.charAt(next) - 'a'])
						continue;
					used[garden.charAt(next) - 'a'] = true;
					int currBitsNum = Integer.bitCount(currBits);
					if (garden.charAt(next) == garden.charAt(last) || garden.charAt(next) == forbid.charAt(currBitsNum))
						continue;
					int newMask = currBits | (1 << next);
					long tmp = dp[newMask][next];
					tmp = (tmp + dp[currBits][last]) % MOD;
					dp[newMask][next] = (int)tmp;
//					System.out.printf("currBits=%s, last=%d, newMask=%s, next=%d, dp[%s][%d]=%d\n",
//							Integer.toBinaryString(currBits), last, Integer.toBinaryString(newMask), next,Integer.toBinaryString(newMask), next,
//							dp[newMask][next]);
				}
//				System.out.printf("dp[%s][%d]=%d\n",
//						Integer.toBinaryString(currBits), last, 
//						dp[currBits][last]);
			}
			
		}
		
		int res = 0;
		used = new boolean[26];
		for (int last = 0; last < N; last++) {
//			if (used[garden.charAt(last) - 'a'])
//				continue;
//			used[garden.charAt(last) - 'a'] = true;
			res += dp[mask][last];
			if (res > MOD)
				res -= MOD;
		}
		return res;
	}
	
	public 	int count1(String garden, String forbid) {
		this.forbid = forbid;
		this.N = garden.length();
		this.res = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (char c : garden.toCharArray()) {
			if (!map.containsKey(c)) {
				map.put(c, 0);
			}
			map.put(c, map.get(c) + 1);
		}
		recur(map, ' ', 0);
		return res;
	}
	
	private String forbid;
	private int N;
	private int res;
	private final int MOD = 1000000007;
	
	// 存在重复计算，时间复杂度是N^N,每次递归调用时遍历全部情况
	private void recur(Map<Character, Integer> map, char lastChar, int n) {
		if (n == N) {
			res++;
			if (res > MOD)
				res -= MOD;
			return;
		}
		for (Character c : map.keySet()) {
//			System.out.printf("n=%d, lastChar=%c, c=%c, num[%c]=%d\n", n, lastChar, c, c, map.get(c));
			if (map.get(c) > 0 && c != forbid.charAt(n) && c != lastChar) {
				int old = map.get(c);
				map.put(c, old - 1);
				recur(map, c, n + 1);
				map.put(c, old);
			}
		}
	}
}
