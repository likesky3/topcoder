package dp;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class NumberSplit {

	public static void main(String[] args) {
		NumberSplit obj = new NumberSplit();
		System.out.println(obj.longestSequence1(99999));
	}
	
	public int longestSequence1(int start) {
		return recur(String.valueOf(start));
	}
	
	public int longestSequence(int start) {
		
		if (map2.containsKey(start))
			return map2.get(start);
		if (start < 10)
			return 1;
		Set<Integer> successors = new HashSet<>();
		generateSuccessors(1, start, successors);
		successors.remove(start);
		int res = 1;
		for (int succ : successors) {
			res = Math.max(res, longestSequence(succ) + 1);
			System.out.printf("start=%d, succ=%d, res=%d\n", start, succ, res);
		}
		map2.put(start, res);
		return res;
	}
	
	public int longestSequence2(int start) {
		int[] dp = new int[100001];
		for (int i = 0; i < 10; i++)
			dp[i] = 1;
		for (int i = 10; i <= start; i++) {
			Set<Integer> successors = new HashSet<>();
			generateSuccessors(1, i, successors);
			int max = 1;
			for (int succ : successors) {
				max = Math.max(max, dp[succ] + 1);
			}
			dp[i] = max;
		}
		return dp[start];
	}
	
	private Map<Integer, Integer> map2 = new HashMap<>();
	private void generateSuccessors(int multiplier, int n, Set<Integer> successors) {
		successors.add(multiplier * n);
		for (int pow = 10; pow <= n; pow *= 10) {
			generateSuccessors(multiplier * (n / pow), n % pow, successors);
		}
	}
	
	Map<String, Integer> map = new HashMap<>();
	PrintWriter pw;
	private int recur(String num) {
		if (map.containsKey(num))
			return map.get(num);
		if (num.length() <= 1)
			return 1;
		int n = num.length();
		int m = n - 1;
		int newLength = 0;
		for (int mask = 1; mask < 1 << m; mask++) {
			int k = 1;
			String curr = num;
			ArrayList<Integer> parts = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				if ((mask & (1 << i)) > 0) {
					parts.add(Integer.parseInt(curr.substring(0, k)));
					curr = curr.substring(k);
					k = 1;
				} else {
					k++;
				}
			}
			if (curr.length() > 0)
				parts.add(Integer.parseInt(curr));
			int nextNum = 1;
			for (int item : parts) {
				nextNum *= item;
			}
			
			newLength = Math.max(newLength, longestSequence1(nextNum) + 1);
		}
		map.put(num, newLength);
		return newLength;
	}

}
