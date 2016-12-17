package dp;

public class LongestValidParentheses {

	public static void main(String[] args) {
		LongestValidParentheses obj = new LongestValidParentheses();
		System.out.println(obj.longestValidParentheses("(())(())"));
	}
	
	public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 0;
        for (int i = 0; i + 1 < n; i++) {
            if (s.charAt(i) == '(' && s.charAt(i + 1) == ')') {
                dp[i][i + 1] = true;
                maxLen = 2;
            }
            System.out.printf("pos1: dp[%d][%d]=%b, maxLen=%d\n", i, i + 1, dp[i][i + 1], maxLen);
        }
        for (int len = 4; len <= n; len += 2) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == '(' && s.charAt(j) == ')';
                if (dp[i][j]) {
                    maxLen = len;
                    System.out.printf("pos2: dp[%d][%d]=%b, maxLen=%d\n", i, j, dp[i][j], maxLen);
                    continue;
                }
                for (int subLen = 2; subLen <= len - 2; subLen += 2) {
                    int k = i + subLen - 1;
                    dp[i][j] = dp[i][k] && dp[k + 1][j];
                    if (dp[i][j]) {
                        maxLen = len;
                        break;
                    }
                }
                System.out.printf("pos3: dp[%d][%d]=%b, maxLen=%d\n", i, j, dp[i][j], maxLen);
            }
        }
        return maxLen;
    }

}
