package dp;

public class Excavations2 {

	public static void main(String[] args) {
		Excavations2 obj = new Excavations2();
		int[] kind = {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50};
		int[] found = {50};
		System.out.println(obj.count(kind, found, 21));
	}
	
	public long count(int[] kind, int[] found, int K) {
		int N = kind.length;
		int M = found.length;
		// prepare some information
		int[] num = new int[51]; //num[i]: number of buildings of one type i
		for (int i = 0; i < N; i++) {
			num[kind[i]]++;
		}
		int[] total = new int[M + 1]; // total[i]: total number of buildings of the first i types in found[]
		for (int i = 1; i <= M; i++) {
			total[i] = total[i-1] + num[found[i-1]];
//			System.out.printf("num[%d]=%d, total[%d]=%d\n", found[i-1], num[found[i-1]], i, total[i]);
		}
		generateComb();
		// start dp
		// dp[i][j]: how many j-tuples the first i types in found[] would offer
		long[][] dp = new long[M+1][K+1];
		dp[0][0] = 1;
		for (int i = 1; i <= M; i++) {
			int largestTuple = Math.min(K, total[i]);
			for (int j = i; j <= largestTuple; j++) {
				// max number for type found[i-1] to offer, not exceed the total number of this type, 
				// also should be small enough to allow the first i-1 types to offer 1 building each 
				int maxOffer = Math.min(num[found[i-1]], j - (i - 1));
//				System.out.printf("i=%d, j=%d, largestTuple=%d, maxOffer=%d\n", i, j, largestTuple, maxOffer);
				for (int k = 1; k <= maxOffer; k++) {
					if (i == 1 && (j - k) >0)
						continue;
					dp[i][j] += c[num[found[i-1]]][k] * dp[i-1][j-k];
//					System.out.printf("num[%d]=%d, k=%d, comb=%d, dp[%d][%d]=%d\n", found[i-1], num[found[i-1]],
//							k, c[num[found[i-1]]][k], i, j, dp[i][j]);
				}
				
			}
		}
		return dp[M][K];
	}

	private long[][] c;
	private int limit = 51;
	private void generateComb() {
		c = new long[limit][limit];
		c[0][0] = 1;
		for (int i = 1; i < limit; i++) {
			c[i][0] = 1;
			c[i][i] = 1;
			for (int j = 1; j < i; j++) {
				c[i][j] = c[i-1][j] + c[i-1][j-1];
			}
		}
	}
	// would overflow
	private long combination(int a, int b) {
		if (b > a / 2)
			return combination(a, a - b);
		long numerator = 1;
		for (long i = a - b + 1; i <= a; i++) {
			numerator *= i;
		}
		long denominator = 1;
		for (long i = 2; i <= b; i++) {
			denominator *= i;
		}
		return numerator / denominator;
	}
}
