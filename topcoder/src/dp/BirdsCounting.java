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
	
	public double computeProbability2(int birdsNumber, int caughtPerDay, int daysNumber, int birdsMarked) {
		// edge case
		if (birdsMarked < caughtPerDay)
			return 0;
		double[][] dp = new double[daysNumber + 1][birdsNumber + 1];
		dp[1][caughtPerDay] = 1;
		c = new int[21][21];
		for (int day = 1; day < daysNumber; day++) {
			int maxMarkedNum = Math.min(birdsMarked, caughtPerDay * day);
			for (int marked = caughtPerDay; marked <= maxMarkedNum; marked++) {
				// since fromNotMark <= birdsNumber - marked, so fromMark starts from (caughtPerDay + marked - birdsNumber)
				for (int fromMark = Math.max(caughtPerDay + marked - birdsNumber, 0); fromMark <= caughtPerDay; fromMark++) {
					int fromNotMark = caughtPerDay - fromMark;
//					System.out.printf("day=%d, marked=%d, fromMark=%d, fromNotMark=%d, notMark=%d\n",
//							day, marked, fromMark, fromNotMark, birdsNumber - marked);
					int a = comb2(fromMark, marked);
					int b = comb2(fromNotMark, birdsNumber - marked);
					int c = comb2(caughtPerDay, birdsNumber);
					dp[day + 1][marked + fromNotMark] += dp[day][marked] * (a * b * 1.0 / c);
//					System.out.printf("a=%d, b=%d, c=%d, dp[%d][%d]=%f\n", a, b, c, day + 1, marked + fromNotMark, dp[day + 1][marked + fromNotMark]);
				}
			}
		}
		return dp[daysNumber][birdsMarked];
	}

	private int[][] c;
	private int comb2(int b, int a) {
		if (c[a][b] > 0) {
			return c[a][b];
		}
		if (b == 0 || b == a) {
			return c[a][b] = 1;
		}
		return comb2(b, a - 1) + comb2(b - 1, a - 1);
	}
}
