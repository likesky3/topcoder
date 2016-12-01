package review.dp;

import java.util.Arrays;

public class PowerPlants {

	public static void main(String[] args) {
		PowerPlants obj = new PowerPlants();
		String[] connectionCost = {"C4J8HNHKEU5YN", "HTWL0PKM6PR0S", "8VMRL3NZ85BLS", "F30R1H39142WK", "HU28Y6TRAOSV0", "BZ6TIPXSGFB1Y", "CFQ2V1F5DS8B0", "EBLEKOGEQ1L49", "Y47X5GCHYICSW", "MYNRFEL9VLAYU", "AYLLWO4QLIV2N", "040511G4K6A2Z", "50N4FHYXD0M92"};
		String plantList = "YNNNNNNYNNNNN";
		int numPlants = 11;
		System.out.println(obj.minCost(connectionCost, plantList, numPlants));

		connectionCost = new String[]{"012", "123", "234"};
		plantList = "NNY";
		numPlants = 2;
		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		
//		{{"Y4", "HS"}, "YY", 1}
	}

	public int minCost(String[] connectionCost, String plantList, int numPlants) {
		int N = plantList.length();
		// preprocess input
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char c = connectionCost[i].charAt(j);
				if (c >= '0' && c <= '9') {
					board[i][j] = c - '0';
				} else {
					board[i][j] = c - 'A' + 10;
				}
			}
		}
		int init = 0;
		for (int i = 0; i < N; i++) {
			if (plantList.charAt(i) == 'Y') {
				init |= 1 << i;
			}
		}
		int finalMask = (1 << N) - 1;
		if (Integer.bitCount(init) >= numPlants) // take special care of this, or you can move to the dp process to check
			return 0;
//		System.out.printf("init=%s\n", Integer.toBinaryString(init));

		int[] dp = new int[1 << N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[init] = 0;
		int result = Integer.MAX_VALUE;
		for (int mask = init; mask < finalMask; mask++) {
			if (dp[mask] == Integer.MAX_VALUE) continue;
			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) == 0) continue;
				for (int j = 0; j < N; j++) {
					if ((mask & (1 << j)) == 0) {
						int nmask = mask | (1 << j);
						dp[nmask] = Math.min(dp[nmask], dp[mask] + board[i][j]); 
						if (Integer.bitCount(nmask) >= numPlants) {
							result = Math.min(result, dp[nmask]);
						}
//						System.out.printf("i=%d, j=%d, mask=%s, dp[%s]=%d, dp[mask]=%d, board[%d][%d]=%d\n", i, j, Integer.toBinaryString(mask), 
//								Integer.toBinaryString(nmask), dp[nmask], dp[mask], i, j, board[i][j]);
					}
				}
			}
		}
		return result;
	}

}
