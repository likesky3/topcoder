package review.dp;

import java.util.*;

public class AWhiteBoard {

	public static void main(String[] args) {
		AWhiteBoard obj = new AWhiteBoard();
		int[] A = {0, 0, 0};
		int[] B = {1, 2, 3};
		int[] f = {1, 2};
		System.out.println(obj.expectedLength(A, B, f));
	}
	
	public double expectedLength(int[] A, int[] B, int[] f) {
		int roadsNum = A.length;
		int citiesNum = roadsNum + 1;
		int familiesNum = f.length;
		// calculate neighbors for all cities
		g = new HashMap<>(); // key is city label, value is its neighbors
		for (int i = 0; i < citiesNum; i++) {
			g.put(i, new ArrayList<>());
		}
		for (int i = 0; i < roadsNum; i++) {
			g.get(A[i]).add(B[i]);
			g.get(B[i]).add(A[i]);
		}
		this.A = A;
		this.B = B;
		double fullLen = 0;
		visited = new boolean[citiesNum];
		// for each road, calculate its probability of being a full road
		for (int i = 0; i < roadsNum; i++) {
			double p = 1;
			// memo[i][j]: num of cities the traveler would visit through road j
//			memo = new int[citiesNum][roadsNum][2];
//			for (int[][] arr2 : memo) {
//				for (int[] arr1 : arr2)
//					Arrays.fill(arr1, -1);
//			}
			for (int j = 0; j < familiesNum; j++) {
				int citiesArrived = getNumOfCitiesWeCanReach(f[j], i, false);
				p *= citiesArrived * 1.0 / (citiesNum - 1);
				System.out.printf("=== road=%d, family=%d, citiesArrived=%d, p=%f\n", i, j, citiesArrived, p);
			}
			fullLen += p;
		}
		return fullLen;
	}
	
	private int getNumOfCitiesWeCanReach(int curr, int targetRoad, boolean through) { 
		// the below memo[][][] is not enough to describe a status, would be misleading, not use it
//		if (memo[curr][targetRoad][through ? 1 : 0] != -1)
//			return memo[curr][targetRoad][through ? 1 : 0];
		int result = through ? 1 : 0;
		visited[curr] = true;
		// enumerate all neighbors of the current city 'curr'
		for (int neigh : g.get(curr)) {
			if (visited[neigh]) continue;
			boolean nthrough = through | ((curr == A[targetRoad] && neigh == B[targetRoad])
					|| (curr == B[targetRoad] && neigh == A[targetRoad])); // check if we go through the target or not
			result += getNumOfCitiesWeCanReach(neigh, targetRoad, nthrough);
		}
		visited[curr] = false;
//		memo[curr][targetRoad][through ? 1 : 0] = result;
		System.out.printf("memo[%d][%d][%d]=%d\n", curr, targetRoad, through ? 1 : 0, result);
		return result;
	}
	
	private Map<Integer, List<Integer>> g;
	private boolean[] visited;
	private int[] A;
	private int[] B;
//	private int[][][] memo; 

}
