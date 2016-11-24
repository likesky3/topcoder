package dp;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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
//		System.out.println(obj.minTime1(time, prec));
		
		time = new int[]{8, 991, 277, 788, 212, 992, 236, 1000, 1, 239, 6, 11, 80, 129, 945, 796, 24, 48, 316, 37, 640, 667, 140, 927, 5, 924, 328, 245, 931, 1, 388, 991, 663, 523, 463, 81, 997, 998, 211, 774, 12, 32, 930, 6, 193, 981, 985, 959, 332, 603};
		prec = new String[]{"NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNN", "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNYNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNYNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNYNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNYNNNNNNNNNNN", "NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN"};
		System.out.println(obj.minTime1(time, prec));
	}
	
	public int minTime1(int[] time, String[] prec) {
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
			System.out.printf("next=%d, time[%d]=%d, total=%d, remains=%d\n", next, next, time[next], total, precedences.size());
		}
		return total;
	}
	
	public int minTime(int[] time, String[] prec) {
		int n = time.length;
		Node[] graph = readGraph(n, prec);
		Queue<Node> queue = new ArrayDeque<Node>();
		int[] startTime = new int[n];
		Arrays.fill(startTime, 0);
		for (int i = 0; i < n; i++) {
			if (graph[i].inorder == 0) {
				queue.offer(graph[i]);
				startTime[i] = 0;
			}
		}
		int count = 0;
		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			for (Node w : curr.neighs) {
				if (--w.inorder == 0) {
					queue.offer(w);
				}
				// update successor's startTime
				startTime[w.label] = Math.max(startTime[w.label], startTime[curr.label] + time[curr.label]);
//				System.out.printf("startTime[curr=%d]=%d, startTime[w=%d]=%d\n", curr.label, startTime[curr.label], w.label, startTime[w.label]);
			}
			count++;
		}
		
		if (count < n)
			return -1;
		int endTime = 0;
		for (int i = 0; i < n; i++) {
			endTime = Math.max(endTime, startTime[i] + time[i]);
		}
		return endTime;
	}
	
	private Node[] readGraph(int n, String[] prec) {
		Node[] graph = new Node[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new Node(i);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (prec[i].charAt(j) == 'Y') {
					graph[j].inorder++;
					graph[i].neighs.add(graph[j]);
				}
			}
		}
		return graph;
	}
	private class Node {
		int label;
		int inorder;
		List<Node> neighs;
		public Node(int label) {
			this.label = label;
			this.inorder = 0;
			this.neighs = new ArrayList<Node>();
		}
	}
	
	public int minTime2(int[] time, String[] prec) {
		// preprocess
		int N = time.length;
		Proc[] procs = new Proc[N];
		for (int i = 0; i < N; i++) {
			procs[i] = new Proc(i, time[i]);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (j != i && prec[i].charAt(j) == 'Y') {
					if (j == 44) {
//						System.out.printf("i=%d, cost=%d\n", i, procs[i].cost);
					}
					procs[i].successors.add(procs[j]);
					// the below statement is not enough to judge whether i and j forms circle
					// consider 0->1->2 and 2->0
//					if (procs[j].successors.contains(procs[i]))
//						return -1;
					procs[j].indegree++;
				}
			}
		}
		
		int result = 0;
		for (int t = 0; t < N; t++) {
			// select the procs which has no dependent and need the least time to finish execution
			Proc toExec = null;
			for (int i = 0; i < N; i++) {
				if (procs[i].indegree == 0 && procs[i].cost > 0 && (toExec == null || toExec.cost > procs[i].cost)) {
					toExec = procs[i];
				}
			}
			if (toExec == null)
				break;
//			System.out.printf("t=%d, curr proc[%d]: timecost=%d\n", t,toExec.label, toExec.cost);
			// update other peers which are executing at the same time
			for (int i = 0; i < N; i++) {
				if (i != toExec.label && procs[i].indegree == 0 && !toExec.successors.contains(procs[i])
						&& procs[i].cost > 0) {
//					procs[i].cost = Math.max(procs[i].cost - toExec.cost, 0);
					procs[i].cost -= toExec.cost;
					if (procs[i].cost <= 0) { // once forgot!!!!!!
						for (Proc p : procs[i].successors) {
							p.indegree--;
						}
					}
//					System.out.printf("proc[%d].cost=%d\n", i, procs[i].cost);
				}
			}
			// set cost toExec to 0, this is also a sign that this proc has been finished
			result += toExec.cost;
			toExec.cost = 0;
			// update successors
			for (Proc p : toExec.successors) {
				p.indegree--;
			}
		}
		// check if there is circle in the graph
		for (int i = 0; i < N; i++) {
			if (procs[i].cost > 0) {
				return -1;
//				System.out.printf("prcos[%d]:cost=%d, indegree=%d\n", i, procs[i].cost, procs[i].indegree);
			}
		}
		return result;
	}
	
	private class Proc {
		public int label;
		public int cost;
		public int indegree;
		public Set<Proc> successors;
		public Proc(int label, int costTime) {
			this.label = label;
			this.cost = costTime;
			successors = new HashSet<Proc>();
		}
		
	}
}
