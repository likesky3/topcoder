package review.dp;

import java.util.*;

public class ParallelProgramming {

	public static void main(String[] args) {
		ParallelProgramming obj = new ParallelProgramming();
		int[] time = {345,335,325,350,321,620}; 
		String[] prec = {"NNNNNN",
				 "NNYYYY",
				 "YNNNNN",
				 "NNYNYN",
				 "NNNNNN",
				 "NNNNNN"};
		System.out.println(obj.minTime2(time, prec) == 1355);
		
		time = new int[] {150, 200, 250};
		prec = new String[]{"NYN",
				"NNY",
				"YNN"};
		System.out.println(obj.minTime2(time, prec) == -1);
		
		time = new int[]{8, 991, 277, 788, 212, 992, 236, 1000, 1, 239, 6, 11, 80, 129, 945, 796, 24, 48, 316, 37, 640, 667, 140, 927, 5, 924, 328, 245, 931, 1, 388, 991, 663, 523, 463, 81, 997, 998, 211, 774, 12, 32, 930, 6, 193, 981, 985, 959, 332, 603};
		prec = new String[]{"NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNN", "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNYNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNYNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN"};
		System.out.println(obj.minTime2(time, prec) == 2010);
	}
	
	public int minTime(int[] time, String[] prec) {
		// prepare a precedences map
		int N = time.length;
		Map<Integer, Set<Integer>> precedence = new HashMap<Integer, Set<Integer>>();
		for (int i = 0; i < N; i++) {
			precedence.put(i, new HashSet<>());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (prec[i].charAt(j) == 'Y') {
					precedence.get(j).add(i);
				}
			}
		}
		// start calculation time needed
		int total = 0;
		while (!precedence.isEmpty()) {
			int minTime = Integer.MAX_VALUE;
			int next = -1;
			// find the quickest free process which some execution time
			for (int p : precedence.keySet()) {
				if (precedence.get(p).isEmpty() && time[p] < minTime) { // this process is free to go
					minTime = time[p];
					next = p;
				}
			}
			if (next == -1)
				return -1;
			precedence.remove(next); // remove the selected proc
			total += time[next];
			System.out.printf("next=%d, time[%d]=%d, total=%d, remains=%d\n", next, next, time[next], total, precedence.size());
			
			for (int p : precedence.keySet()) {
				// update time cost of ohter free processes
				if (precedence.get(p).isEmpty()) {
					time[p] -= time[next]; // we can not directly remove the process whose time <= 0, since there may be some other proc dependent on it
					// since time[next] is the smallest among all the free proc, so after -time[next], time[p] is always >= 0
				}
				// update the status of the 'next' process's successors
				if (precedence.get(p).contains(next)) {
					precedence.get(p).remove(next);
				}
			}	
		}
		return total;
	}
	
	public int minTime2(int[] time, String[] prec) {
		int N = time.length;
		// read graph
		Proc[] procs = new Proc[N];
		for (int i = 0; i < N; i++) {
			procs[i] = new Proc(i);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (prec[i].charAt(j) == 'Y') {
					procs[j].indegree++;
					procs[i].neighs.add(procs[j]);
				}
			}
		}
		int[] startTime = new int[N];
		Queue<Proc> queue = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			if (procs[i].indegree == 0)
				queue.offer(procs[i]);
		}
		
		int count = 0;
		while (!queue.isEmpty()) {
			Proc p = queue.poll();
			for (Proc s : p.neighs) {
				startTime[s.label] = Math.max(startTime[s.label], startTime[p.label] + time[p.label]);
				s.indegree--;
				if (s.indegree == 0)
					queue.offer(s);
			}
			count++;
		}
		if (count < N)
			return -1;
		int maxEnd = 0;
		for (int i = 0; i < N; i++) {
			maxEnd = Math.max(maxEnd, startTime[i] + time[i]);
		}
		return maxEnd;
	}
	
	private class Proc {
		public int label;
		public int indegree;
		public Set<Proc> neighs;
		public Proc(int i) {
			label = i;
			indegree = 0;
			neighs = new HashSet<>();
		}
	}
}
