package dp;

public class NumberOfFiboCalls {

	public static void main(String[] args) {
		NumberOfFiboCalls obj = new NumberOfFiboCalls();
		int[] res = obj.fiboCallsMade(22);
		System.out.println(res[0] + " " + res[1]);
	}
	
	public int[] fiboCallsMade(int n) {
		if (n == 0) {
			return new int[]{1, 0};
		} else if (n == 1) {
			return new int[]{0, 1};
		}
		int[][] dp = new int[n + 1][2];
		dp[0] = new int[]{1, 0};
		dp[1] = new int[]{0, 1};
		for (int i = 2; i <= n; i++) {
			dp[i] = new int[] {dp[i - 1][0] + dp[i - 2][0], dp[i - 1][1] + dp[i - 2][1]};
		}
		return dp[n];
	}

}
