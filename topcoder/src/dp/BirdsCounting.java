package dp;

public class BirdsCounting {

	public static void main(String[] args) {
		BirdsCounting obj = new BirdsCounting();
//		System.out.println(obj.computeProbablity(2, 1, 2, 1));
//		System.out.println(obj.computeProbablity(2, 1, 2, 2));
//		System.out.println(obj.computeProbablity(3, 1, 2, 2));
//		System.out.println(obj.computeProbablity(3, 1, 5, 1));
		System.out.println(obj.computeProbablity(13, 1, 2, 0));
		System.out.println(obj.computeProbablity(4, 4, 1, 3));
	}
	
	public double computeProbablity(int birdsNumber, int caughtPerDay, int daysNumber, int birdsMarked) {
		// consider the edge case
		if (birdsMarked < caughtPerDay)
			return 0;
		double[][] prop = new double[caughtPerDay + 1][birdsMarked + 1];
		for (int prev = caughtPerDay; prev <= birdsMarked; prev++) {
			for (int add = 0; add <= caughtPerDay && prev + add <= birdsMarked; add++) {
				prop[add][prev] = 1.0 * comb(birdsNumber - prev, add) * comb(prev, caughtPerDay - add) / comb(birdsNumber, caughtPerDay);
//				System.out.printf("prop[%d][%d]=%f\n", add, prev, prop[add][prev]);
			}
		}
		double[][] dp = new double[daysNumber + 1][birdsMarked + 1];
		dp[1][caughtPerDay] = 1;
		for (int d = 2; d <= daysNumber; d++) {
			for (int m = caughtPerDay; m <= birdsMarked; m++) {
				for (int add = 0; add <= caughtPerDay && add <= m - caughtPerDay; add++) {
					dp[d][m] += prop[add][m - add] * dp[d - 1][m - add];
//					System.out.printf("dp[%d][%d]=%f, add=%d\n", d, m, dp[d][m], add);
				}
			}
		}
		return dp[daysNumber][birdsMarked];
	}
	private long comb(int a, int b) {
		long numerator = 1;
		for (int i = 0; i < b; i++) {
			numerator *= (a - i);
		}
		long denumerator = 1;
		for (int i = 1; i <= b; i++) {
			denumerator *= i;
		}
		return numerator / denumerator;
	}
}
