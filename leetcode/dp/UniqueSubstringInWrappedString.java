package dp;

public class UniqueSubstringInWrappedString {

	public static void main(String[] args) {
		UniqueSubstringInWrappedString obj = new UniqueSubstringInWrappedString();
		System.out.println(obj.findSubstringInWraproundString("zabebcde"));
	}

	public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0)
            return 0;
        int n = p.length();
        int[] dp = new int[26];
        int[] f = new int[n];
        dp[p.charAt(n - 1) - 'a'] = 1;
        f[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            char curr = p.charAt(i);
            char next = p.charAt(i + 1);
            if ((curr - 'a' + 1) % 26 == next - 'a') {
                dp[curr - 'a'] = Math.max(f[i + 1] + 1, dp[curr - 'a']);
                f[i] = f[i + 1] + 1;
            } else {
                dp[curr - 'a'] = Math.max(1, dp[curr - 'a']);
                f[i] = 1;
            }
//            System.out.printf("i=%d, dp[%c]= %d, f[%d]=%d\n", i, curr, dp[curr - 'a'], i, f[i]);
            
        }
        int result = 0;
        for (int i = 0; i < 26; i++) {
//        	System.out.printf("dp[%d]=%d\n", i, dp[i]);
            result += dp[i];
        }
        return result;
    }
}
