package dp;

import java.util.Arrays;

public class PowerPlants {

	public static void main(String[] args) {
		PowerPlants obj = new PowerPlants();
		String[] connectionCost = {"C4J8HNHKEU5YN", "HTWL0PKM6PR0S", "8VMRL3NZ85BLS", "F30R1H39142WK", "HU28Y6TRAOSV0", "BZ6TIPXSGFB1Y", "CFQ2V1F5DS8B0", "EBLEKOGEQ1L49", "Y47X5GCHYICSW", "MYNRFEL9VLAYU", "AYLLWO4QLIV2N", "040511G4K6A2Z", "50N4FHYXD0M92"};
		String plantList = "YNNNNNNYNNNNN";
		int numPlants = 11;
//		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		connectionCost = new String[]{"1ABCD",
				 "35HF8",
				 "FDM31",
				 "AME93",
				 "01390"};
		plantList = "NYNNN";
		numPlants = 5;
		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		
		connectionCost = new String[]{"01","20"};
		plantList = "YN";
		numPlants = 2;
//		System.out.println(obj.minCost(connectionCost, plantList, numPlants));
		
	}
	
	public int minCost(String[] connectionCost, String plantList, int numPlants) {
		N = connectionCost.length;
		// prepare information
		costTable = new int[N][N];
		memo = new int[1<<N][1<<N];
		for (int i = 0; i < memo.length; i++) {
			Arrays.fill(memo[i], -1);
		}
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
		for (int candStatus = 1; candStatus < 1 << N; candStatus++) {
			if ((candStatus & initStatus) > 0 && Integer.bitCount(candStatus) == numPlants) {
				System.out.printf(">>>init=%s, cand=%s\n", Integer.toBinaryString(initStatus),
						Integer.toBinaryString(candStatus));
				recur(initStatus, candStatus, 0);
			}
		}
		return minimumCost != Integer.MAX_VALUE ? minimumCost : 0;
	}
	
	private int N;
	private int[][] costTable;
	private int minimumCost = Integer.MAX_VALUE;
	private int[][] memo;
	private int recur(int currStatus, int finalStatus, int cost) {
		if (memo[currStatus][finalStatus] != -1)
			return cost + memo[currStatus][finalStatus];
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
					}
				}
			}
		}
		System.out.printf("curr=%s, final=%s, cost=%d, res=%d\n", Integer.toBinaryString(currStatus),
				Integer.toBinaryString(finalStatus), cost, res);
		memo[currStatus][finalStatus] = res;
		return res;
	}
}
