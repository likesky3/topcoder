package dp;

public class ScrambleString {

	public static void main(String[] args) {
		ScrambleString obj = new ScrambleString();
		String s1 = "great";
		String s2 = "taegr";
		System.out.println(obj.isScramble(s1, s2));
	}
	
	public boolean isScramble(String s1, String s2) {
        if (s1 == null) return s2 == null;
        if (s2 == null) return s1 == null;
        if (s1.length() != s2.length()) return false;
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                for (int j = 0; j + len <= n; j++) {
                    for (int k = 1; k < len; k++) {
                        dp[i][j][len] |= (dp[i][j][k] && dp[i + k][j + k][len - k]) 
                                     || (dp[i][j + len - k][k] && dp[i + k][j][len - k]);
                    }
                }
            }
        }
        return dp[0][0][n];
    }

}
