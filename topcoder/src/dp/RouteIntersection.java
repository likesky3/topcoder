package dp;

import java.util.HashMap;
import java.util.Map;

public class RouteIntersection {
	public String isValid(int N, int[] coords, String moves) {
		HashMap<Integer, Map<Integer, Integer>> dp = new HashMap<Integer, Map<Integer, Integer>>() ;
		Map<Integer, Integer> init = new HashMap<>();
		dp.put(-1, init);
		int M = moves.length();
		for (int i = 0; i < M ; i++) {
			Map<Integer, Integer> curr = new HashMap<>();
			curr.putAll(dp.get(i - 1)); // copy contents of dp[i-1]
			dp.put(i, curr); // fill dp[i]
			// update dp[i] with the current move
			if (!curr.containsKey(coords[i])) {
				curr.put(coords[i], 0);
			}
			if (moves.charAt(i) == '+') {
				curr.put(coords[i], curr.get(coords[i]) + 1);
				if (curr.get(coords[i]) == 0)
					curr.remove(coords[i]);
			} else {
				curr.put(coords[i], curr.get(coords[i]) - 1);
				if (curr.get(coords[i]) == 0)
					curr.remove(coords[i]);
			}
			// compare dp[i] with the previous points
			for (int j = -1; j < i - 1; j++) {
				Map<Integer, Integer> prev= dp.get(j);
				if (curr.size() != prev.size()) // surely not same
					continue;
				boolean findDiff = false;
				for (int key : curr.keySet()) {
					if (!prev.containsKey(key) || prev.get(key) != curr.get(key)) {
						findDiff = true;
						break;
					}
				}
				if (!findDiff) {
					return "NOT VALID";
				}
			}
//			System.out.println("----------i=" + i);
//			for (int key: curr.keySet()) {
//				System.out.printf("\tkey=%d, value=%d\n", key, curr.get(key));
//			}
		}
		return "VALID";
	}
	
	public String isValid1(int N, int[] coorde, String moves) {
		for (int i = 0; i < coorde.length; i++) {
			Map<Integer, Node> map = new HashMap<>();
			int nonZeroNodes = 0;
			for (int j = i; j < coorde.length; j++) {
				Node node = map.get(coorde[j]);
				if (!map.containsKey(coorde[j])) {
					node = new Node(coorde[j], 0);
					map.put(coorde[j], node);
				}
				if (node.coordinate != 0) 
					nonZeroNodes--;
				if (moves.charAt(j) == '+')
					node.coordinate++;
				else
					node.coordinate--;
				if (node.coordinate != 0)
					nonZeroNodes++;
				if (nonZeroNodes == 0)
					return "NOT VALID";
			}
		}
		return "VALID";
	}
	
	private static class Node {
		int dimension;
		int coordinate;
		public Node(int dimension, int coordinate) {
			this.dimension = dimension;
			this.coordinate = coordinate;
		}
	}
	
	public static void main(String[] args) {
		RouteIntersection obj = new RouteIntersection();
		int N = 344447;
		int[] coords = {132,51717,628,344447,628,51717,344447,2};
		String moves = "+-++-+--";
		System.out.println(obj.isValid(N, coords, moves));
	}
}
