package review.dp;

public class Excavations2 {

	public static void main(String[] args) {
		Excavations2 obj = new Excavations2();
		int[] kind = {1, 2, 1, 1, 2, 3, 4, 3, 2, 2};
		int[] found = {4, 2};
		int K = 3;
		System.out.println(obj.count(kind, found, K));
	}
	
	public long count(int[] kind, int[] found, int K) {
		int N = kind.length;
		int M = found.length;
		int[] count = new int[51]; // number of each type
		for (int i = 0; i < N; i++) {
			count[kind[i]]++;
		}
		int[] total = new int[M + 1]; // total number of buildings of the first i types in found[]
		for (int i = 1; i <= M; i++) {
			total[i] = total[i - 1] + count[found[i - 1]]; 
		}
		// calculate combination[i][j];
		int[][] c = new int[51][51];
		c[0][0] = 1;
		for (int i = 1; i <= 50; i++) {
			c[i][0] = c[i][i] = 1;
			for (int j = 1; j < i; j++) {
				c[i][j] = c[i - 1][j] + c[i - 1][j - 1];
			}
		}
		int[][] dp = new int[M + 1][K + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= M; i++) {
			int maxTupleSize = Math.min(total[i], K);
			for (int j = i; j <= maxTupleSize; j++) {
				// not exceed type's capacity, also ensure each of the previous types occupied at least one
				int maxOffer = Math.min(count[found[i - 1]], j - (i - 1));
				for (int k = 1; k <= maxOffer; k++) {
					dp[i][j] += dp[i - 1][j - k] * c[count[found[i - 1]]][k];
				}
			}
		}
		return dp[M][K];
	}

}
