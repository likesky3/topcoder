package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatchNumbersEasy {

	public static void main(String[] args) {
		MatchNumbersEasy obj = new MatchNumbersEasy();
		int[] matches = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
		System.out.println(obj.maxNumber(matches, 50));
		matches = new int[]{5, 23, 24};
		System.out.println(obj.maxNumber(matches, 30));
		matches = new int[]{6, 8, 9, 10, 10, 11, 12, 14, 15, 15};
		System.out.println(obj.maxNumber(matches, 40));
	}
	
	public String maxNumber(int[] matches, int n) {
		Map<Integer, Set<String>> dp = new HashMap<>();
		Set<String> set0 = new HashSet<>();
		set0.add("");
		dp.put(0, set0);
		int m = matches.length;
		for (int i = 1; i <= n; i++) {
			Set<String> setI = new HashSet<>();
			setI.add("");
			dp.put(i, setI);
			String maxNonZero = "0";
			String longestZeros = "";
			for (int j = 0; j < m; j++) {
				if (i >= matches[j]) {
					Set<String> opts = dp.get(i - matches[j]);
					for (String opt : opts) {
						String val = "" + j + opt;
						if (isAllZero(val)) {
							if (val.length() > longestZeros.length())
								longestZeros = val;
						} else {
							if (compIntString(val, maxNonZero) > 0)
								maxNonZero = val;
						}
					}
				}
			}
			if (!maxNonZero.equals("0"))
				setI.add(maxNonZero);
			if (!longestZeros.isEmpty())
				setI.add(longestZeros);
		}
		String max = "0";
		for (String opt : dp.get(n)) {
			if (!isAllZero(opt) && compIntString(opt, max) > 0) {
				max = opt;
			}
		}
		return max;
	}
	
	private int compIntString(String a, String b) {
		if (a.length() != b.length()) {
			return a.length() - b.length();
		} else {
			for (int i = 0; i < a.length(); i++) {
				if (a.charAt(i) != b.charAt(i)) {
					return a.charAt(i) - b.charAt(i);
				}
			}
			return 0;
		}
	}
	
	private boolean isAllZero(String a) {
		int i = 0;
		for (; i < a.length(); i++) {
			if (a.charAt(i) != '0')
				break;
		}
		return i == a.length();
	}
}
