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
		System.out.println(obj.minTime(time, prec));
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
				System.out.printf("startTime[curr=%d]=%d, startTime[w=%d]=%d\n", curr.label, startTime[curr.label], w.label, startTime[w.label]);
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
}
