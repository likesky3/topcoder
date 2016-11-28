package review.dp;

public class OnTime {

	public static void main(String[] args) {
		OnTime obj = new OnTime();
		String[] buses = {"0 1 0 4 3", "1 2 5 3 4"};
//		System.out.println(obj.minCost(3, 8, buses) == 7);
//		buses = new String[] {"0 3 1 6 15", "0 6 0 23 20", "6 2 25 15 30", "6 1 30 15 40", "3 1 15 35 10", 
//				 "3 2 30 80 40", "1 5 55 25 25", "1 2 49 31 10", "2 8 85 10 10", "5 8 83 15 5"};
//		System.out.println(obj.minCost(9, 100, buses) == 55);
//		buses = new String[]{"0 1 0 4 3", "1 2 5 3 4", "1 2 5 4 3", "0 2 0 1001 2"};
//		System.out.println(obj.minCost(3, 1000, buses) == 6);
//		buses = new String[]{"0 1 0 5 1", "1 2 6 1 40", "0 1 1 2 5", "1 2 4 2 5"};
//		System.out.println(obj.minCost(3, 7, buses) == 10);
//		buses = new String[]{"0 1 0 5 3", "1 2 5 3 4"};
//		System.out.println(obj.minCost(3, 8, buses) == -1);
		buses = new String[]{"6 2 1140 35 457108", "7 9 331 136 977561", "5 8 1497 24 488134", "9 1 660 37 992699", "2 5 1224 10 811244", "3 6 995 81 846237", "0 7 187 110 998240", "4 3 923 12 931014", "1 4 803 93 833843", "8 49 1639 26 505691", "24 44 76 1602 410933", "19 13 596 383 295648", "44 36 107 1409 566782", "13 41 563 441 913290", "31 27 801 666 990195", "32 15 1082 383 904537", "33 27 91 818 981022", "0 44 1256 286 995366", "35 14 310 54 627211", "26 32 320 1435 244534", "25 27 360 526 562018", "23 45 589 475 403950", "4 30 552 1248 728519", "9 28 160 1009 34262"};
		System.out.println(obj.minCost(50, 1855, buses));
		
		buses = new String[] {"2 49 4766 153 246165", "1 2 2103 2344 639551", "0 1 118 1013 248923", "31 3 4605 607 387316", "34 30 2501 1287 610417"};
//		System.out.println(obj.minCost(50, 5993, buses) == 1134639);

	}
	
	// do not need memo, since there is little repetition
	public int minCost(int N, int T, String[] buses) {
		this.N = N;
		this.T = T;
		M = buses.length;
		busesInfo = new Bus[M];
		for (int i = 0; i < M; i++) {
			String[] tmp = buses[i].split(" ");
			int[] tmp2 = new int[tmp.length];
			for (int j = 0; j < tmp.length; j++) {
				tmp2[j] = Integer.parseInt(tmp[j]);
			}
			busesInfo[i] = new Bus(tmp2[0], tmp2[1], tmp2[2], tmp2[3], tmp2[4]);
		}
		int minCost = INF;
		for (int i = 0; i < M; i++) {
			if (busesInfo[i].beg == 0) {
				minCost = Math.min(busesInfo[i].cost + recur(i, busesInfo[i].end, busesInfo[i].depart + busesInfo[i].time), minCost);
			}
		}
		return minCost >= INF ? -1 : minCost;
	}
	
	private int M;
	private Bus[] busesInfo;
	private int N;
	private int T;
	private final int INF = 50000001;
	private int recur(int i, int stationNow, int usedTime) {
		if (stationNow == N - 1) {
			return (usedTime <= T) ? 0 : INF;
		} else if (usedTime >= T) {
			return INF;
		}
		
		Bus prev = busesInfo[i];
		int minCost = INF;
		for (int j = 0; j < M; j++) {
			if (j == i) continue;
			Bus curr = busesInfo[j];
			if (curr.beg == prev.end && curr.depart > usedTime) {
				minCost = Math.min(minCost, curr.cost + recur(j, curr.end, curr.depart + curr.time));
//				System.out.printf("i=%d, j =%d, stationNow=%d, usedTime=%d, minCost=%d\n", i, j, stationNow, usedTime, minCost);
			}
		}
//		System.out.printf("---i=%d, stationNow=%d, usedTime=%d, minCost=%d\n", i, stationNow, usedTime, minCost);
		return minCost;
	}
	
	private class Bus {
		int beg;
		int end;
		int depart;
		int time;
		int cost;
		public Bus(int beg, int end, int depart, int time, int cost) {
			this.beg = beg;
			this.end = end;
			this.depart = depart;
			this.time = time;
			this.cost = cost;
		}
	}
}
