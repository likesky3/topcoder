package dp;

import java.util.*;

public class CreateMaximumNumber {

	public static void main(String[] args) {
		CreateMaximumNumber obj = new CreateMaximumNumber();
		int[] num1 = {5, 2, 2};
		int[] num2 = {6, 4, 1};
		int[] result = obj.maxNumber(num1, num2, 3);
		for (int n : result) {
			System.out.print(n);
		}
		System.out.println();
	}
	
	// greedy
	public int[] maxNumber(int[] num1, int[] num2, int k) {
		int minLenFromNum1 = Math.max(0, k - num2.length);
		int maxLenFromNum1 = Math.min(num1.length, k);
		int[] result = new int[k];
		for (int i = minLenFromNum1; i <= maxLenFromNum1; i++) {
			int[] cand = merge(maxArray(num1, i), maxArray(num2, k - i), k);
			if (greater(cand, 0, result, 0)) {
				result = cand;
			}
		}
		return result;
	}
	
	// input num1.length + num2.length == k
	private int[] merge(int[] num1, int[] num2, int k) {
		int[] result = new int[k];
		for (int i = 0, j = 0, r = 0; r < k; r++) {
			result[r] = greater(num1, i, num2, j) ? num1[i++] : num2[j++];
		}
		return result;
	}
	
	private boolean greater(int[] num1, int i, int[] num2, int j) {
		for (; i < num1.length && j < num2.length; i++, j++) {
			if (num1[i] != num2[j]) break;
		}
		return j == num2.length || (i < num1.length && num1[i] > num2[j]);
	}
	
	private int[] maxArray(int[] num, int k) {
		int n = num.length;
		int last = -1;
		int[] result = new int[k];
		for (int i = 0; i < k; i++) { // need fetch k digits
			// search for the largest digit to fill result[i]
			for (int j = last + 1; j + (k - (i + 1)) < n; j++) {
				if (result[i] < num[j]) {
					result[i] = num[j];
					last = j;
				}
			}
		}
		return result;
	}
	
	private int[] maxArray2(int[] num, int k) {
		Stack<Integer> stack = new Stack<Integer>();
		int n = num.length;
		for (int i = 0; i < n; i++) {
			while (stack.size() + n - i > k && !stack.isEmpty() && stack.peek() < num[i]) {
				stack.pop();
			}
			if (stack.size() < k) {
				stack.push(num[i]);
			}
		}
		int[] result = new int[k];
		for (int i = k - 1; i >= 0; i--) {
			result[i] = stack.pop();
		}
		return result;
	}
	
	// more concise code than maxArray2, without using stack
	private int[] maxArray3(int[] num, int k) {
		int n = num.length;
		int[] result = new int[k];
		for (int i = 0, j = 0; i < n; i++) {
			while (n - i + j > k && j > 0 && result[j - 1] < num[i])
				j--;
			if (j < k)
				result[j++] = num[i];
		}
		return result;
	}
	
	// version 1 & 2 both timeout
	public int[] maxNumber2(int[] num1, int[] num2, int k) {
		String[][][] dp = new String[k + 1][num1.length + 1][num2.length + 1];
		for (String[][] arr2 : dp) {
			for (String[] arr1 : arr2) {
				Arrays.fill(arr1, "");
			}
		}
		for (int t = 1; t <= k; t++) {
			// i = 0
			for (int j = t; j  <= num2.length; j++) {
				dp[t][0][j] = getLargerNum(dp[t][0][j - 1], dp[t - 1][0][j - 1] + String.valueOf(num2[j - 1]));
			}
			for (int i = 1; i <= num1.length; i++) {
				if (i >= t) {
					dp[t][i][0] = getLargerNum(dp[t][i - 1][0], dp[t - 1][i - 1][0] + String.valueOf(num1[i - 1]));
				}
				for (int j = 1; j <= num2.length; j++) {
					if (i + j >= t) {
						dp[t][i][j] = getLargerNum(dp[t][i - 1][j - 1], getLargerNum(dp[t - 1][i][j - 1] + String.valueOf(num2[j - 1]), dp[t - 1][i - 1][j] + String.valueOf(num1[i - 1])));
						dp[t][i][j] = getLargerNum(dp[t][i][j], getLargerNum(dp[t][i][j - 1], dp[t][i - 1][j])); // easy to omit these two
					}
				}
			}
		}
		int[] maxNum = new int[k];
		char[] charArray = dp[k][num1.length][num2.length].toCharArray();
		for (int i = 0; i < k; i++) {
			maxNum[i] = charArray[i] - '0';
		}
		return maxNum;
	}
	
	public int[] maxNumber1(int[] num1, int[] num2, int k) {
		memo = new String[num1.length + 1][num2.length + 1][k + 1];
		this.num1 = num1;
		this.num2 = num2;
		String s = recur(0, 0, k);
		int[] maxNum = new int[k];
		char[] charArray = s.toCharArray();
		for (int i = 0; i < k; i++) {
			maxNum[i] = charArray[i] - '0';
		}
		return maxNum;
	}

	int[] num1, num2;
	String[][][] memo;
	private String recur(int i, int j, int k) {
//		System.out.printf("i = %d, j = %d, k = %d, cand=%s\n", i, j, k, getNum(cand));
		if (k == 0 || (i >= num1.length && j >= num2.length)) // no digits available
			return "";
		if (memo[i][j][k] != null) {
			return memo[i][j][k];
		}
		String result = "";
		if (i < num1.length && j < num2.length)
			result = recur(i + 1, j + 1, k); // skip current digit in num1 and num2
		if (i < num1.length) {
			String s = String.valueOf(num1[i]) + recur(i + 1, j, k - 1);
			result = getLargerNum(s, result);
			s = recur(i + 1, j, k);
			result = getLargerNum(s, result);
		}
		if (j < num2.length) {
			String s = String.valueOf(num2[j]) + recur(i, j + 1, k - 1);
			result = getLargerNum(s, result);
			s = recur(i, j + 1, k);
			result = getLargerNum(s, result);
		}
		return memo[i][j][k] = result;
	}
	
	private String getLargerNum(String a, String b) {
		if (a == null) return b;
		if (a.length() != b.length()) {
			return a.length() > b.length() ? a : b;
		} else {
			boolean aIsLarger = true;
			for (int i = 0; i < a.length(); i++) {
				if (a.charAt(i) > b.charAt(i)) {
					break;
				} else if (a.charAt(i) < b.charAt(i)) {
					aIsLarger = false;
					break;
				}
			}
			return aIsLarger ? a : b;
		}
	}
	private void updateMax(int[] maxNum, List<Integer> cand) {
//		System.out.printf("old max=%s, cand=%s\n", getNum(maxNum), getNum(cand));
		boolean needUpdate = false;
		for (int i = 0; i < maxNum.length; i++) {
			if (cand.get(i) > maxNum[i]) {
				needUpdate = true;
				break;
			} else if (cand.get(i) < maxNum[i]){
				break;
			}
		}
		if (needUpdate) {
			for (int i = 0; i < maxNum.length; i++) {
				maxNum[i] = cand.get(i);
			}
		}
//		System.out.printf("new max=%s, cand=%s\n", getNum(maxNum), getNum(cand));
	}

	private String getNum(List<Integer> cand) {
		String s = "";
		for (int n : cand) {
			s += n;
		}
		return s;
	}
	private String getNum(int[] cand) {
		String s = "";
		for (int n : cand) {
			s += n;
		}
		return s;
	}
}
