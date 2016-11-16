package dp;

public class PalindromicSubstringsDiv2 {

	public static void main(String[] args) {
		PalindromicSubstringsDiv2 obj = new PalindromicSubstringsDiv2();
		String[] S1 = {"da"};
		String[] S2 = {"ata"};
		System.out.println(obj.count(S1, S2));
	}
	
	public int count(String[] S1, String[] S2) {
		StringBuilder sb = new StringBuilder();
		for (String s : S1) {
			sb.append(s);
		}
		for (String s: S2) {
			sb.append(s);
		}
		int n = sb.length();
		int count = n;
		boolean[][] dp = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			dp[i][i] = true;
		}
		for (int len = 2; len <= n; len++) {
			for (int i = 0; i + len <= n; i++) {
				int j = i + len - 1;
				dp[i][j] = sb.charAt(i) == sb.charAt(j) && (i + 1 == j || dp[i + 1][j - 1]);
				if (dp[i][j])
					count++;
			}
		}
		return count;
	}

}
