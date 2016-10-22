package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContestSchedule {

	public static void main(String[] args) {
		ContestSchedule obj = new ContestSchedule();
		String[] contests = {"1 20 20", "30 40 60", "15 35 90"};
		System.out.printf("recv=%f, correct=%b\n", obj.expectedWinnings(contests), 
				obj.expectedWinnings(contests) == 0.9);
		contests = new String[]{"1 10 100", "20 30 100"};
		System.out.printf("recv=%f, correct=%b\n", obj.expectedWinnings(contests), 
				obj.expectedWinnings(contests) == 2.0);
	}	
	
	public double expectedWinnings(String[] contests) {
		int n = contests.length;
		Contest[] c = new Contest[n];
		for (int i = 0; i < n; i++) {
			String[] tmp = contests[i].split(" ");
			c[i]= new Contest(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
		}
		int[] endArray = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			endArray[i] = c[i - 1].end;
		}
		Arrays.sort(endArray);
		// key is end time, value is the max win probability John get by the end time
		// since store each minute is too expensive, we only store end time of each contests
		Map<Integer, Integer> map = new HashMap<>(); 
		map.put(0, 0);
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < n; j++) {
				if (c[j].start >= endArray[i]) {
					int oldValue = map.getOrDefault(c[j].end, 0);
					int newValue = map.get(endArray[i]) + c[j].probability;
					if (newValue > oldValue)
						map.put(c[j].end, newValue);
				}
			}
		}
		int max = 0;
		for (int i = 0; i <= n; i++) {
			max = Math.max(max, map.get(endArray[i]));
		}
		return max / 100.0;
	}
	class Contest {
		public int start;
		public int end;
		public int probability;
		public Contest(int s, int e, int p) {
			this.start = s;
			this.end = e;
			this.probability = p;
		}
	}
}
