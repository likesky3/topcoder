package dp;

public class OnesAndZeros {

	public static void main(String[] args) {
		OnesAndZeros obj = new OnesAndZeros();
		String[] strs = {"10","0001","111001","1","0"};
		int m = 4, n = 3;
		System.out.println(obj.findMaxForm(strs, m, n));
	}
	
	public int findMaxForm(String[] strs, int m, int n) {
        int N = strs.length;
        int[][] dp = new int[m + 1][n + 1];
        int[] zeros = new int[N];
        int[] ones = new int[N];
        for (int i = 0; i < strs.length; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0')
                    zeros[i]++;
                else
                    ones[i]++;
            }
        }
        for (int p = N - 1; p >= 0; p--) {
        	for (int i = m; i >= zeros[p]; i--) {
        		for (int j = n; j >= ones[p]; j--) {
        			dp[i][j] = Math.max(1 + dp[i - zeros[p]][j - ones[p]], dp[i][j]);
//        				System.out.printf("dp[%d][%d][%d]=%d\n", p, i, j, dp[p][i][j]);
        		}
        	}
        }
        return dp[m][n];
    }
}
