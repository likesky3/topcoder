package dp;

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
		f = new int[]{0, 1};
		System.out.println(obj.expectedLength(A, B, f));
	}
	
	public double expectedLength(int[] A, int[] B, int[] f) {
		double L = 0.0;
		int n = A.length + 1;
		visited = new boolean[n];
		for (int i = 0; i < A.length; i++) {
			double p = 1;
			// compute the probability of road i becomes a full road
			for (int j = 0; j < f.length; j++) { // compute the probablity of the j-th family pass the i-th road
				int reachedCityNum = dfs(A, B, f[j], i, false);
//				System.out.printf("road=%d, family=%d, reached=%d\n", i, j, reachedCityNum);
				p = p * ((double)reachedCityNum / (n - 1)); 
				
			}
			L += p;
		}
		return L;
	}
	boolean[] visited;
	private int dfs(int[] A, int[] B, int from, int k, boolean useTargetRoad) {
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
				total += dfs(A, B, x, k, useTargetRoad2);
			}
		}
		visited[from] = false;
//		System.out.println("useTargetRoad=" + useTargetRoad + " from=" + from + " total=" + total);
		return total;
	}
	
	// a more elegant and readability version
	private int DFS(int[] A, int[] B, int x, int parent, int road, boolean through) {
		int total = through ? 1 : 0;
		for (int i = 0; i < A.length; i++) {
			if (x == A[i] || x == B[i]) { 
				int y = A[i] == x ? B[i] : A[i];
				if (y != parent) {
					boolean nthrough = through || i == road;
					total += DFS(A, B, y, x, road, nthrough);
				}
			}
		}
		return total;
	}
}
