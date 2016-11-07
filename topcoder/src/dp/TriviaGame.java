package dp;

public class TriviaGame {
	public int maximumScore(int[] points, int tokensNeeded, int[] bonuses) {
		int N = points.length;
		int T = tokensNeeded;
		// dp[i][j]: at the i-th question (index start at 1), the total points have when having j tokens
		int[][] dp = new int[N + 1][tokensNeeded];
		for (int i = 1; i <= N; i++) {
			if (i >= T)
				dp[i][0] = Math.max(dp[i - 1][T - 1] + points[i-1] + bonuses[i-1], dp[i-1][0] - points[i-1]);
			else
				dp[i][0] = dp[i-1][0] - points[i-1];
			for (int j = 1; j < T; j++) {
				dp[i][j] = Math.max(dp[i-1][j-1] + points[i-1], dp[i-1][j] - points[i-1]);
			}
		}
		int res = dp[N][0];
		for (int j = 1; j < T; j++) {
			res = Math.max(dp[N][j], res);
		}
		return res;
	}
	
	public static void main(String[] args) {
		TriviaGame obj = new TriviaGame();
		int[] points = {1, 2, 3, 4, 5, 6};
		int[] bonuses = {1, 1, 1, 20, 1, 1};
		System.out.println(obj.maximumScore(points, 3, bonuses));
		
		points = new int[]{1, 2, 3, 4, 5, 6};
		bonuses = new int[]{4, 4, 4, 4, 4, 4};
		System.out.println(obj.maximumScore(points, 3, bonuses));
		
		points = new int[]{1, 2, 3, 4, 5, 6};
		bonuses = new int[]{4, 4, 4, 4, 4, 4};
		System.out.println(obj.maximumScore(points, 3, bonuses));
	}
}
