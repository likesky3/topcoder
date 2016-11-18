package dp;
import java.util.Arrays;

public class P8XGraphBuilder {

	public static void main(String[] args) {
		P8XGraphBuilder obj = new P8XGraphBuilder();
		int[] scores = {1, 3, 2, 5, 3, 7, 5};
//		int[] scores = {1, 1};
//		System.out.println(obj.solve(scores));
//		System.out.println(obj.solve2(scores));
		scores = new int[]{0, 0, 0, 10};
		System.out.println(obj.solve7(scores) == 10);
		scores = new int[]{5, 0, 0};
		System.out.println(obj.solve6(scores) == 15);
		scores = new int[]{0, 0, 0, 4, 7, 0, 0};
		System.out.println(obj.solve6(scores) == 8);
	}
	
	// Evil Theorem: Suppose we have N nodes and we know the degrees of each node.
	// Suppose the degrees follows the rule in Degree Bound Lemma and the degrees 
	// sums to 2*N - 2 as in I-Forgot-The-Name Lemma. Then, there exists a tree consisting
	// of N nodes whose nodes has the corresponding degrees.
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
	
	// recursive dp
	public int solve2(int[] scores) {
		this.scores = scores;
		int n = scores.length;
		int nodesNum = n + 1;
		memo = new int[nodesNum + 1][nodesNum + 1];
		for (int[] p : memo) {
			Arrays.fill(p, -1);
		}
		int best = -1;
		for (int i = 1; i <= n; i++) { // special take care of root
			best = Math.max(best, scores[i - 1] + recur(i, n - i));
		}
		return best;
	}
	
	private int[] scores;
	private int[][] memo;
	private int recur(int unprocessed, int remain) {
		if (memo[unprocessed][remain] != -1) {
			return memo[unprocessed][remain];
		}
		if (unprocessed == 0 && remain > 0) {
			return -1;
		}
		if (remain == 0) // all the unprocessed nodes now are turned to be leaves
			return unprocessed * scores[0];
		
		int best = Integer.MIN_VALUE;
		for (int i = 0; i <= remain; i++) {
			best = Math.max(best, scores[i] + recur(unprocessed + i - 1, remain - i));
		}
		memo[unprocessed][remain] = best;
		return best;
	}

	
	public int solve3(int[] scores) {
		int n = scores.length;
		int N = n + 1; // total number of nodes
		// dp[i][j]: max scores got when there are i nodes unprocessed in the tree, 
		// j remained to be added into the tree 
		int[][] dp = new int[N + 1][N + 1];
		for (int[] p: dp) {
			Arrays.fill(p, -1);
		}
		dp[1][n] = 0;
		// special case: dp[1][n]
		for (int k = 1; k <= n; k++) {
			dp[k][n - k] = dp[1][n] + scores[k - 1];
			System.out.printf("dp[%d][%d]=%d\n", k, n - k, dp[k][n -k]);
		}
		for (int i = 1; i <= n; i++) {
			for (int j = N - 2; j > 0; j--) {
				if (dp[i][j] == -1)
					continue;
				for (int k = 0; k <= j; k++) {
//					System.out.printf("i=%d, j=%d, k=%d, i-1+k=%d, j-k=%d\n", i, j, k,  i - 1 + k, j - k);
					dp[i - 1 + k][j - k] = Math.max(dp[i - 1 + k][j - k], dp[i][j] + scores[k]);
//					System.out.printf("dp[%d][%d]=%d\n", i - 1 + k, j - k, dp[i-1+k][j-k]);
				}
			}
		}
		int maxScore = 0;
		for (int i = 0; i <= n; i++) {
			maxScore = Math.max(maxScore, dp[i][0] + i * scores[0]); // process the i leaves
		}
		return maxScore;
	}
	
	// not correct version and not needed to be 3 dimensions
	public int solve4(int[] scores) {
		int n = scores.length;
		int N = n + 1; // total number of nodes
		// dp[i][j]: max scores got when there are i nodes unprocessed in the tree, 
		// j remained to be added into the tree 
		int[][][] dp = new int[N + 1][N + 1][N + 1];
		// special case: dp[1][n]
		for (int j = n; j > 0; j--) {
			for (int k = 1; k <= j; k++) {
				dp[1 + k][k][j - k] = dp[1][1][j] + scores[k - 1] + k * scores[0];
				System.out.printf("j=%d, k=%d, dp[%d][%d][%d]=%d\n", j, k, 1 + k, k, j - k, dp[1 + k][k][j - k]);
			}
		}
		for (int t = 2; t <= n; t++) {
			for (int i = 1; i < t; i++) {
				for (int j = N - t; j > 0; j--) {
					for (int k = 1; k <= j; k++) {
						dp[t + k][i - 1 + k][j - k] = Math.max(dp[t + k][i - 1 + k][j - k], dp[t][i][j] - scores[0] + scores[k - 1] + k * scores[0]);
						System.out.printf("t=%d, i=%d, j=%d, k=%d, dp[%d][%d][%d]=%d\n", t, i, j, k, t + k, i - 1 + k, j - k, dp[t + k][i-1+k][j-k]);
					}
				}
			}
		}
		int maxScore = 0;
		for (int i = 0; i <= n; i++) {
			maxScore = Math.max(maxScore, dp[N][i][0]);
		}
		return maxScore;
	}
	
	// in solve4, the degrees of the unprocessed nodes do not count
	// in solve5, do count
	public int solve5(int[] scores) {
		int n = scores.length;
		int N = n + 1; // total number of nodes
		// dp[i][j]: max scores got when there are i nodes unprocessed in the tree, 
		// j remained to be added into the tree 
		int[][] dp = new int[N + 1][N + 1];
		for (int[] p: dp) {
			Arrays.fill(p, -1);
		}
		dp[1][n] = 0;
		// special case: dp[1][n]
		for (int k = 1; k <= n; k++) {
			dp[k][n - k] = dp[1][n] + scores[k - 1] + k * scores[0];
//			System.out.printf("dp[%d][%d]=%d\n", k, n - k, dp[k][n -k]);
		}
		for (int i = 1; i <= n; i++) {
			for (int j = N - 2; j > 0; j--) {
				if (dp[i][j] == -1)
					continue;
				for (int k = 0; k <= j; k++) {
//					System.out.printf("i=%d, j=%d, k=%d, i-1+k=%d, j-k=%d\n", i, j, k,  i - 1 + k, j - k);
					dp[i - 1 + k][j - k] = Math.max(dp[i - 1 + k][j - k], dp[i][j] + scores[k] + k * scores[0] - scores[0]);
//					System.out.printf("dp[%d][%d]=%d\n", i - 1 + k, j - k, dp[i-1+k][j-k]);
				}
			}
		}
		int maxScore = 0;
		for (int i = 0; i <= n; i++) {
			maxScore = Math.max(maxScore, dp[i][0]); 
		}
		return maxScore;
	}
	
	public int solve6(int[] scores) {
		int n = scores.length;
		int N = n + 1; // number of nodes
		if (N == 1)
			return 0;
		if (N == 2)
			return scores[0] * 2;
		// dp[i][j]: max scores we can obtained from a tree with size of i and the degrees of its root is j
		int[][] dp = new int[N + 1][N];
		for (int[] p : dp) {
			Arrays.fill(p, -1);
		}
		dp[1][0] = 0;
		dp[2][1] = scores[0] * 2;
		// start dp
		// key idea, combine two small trees to obtain a larger tree, 
		// enumerate all the trees and find the one with maximal scores
		// tree(i,j) = tree(k, t) + tree(i-k, j-1);
		for (int i = 3; i <= N; i++) {
			// j = 1, only one choice: k = i - 1
			for (int t = 1; t < i - 1; t++) {
				if (dp[i - 1][t] != -1)
					dp[i][1] = Math.max(dp[i][1], dp[1][0] + dp[i - 1][t] + scores[0] + scores[t] - scores[t - 1]);
			}
			
			for (int j = 2; j < i; j++) {
				// k = 1
				dp[i][j] = dp[1][0] + dp[i - 1][j - 1] + scores[0] + scores[j - 1] - scores[j - 2];
				for (int k = 2; k < i; k++) {
					for (int t = 1; t < k; t++) {
						if (dp[k][t] != -1 && dp[i - k][j - 1] != -1) {
							dp[i][j] = Math.max(dp[i][j], 
									dp[k][t] + dp[i - k][j - 1] 
											+ scores[t] - scores[t - 1] + scores[j - 1] - scores[j - 2]);
						}
					}
				}
//				System.out.printf("dp[%d][%d]=%d\n", i, j, dp[i][j]);
			}
		}
		int max = 0;
		for (int j = 1; j < N; j++) {
			max = Math.max(max, dp[N][j]);
		}
		return max;
	}
	
	public int solve7(int[] scores) {
		int n = scores.length;
		int N = n + 1; // number of nodes
		if (N == 1)
			return 0;
		if (N == 2)
			return scores[0] * 2;
		// dp[i][j]: max scores we can obtained from a tree with size of i and the degrees of its root is j
		int[][] dp = new int[N + 1][N];
		for (int[] p : dp) {
			Arrays.fill(p, Integer.MIN_VALUE);
		}
		dp[1][0] = 0;
		dp[2][1] = scores[0] * 2;
		// f[i]: to speed the algorithm, max scores from a tree with size of i and the degrees of its root is one more
		// than actual
		int[] f = new int[N + 1];
		Arrays.fill(f, Integer.MIN_VALUE);
		f[1] = scores[0];
		
		// start dp
		// key idea, combine two small trees to obtain a larger tree, 
		// enumerate all the trees and find the one with maximal scores
		// tree(i,j) = tree(k, t) + tree(i-k, j-1);
		for (int i = 2; i <= N; i++) {
			// j = 1
			dp[i][1] = f[i - 1] + scores[0]; 
			for (int j = 2; j < i; j++) {
				for (int k = 1; k < i; k++) {
					if (dp[i - k][j - 1] != Integer.MIN_VALUE && f[k] != Integer.MIN_VALUE)
						dp[i][j] = Math.max(dp[i][j], f[k] + dp[i - k][j - 1] + scores[j - 1] - scores[j - 2]);
				}
			}
			
			if (i == N) 
				continue;
			
			for (int j = 1; j < i; j++) {
				f[i] = Math.max(f[i], dp[i][j] - scores[j - 1] + scores[j]);
			}
		}
		int max = 0;
		for (int j = 1; j < N; j++) {
			max = Math.max(max, dp[N][j]);
		}
		return max;
	}
}
