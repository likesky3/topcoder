package dp;
import java.util.Arrays;

public class P8XGraphBuilder {

	public static void main(String[] args) {
		P8XGraphBuilder obj = new P8XGraphBuilder();
		int[] scores = {1, 3, 2, 5, 3, 7, 5};
		System.out.println(obj.solve(scores));
	}

	public int solve(int[] scores) {
		int n = scores.length;
		int nodesNum = n + 1;
		int totalDegrees = 2 * n;
		int[][] dp = new int[nodesNum + 1][totalDegrees + 1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[0][0] = 0;
		for (int i = 0; i < nodesNum; i++) {
			for (int j = 0; j < totalDegrees; j++) {
				for (int k = 1; k < nodesNum && j + k <= totalDegrees; k++) {
					dp[i + 1][j + k] = Math.max(dp[i + 1][j + k], dp[i][j] + scores[k - 1]);
				}
			}
		}
		return dp[nodesNum][totalDegrees];
	}
}
