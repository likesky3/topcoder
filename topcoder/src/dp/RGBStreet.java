package dp;

public class RGBStreet {

	public static void main(String[] args) {
		RGBStreet obj = new RGBStreet();
		String[] houses = {"30 19 5", "64 77 64", "15 19 97", "4 71 57", "90 86 84", "93 32 91"};
		System.out.println(obj.estimateCost(houses));
	}

	public int estimateCost(String[] houses) {
		// prepare information
		int N = houses.length;
		int[][] cost = new int[N][3];
		for (int i = 0; i < N; i++) {
			String[] a = houses[i].split(" ");
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(a[j]);
			}
		}
		
		// start dp
		int[][] dp = new int[N][3];
		for (int j = 0; j < 3; j++) {
			dp[0][j] = cost[0][j];
		}
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + cost[i][j];
			}
		}
		return Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2]));
	}
}
