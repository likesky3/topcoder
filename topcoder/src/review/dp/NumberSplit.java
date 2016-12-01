package review.dp;

import java.util.*;

public class NumberSplit {

	public static void main(String[] args) {
		NumberSplit obj = new NumberSplit();
		System.out.println(obj.longestSequence(99999));
	}
	
	// time out 
	public int longestSequence(int start) {
		dp = new int[100001];
		Arrays.fill(dp, -1);
		return recur(start);
	}

	private int[] dp;
	private int recur(int start) {
		if (dp[start] != -1)
			return dp[start];
		if (start < 10)
			return 1;
		Set<Integer> successors = getSuccessors(start);
		int result = 0;
		for (int num : successors) {
			result = Math.max(result, recur(num));
		}
		result += 1;
		return result;
	}

	private Set<Integer> getSuccessors(int start) {
		Set<Integer> result = new HashSet<>();
		if (start < 10) {
			result.add(start);
			return result;
		}

		int power10 = 10;
		while (start / power10 > 0) {
			int part1 = start / power10;
			int part2 = start % power10;
			result.add(part1 * part2);
			if (part1 >= 10 || part2 >= 10) {
				Set<Integer> A = getSuccessors(part1);
				Set<Integer> B = getSuccessors(part2);
				for (int a: A) {
					for (int b: B) {
						result.add(a * b);
					}
				}
			}
			power10 *= 10;
		}
		return result;
	}
}
