package dp;

public class WildcardMatching {

	public static void main(String[] args) {
		WildcardMatching obj = new WildcardMatching();
		System.out.println(obj.isMatch("bbbaab", "a**?***"));
	}
	
	public boolean isMatch(String s, String p) {
        if (s == null) return p == null;
        if (p == null) return s == null;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[2][n + 1];
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';
        }
        int prev, curr = 0;
        for (int i = 1; i <= m; i++) {
            prev = curr;
            curr = 1 - curr;
            dp[curr][0] = false;
            for (int j = 1; j <= n; j++) {
                char c = p.charAt(j - 1);
                if (c == '?') {
                    dp[curr][j] = dp[prev][j - 1];
                } else if (c == '*') {
                    dp[curr][j] = dp[curr][j - 1] || dp[prev][j - 1] || dp[prev][j];
                } else {
                    dp[curr][j] = dp[prev][j - 1] && c == s.charAt(i - 1);
                }
                System.out.printf("dp[%d][%d]=%b\n", i, j, dp[curr][j]);
            }
            System.out.println();
        }
        return dp[curr][n];
    }

}
