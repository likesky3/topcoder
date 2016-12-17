package dp;

public class RegularExpressionMatching {

	public static void main(String[] args) {
		RegularExpressionMatching obj = new RegularExpressionMatching();
//		System.out.println(obj.isMatch("aab", "c*a*b"));
		System.out.println(obj.isMatch("aaa", "ab*ac*a"));
	}
	
	public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j += 2) {
        	dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                boolean matchCase1 = s.charAt(i - 1) == p.charAt(j - 1);
                boolean matchCase2 = p.charAt(j - 1) == '.';
                boolean matchCase3 = j >= 2 && (p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)) && p.charAt(j - 1) == '*';
//                dp[i][j] = (dp[i - 1][j - 1] && (matchCase1 || matchCase2 )) || (((j >= 2 && (dp[i][j - 2] )) || dp[i - 1][j]) && matchCase3);
                dp[i][j] = (dp[i - 1][j - 1] && (matchCase1 || matchCase2 )) || (p.charAt(j - 1) == '*' && j >= 2 && dp[i][j - 2])
                		|| (dp[i - 1][j] && matchCase3);
//                System.out.printf("m1=%b, m2=%b, m3=%b, dp[%d][%d]=%b, %s, %s\n", 
//                		matchCase1, matchCase2, matchCase3, i, j, dp[i][j], s.substring(0, i), p.subSequence(0,  j));
            }
        }
        return dp[m][n];
    }

}
