package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParallelProgramming {

	public static void main(String[] args) {
		ParallelProgramming obj = new ParallelProgramming();
		int[] time = {150,200,250};
		String[] prec = {"NYN",
				 "NNY",
		 "NNN"};
		System.out.println(obj.minTime(time, prec));
	}
	
	public int minTime(int[] time, String[] prec) {
		Map<Integer, Set<Integer>> precedences  = new HashMap<>();
		int N = time.length;
		for (int i = 0; i < N; i++) {
			Set<Integer> precI = new HashSet<>();
			for (int j = 0; j < N; j++) {
				if (prec[j].charAt(i) == 'Y') {
					precI.add(j);
				}
			}
			precedences.put(i, precI);
		}
		
		int total = 0;
		while (!precedences.isEmpty()) {
			int next = -1;
			int min = Integer.MAX_VALUE;
			for (Integer i : precedences.keySet()) {
				if (precedences.get(i).isEmpty() && time[i] < min) {
					min =time[i];
					next = i;
				}
			}
			if (next == -1)
				return -1;
			precedences.remove(next);
			for (Integer i : precedences.keySet()) {
				if (precedences.get(i).isEmpty()) {
					time[i] -= min;
				}
				precedences.get(i).remove(next);
			}
			total += min;
		}
		return total;
	}

}
