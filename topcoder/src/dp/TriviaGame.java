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
	
	public int maximumScore2(int[] points, int tokensNeeded, int[] bonuses) {
		int N = points.length;
		if (tokensNeeded == 1) {
			int result = 0;
			for (int p : points) {
				result += p;
			}
			for (int b : bonuses) {
				result += b;
			}
			return result;
		}
		// dp[i][t]: maximum score got after answering the i-th question with t tokens on hand
		int[][] dp = new int[2][tokensNeeded];
		dp[0][0] = - points[0];
		dp[0][1] = points[0];
		for (int i = 1; i < N; i++) {
			for (int t = 0; t < tokensNeeded; t++) {
				dp[i % 2][t] = Math.max(dp[(i - 1) % 2][t] - points[i], 
						dp[(i - 1) % 2][(t - 1 + tokensNeeded) % tokensNeeded] + points[i] + (t == 0 ? bonuses[i] : 0));
			}
		}
		int result = 0;
		for (int t = 0; t < tokensNeeded; t++) {
			result = Math.max(result, dp[(N - 1) % 2][t]);
		}
		return result;
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
