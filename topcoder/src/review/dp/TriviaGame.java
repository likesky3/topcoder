package review.dp;

public class TriviaGame {

	public static void main(String[] args) {
		TriviaGame obj = new TriviaGame();
		int[] points = {1, 2, 3, 4, 5, 6};
		int tokensNeeded = 3;
		int[] bonuses = {1, 1, 1, 20, 1, 1};
//		System.out.println(obj.maximumScore(points, tokensNeeded, bonuses));
		
		points = new int[]{150, 20, 30, 40, 50};
		tokensNeeded = 3;
		bonuses = new int[]{0, 0, 0, 250, 0};
//		System.out.println(obj.maximumScore(points, tokensNeeded, bonuses));
		
		points = new int[]{108, 25, 306};
		tokensNeeded = 12;
		bonuses = new int[]{768, 6411, 5545};
		System.out.println(obj.maximumScore(points, tokensNeeded, bonuses) == 439);
	}
	
	public int maximumScore(int[] points, int tokensNeeded, int[] bonuses) {
		int N = points.length;
		int T = tokensNeeded;
		int[][] dp = new int[N + 1][T];
		for (int i = 1; i <= N; i++) {
			if (i >= T) {
				dp[i][0] = Math.max(dp[i - 1][0] - points[i - 1], dp[i - 1][T - 1] + points[i - 1] + bonuses[i - 1]);
			} else {
				dp[i][0] = dp[i - 1][0] - points[i - 1];
			}
			for (int t = 1; t < T; t++) {
				dp[i][t] = Math.max(dp[i - 1][t - 1] + points[i - 1], dp[i - 1][t] - points[i - 1]);
			}
		}
		int result = 0;
		for (int t = 0; t < tokensNeeded; t++) {
			result = Math.max(result, dp[N][t]);
		}
		return result;
	}

}
