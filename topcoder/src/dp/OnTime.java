package dp;

import java.util.ArrayList;
import java.util.List;

public class OnTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] buses = {"4 9 40 8080 1886", "5 1 305 7627 2042", "8 0 1496 2844 3229", "4 0 3035 1497 6538", "2 6 5086 267 4256", "2 5 2620 7283 5405", "7 8 2814 6313 6645", "8 0 5826 1037 4473", "0 1 952 3101 8975", "0 2 5695 623 2227", "5 7 9486 384 1746", "1 2 2591 5600 4713", "2 9 1891 1645 6498", "2 3 2019 6981 2571", "2 9 2062 333 4428", "9 2 1049 6399 5098", "6 8 242 8713 9460", "2 0 6618 959 2020", "2 8 7099 2178 8033", "3 9 420 2022 878", "6 1 3019 3579 7671", "4 8 1785 678 4516", "2 7 635 3307 7028", "4 9 4792 3622 3277", "8 6 3534 4137 5837", "9 6 2635 2571 370", "4 0 7096 24 2986", "0 7 5560 777 3378", "4 8 4343 4183 6671", "3 0 2733 3171 4318", "5 0 279 4431 2301", "1 0 2155 6212 3770", "0 9 5659 1134 7609", "8 2 1419 4615 4726", "5 4 4761 2930 5563", "5 9 6886 908 3945", "0 7 3578 4230 2970", "7 1 1439 297 1520", "5 8 1123 8239 870", "8 4 6476 3154 311", "5 1 2107 6952 5526", "4 3 6654 35 8230", "8 7 1810 7497 9427"};
		OnTime obj = new OnTime();
//		System.out.println(obj.minCost(10, 5747, buses));
		buses = new String[]{"0 1 0 4 3", "1 2 5 3 4", "1 2 5 4 3", "0 2 0 1001 2"};
		System.out.println(obj.minCost(3, 1000, buses));
	}
	
	public int minCost(int N, int T, String[] buses) {
		this.N = N;
		this.T = T;
		List<Bus> beginningBuses = readBusInfo(buses);
		int res = MAX;
		for (Bus firstBus : beginningBuses) {
			res = Math.min(res, recur(firstBus, firstBus.endStation, firstBus.departureTime + firstBus.totalTime, firstBus.cost));
		}
		if (res >= MAX)
			return -1;
		else
			return res;
	}
	
	private List<Bus> readBusInfo(String[] buses) {
		int num = buses.length;
		int[][] busInfo = new int[num][5];
		for (int i = 0; i < num; i++) {
			String[] tmp = buses[i].split(" ");
			for (int j = 0; j < 5; j++) {
				busInfo[i][j] = Integer.parseInt(tmp[j]);
			}
		}
		List<Bus> busList = new ArrayList<>();
		List<Bus> beginningBuses = new ArrayList<>();
		for (int i = 0; i < num; i++) {
			Bus bus = new Bus(busInfo[i][0], busInfo[i][1], busInfo[i][2], busInfo[i][3], busInfo[i][4]);
			busList.add(bus);
			if (bus.startStation == 0) {
				beginningBuses.add(bus);
			}
		}
		
		for (int i = 0; i < num; i++) {
			Bus curr = busList.get(i);
			for (int j = 0; j < num; j++) {
				if (i == j)
					continue;
				Bus cand = busList.get(j);
				if (cand.startStation == curr.endStation && cand.departureTime > (curr.departureTime + curr.totalTime)) {
					curr.nextBuses.add(cand);
				}
				
			}
		}
		return beginningBuses;
	}
	
	private int recur(Bus bus, int currStation, int currTime, int currCost) {
		if (currStation == N - 1) {
			return currTime <= T ? currCost : MAX;
		}
		int res = MAX;
		for (Bus next : bus.nextBuses) {
			if (next.departureTime + next.totalTime > T)
				continue;
			res = Math.min(recur(next, next.endStation, next.departureTime + next.totalTime, currCost + next.cost) , res);	
		}
		return res;
	}
	
	private int N;
	private int T;
	private int MAX = 60000000;
	private class Bus {
		int startStation;
		int endStation;
		int departureTime;
		int totalTime;
		int cost;
		List<Bus> nextBuses;
		public Bus(int a, int b, int departure, int time, int cost) {
			this.startStation = a;
			this.endStation = b;
			this.departureTime = departure;
			this.totalTime = time;
			this.cost = cost;
			this.nextBuses = new ArrayList<>();
		}
	}

}
