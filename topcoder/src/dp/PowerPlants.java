package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PowerPlants {

	public static void main(String[] args) {
		PowerPlants obj = new PowerPlants();
		// below case test timeout
		String[] connectionCost = {"C4J8HNHKEU5YN", "HTWL0PKM6PR0S", "8VMRL3NZ85BLS", "F30R1H39142WK", "HU28Y6TRAOSV0", "BZ6TIPXSGFB1Y", "CFQ2V1F5DS8B0", "EBLEKOGEQ1L49", "Y47X5GCHYICSW", "MYNRFEL9VLAYU", "AYLLWO4QLIV2N", "040511G4K6A2Z", "50N4FHYXD0M92"};
		String plantList = "YNNNNNNYNNNNN";
		int numPlants = 11;
		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		connectionCost = new String[]{"1ABCD",
				 "35HF8",
				 "FDM31",
				 "AME93",
				 "01390"};
		plantList = "NYNNN";
		numPlants = 5;
		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		
		connectionCost = new String[]{"AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA", "AAAAAAAAAAAAAAAA"};
		plantList = "NNNNYNNNNNNNNNNN";
		numPlants = 16;
		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		
	}
	
	public int minCost(String[] connectionCost, String plantList, int numPlants) {
		N = connectionCost.length;
		// prepare information
		costTable = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char c = connectionCost[i].charAt(j);
				if (c >= '0' && c <= '9') {
					costTable[i][j] = c - '0';
				} else {
					costTable[i][j] = 10 + c - 'A';
				}
			}
		}
		// use bitMask to indicate the status, bit i will be set to 1 if the i-th plant is powered
		int initStatus = 0;
		for (int i = 0; i < N; i++) {
			if (plantList.charAt(i) == 'Y') {
				initStatus |= 1 << i;
			}
		}
		// start dp
		int[] dp = new int[1 << N]; // dp[i]: cost from initStatus to status represented as i
		Arrays.fill(dp, INF);
		dp[initStatus] = 0;
		int minCost = INF;
		for (int status = initStatus; status < 1<<N; status++) {
			if ((status & initStatus) == initStatus && Integer.bitCount(status) >= numPlants) {
				// reach a final status where no less than numPlants have been powered
				minCost = Math.min(minCost, dp[status]);
			} else {
				for (int j = 0; j < N; j++) {
					if ((status & (1 << j)) == 0) { // plant j has not been powered
						int lowCost = INF; // lowest cost to power on plant j using working plant in status
						for (int k = 0; k < N; k++) {
							if ((status & (1 << k)) > 0) {
								lowCost = Math.min(lowCost, costTable[k][j]);
							}
						}
						dp[status | (1 << j)] = Math.min(dp[status | (1 << j)], dp[status] + lowCost);
					}
				}
				
			}
		}
		return minCost;
	}
	
	public int minCost1(String[] connectionCost, String plantList, int numPlants) {
		N = connectionCost.length;
		// prepare information
		costTable = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				char c = connectionCost[i].charAt(j);
				if (c >= '0' && c <= '9') {
					costTable[i][j] = c - '0';
				} else {
					costTable[i][j] = 10 + c - 'A';
				}
			}
		}
		// use bitMask to indicate the status, bit i will be set to 1 if the i-th plant is powered
		int initStatus = 0;
		for (int i = 0; i < N; i++) {
			if (plantList.charAt(i) == 'Y') {
				initStatus |= 1 << i;
			}
		}
		
//		memo = new int[1<<N][1<<N]; // would lead to heap 
		memo2 = new int[1<<N][N];
		for (int i = 0; i < memo2.length; i++) {
			Arrays.fill(memo2[i], -1);
		}
//		cache = new HashMap<>();
		for (int candStatus = 1; candStatus < 1 << N; candStatus++) {
			if ((candStatus & initStatus) == initStatus && Integer.bitCount(candStatus) == numPlants) {
				System.out.printf(">>>init=%s, cand=%s\n", Integer.toBinaryString(initStatus),
						Integer.toBinaryString(candStatus));
				finalStatus = candStatus;
				recur2(initStatus, 0);
				
				// code using recur()
//				memo = new int[1<<N][36*N];
//				for (int i = 0; i < memo.length; i++) {
//					Arrays.fill(memo[i], -1);
//				}
//				recur(initStatus, candStatus, 0);
				
				
			}
		}
		return minimumCost != Integer.MAX_VALUE ? minimumCost : 0;
	}
	
	private final int INF = 10000;
	private int N;
	private int[][] costTable;
	private int minimumCost = Integer.MAX_VALUE;
	private int[][] memo;
	
	private int finalStatus;
	private int[][] memo2;
	private Map<String, Integer> cache;
	private void recur2(int currStatus, int cost) {
//		System.out.printf("currStatus=%s, cost=%d\n", Integer.toBinaryString(currStatus), cost);
		if (currStatus == finalStatus) {
			minimumCost = Math.min(minimumCost, cost);
			return;
		}
		if (cost >= minimumCost)
			return;
		for (int j = 0; j < N; j++) {
			if ((currStatus & (1<<j)) == 0 && (finalStatus & (1<<j)) > 0) {
				int nextStatus = currStatus | (1<<j);
				if (memo2[currStatus][j] == -1) {
					int minCost = Integer.MAX_VALUE;
					for (int i = 0; i < N; i++) {
						if ((currStatus & (1 << i)) > 0) {
							minCost = Math.min(minCost, costTable[i][j]);
						}
					}
					memo2[currStatus][j] = minCost;
				}
				recur2(nextStatus, cost + memo2[currStatus][j]);
			}
		}
	}
	

	private int recur(int currStatus, int finalStatus, int cost) {
		if (memo[currStatus][cost] != -1)
			return  memo[currStatus][cost];
		if (currStatus == finalStatus) {
			minimumCost = Math.min(minimumCost, cost);
			return minimumCost;
		}
		
		int res = Integer.MAX_VALUE;
		for (int j = 0; j < N; j++) {
			// search for a plant which need to be restart to power on to reach finalstatus
			if ((currStatus & (1<<j)) == 0 && (finalStatus & (1<<j)) > 0) {
				// search for a plant which is working
				for (int i = 0; i < N; i++) {
					if ((currStatus & (1 << i)) > 0) {
						res = Math.min(res, recur(currStatus | (1<<j), finalStatus, cost + costTable[i][j]));
//						System.out.printf("curr=%s, next=%s, cost=%d, res=%d\n", Integer.toBinaryString(currStatus),
//								Integer.toBinaryString(currStatus | (1<<j)), cost, res);
					}
				}
			}
		}
		memo[currStatus][cost] = res;
//		System.out.printf("curr=%s, final=%s, cost=%d, memo[%s][%s]=%d\n", Integer.toBinaryString(currStatus),
//				Integer.toBinaryString(finalStatus), cost, res);
//		System.out.printf("cost=%d, memo[%s][%s]=%d\n", cost, Integer.toBinaryString(currStatus),
//				Integer.toBinaryString(finalStatus), res);
		return res;
	}
	
//	private void recur2(int currStatus, int cost) {
//		if (currStatus == finalStatus) {
//			minimumCost = Math.min(minimumCost, cost);
//			return;
//		}
//		if (cost >= minimumCost)
//			return;
//		for (int j = 0; j < N; j++) {
//			if ((currStatus & (1<<j)) == 0 && (finalStatus & (1<<j)) > 0) {
//				int nextStatus = currStatus | (1<<j);
//				String key = "" + currStatus + j;
//				if (!cache.containsKey(key)) {
//					int minCost = Integer.MAX_VALUE;
//					for (int i = 0; i < N; i++) {
//						if ((currStatus & (1 << i)) > 0) {
//							minCost = Math.min(minCost, costTable[i][j]);
//						}
//					}
//					cache.put(key, minCost);
//				}
//				recur2(nextStatus, cost + cache.get(key));
//			}
//		}
//	}
}
