package dp;

import java.util.Arrays;
import java.util.Comparator;

public class SentenceDecomposition {

	public static void main(String[] args) {
		SentenceDecomposition obj = new SentenceDecomposition();
		String sentence = "thisismeaningless";
		String[] validWords = {"this", "is", "meaningful"};
//		System.out.println(obj.decompose(sentence, validWords));
		sentence = "abba";
		validWords = new String[] {"ab", "ac"};
		System.out.println(obj.decompose(sentence, validWords));
	}
	
	private final int inf = 100;
	public int decompose(String sentence, String[] validWords) {
		int N = sentence.length();
		int M = validWords.length;
		// dp[i]: the transformation cost for substring(0, i-1) inclusively
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = inf;
			for (int l = 1; l <= i; l++) {
				String sub = sentence.substring(i - l, i);
				int cost = inf;
				for (int j = 0; j < M; j++) {
					cost = Math.min(cost, calCost(validWords[j], sub));
				}
				System.out.printf("i=%d, sub=%s, cost=%d\n", i, sub, cost);
				dp[i] = Math.min(dp[i], dp[i - l] + cost);
			}
		}
		return dp[N] == inf ? -1 : dp[N];
	}
	
	private int calCost(String word, String s) {
		if (word.length() != s.length())
			return inf;
		char[] wordArray = word.toCharArray();
		char[] sArray = s.toCharArray();
		Arrays.sort(wordArray);
		Arrays.sort(sArray);
		for (int i = 0; i < s.length(); i++) {
			if (wordArray[i] != sArray[i])
				return inf;
		}
		int cost = 0;
		for (int i = 0; i < s.length(); i++) {
			if (word.charAt(i) != s.charAt(i))
				cost++;
		}
		return cost;
	}

	@SuppressWarnings("unchecked")
	public int decompose1(String sentence, String[] validWords) {
		Arrays.sort(validWords, new Comparator() {
			@Override
			public int compare(Object a, Object b) {
				return ((String)a).length() - ((String)b).length();
			}});
		int N = sentence.length();
		int M = validWords.length;
		// dp[i] : the transformation cost of substring s(0, i - 1);
		int[] dp = new int[N + 1]; 
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				int len = validWords[j].length();
				if (len > i) break;
				int p = i - len;
				if (dp[p] != Integer.MAX_VALUE) {
					int cost = check(sentence.substring(p, i), validWords[j]);
					if (cost >= 0) {
						dp[i] = Math.min(dp[i], dp[p] + cost);
					}
				}
			}
		}
		return dp[N] == Integer.MAX_VALUE ? -1 : dp[N];
	}
	private int check(String s, String t) {
		int[] used = new int[26];
		for (int i = 0; i < s.length(); i++) {
			used[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			int idx = t.charAt(i) - 'a'; 
			used[idx]--;
			if (used[idx] < 0) {
//				System.out.println("ret1: " + s + " " + t + " " + ('a' + idx));
				return -1;
			}
		}
		for (int i = 0; i < used.length; i++) {
			if (used[i] > 0) {
//				System.out.println("ret2: " + s + " " + t + " " + ('a' + i));
				return -1;
			}
		}
		int cost = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != t.charAt(i))
				cost++;
		}
		return cost;
	}
}
