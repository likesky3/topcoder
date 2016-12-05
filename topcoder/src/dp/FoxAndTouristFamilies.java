package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoxAndTouristFamilies {

	public static void main(String[] args) {
		int[] A = {0, 1, 1, 0, 4, 4};
		int[] B = {1, 2, 3, 4, 5, 6};
		FoxAndTouristFamilies obj = new FoxAndTouristFamilies();
		obj.visited = new boolean[A.length + 1];
//		System.out.println(obj.dfs(A, B, 5, 3, false));
		
		A = new int[]{0};
		B = new int[]{1};
		int[] f = {0};
//		System.out.println(obj.expectedLength(A, B, f));
		
		A = new int[] {0, 0, 0};
		B = new int[] {1, 2, 3};
		f = new int[]{1, 2};
		System.out.println(obj.expectedLength(A, B, f));
	}
	
	public double expectedLength(int[] A, int[] B, int[] f) {
		this.A = A;
		this.B = B;
		double L = 0.0;
		int n = A.length + 1;
		visited = new boolean[n];
		for (int i = 0; i < A.length; i++) {
			double p = 1;
			// compute the probability of road i becomes a full road
			for (int j = 0; j < f.length; j++) { // compute the probablity of the j-th family pass the i-th road
				int reachedCityNum = dfs(f[j], i, false);
//				System.out.printf("road=%d, family=%d, reached=%d\n", i, j, reachedCityNum);
				p = p * ((double)reachedCityNum / (n - 1)); 
				System.out.printf("road=%d, family=%d, citiesArrived=%d, p=%f\n", i, j, reachedCityNum, p);
				
			}
			L += p;
		}
		return L;
	}
	private int dfs(int from, int k, boolean useTargetRoad) {
		visited[from] = true;
		int total = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == from || B[i] == from) {
				int x = A[i] == from ? B[i] : A[i];
				if (visited[x])
					continue;
				boolean useTargetRoad2 = useTargetRoad;
				if (i == k) { // come to the target road
					useTargetRoad2 = true;
				}
				if (useTargetRoad2) {
					total++;
				}
				total += dfs(x, k, useTargetRoad2);
			}
		}
		visited[from] = false;
//		System.out.println("useTargetRoad=" + useTargetRoad + " from=" + from + " total=" + total);
		return total;
	}
	
	// a more elegant and readability version
	private int DFS(int x, int parent, int road, boolean through) {
		int total = through ? 1 : 0;
		for (int i = 0; i < A.length; i++) { // search roads which brings one out of city x
			if (x == A[i] || x == B[i]) { 
				int y = A[i] == x ? B[i] : A[i];
				if (y != parent) {
					boolean nthrough = through || i == road;
					total += DFS(y, x, road, nthrough);
				}
			}
		}
		return total;
	}
	
	// more complicated version
	public double expectedLength2(int[] A, int[] B, int[] f) {
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
				p *= getNumOfCitiesWeCanReach(f[j], i, false) * 1.0 / (citiesNum - 1);
//				System.out.println("=====");
			}
			fullLen += p;
		}
		return fullLen;
	}
	
	private int getNumOfCitiesWeCanReach(int curr, int targetRoad, boolean through) { 
		// the below memo[][][] is not enough to describe a status, would be misleading, not use it
		// consider case A={0, 0,0}, B = {1, 2, 3}, f={1,2}
		// when calculate for road=2, familyIdx=0, we set memo[2][2][0]= 0
		// then we calculate for road=2, familyIdx=1, its answer should be 1, but we would get wrong answer since memo[2][2][0] is there already
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
