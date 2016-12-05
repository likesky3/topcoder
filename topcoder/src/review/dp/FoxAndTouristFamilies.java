package review.dp;

public class FoxAndTouristFamilies {

	public static void main(String[] args) {
		FoxAndTouristFamilies obj = new FoxAndTouristFamilies();
		int[] A = new int[]{0};
		int[] B = new int[]{1};
		int[] f = {0};
//		System.out.println(obj.expectedLength(A, B, f));
		
		A = new int[] {0, 0, 0};
		B = new int[] {1, 2, 3};
		f = new int[]{0, 1};
		System.out.println(obj.expectedLength(A, B, f));
	}

	public double expectedLength(int[] A, int[] B, int[] f) {
		this.A = A;
		this.B = B;
		int n = A.length + 1; // total number of cities
		double E = 0;
		for (int i = 0; i < A.length; i++) {
			// calculate the probability of a road is a full road
			
			double p = 1.0;
			for (int j = 0; j < f.length; j++) { 
				// calculate the probability that a family will travel through road i
				
				int reachedCityNum = dfs(f[j], -1, i, false);
				p *= 1.0 * reachedCityNum / (n - 1);
			}
			E += p;
		}
		return E;
	}
	
	private int[] A;
	private int[] B;
	
	// calculate the number of cities which can be reached through the given "road" start from city "from"
	public int dfs(int from, int parent, int road, boolean through) {
		int total = through ? 1 : 0;
		for (int i =  0; i < A.length; i++) {
			if (from == A[i] || from == B[i]) {
				int next = from == A[i] ? B[i] : A[i];
				if (next != parent) {
					boolean throughNew = through || i == road;
					total += dfs(next, from, road, throughNew);
				}
			}
		}
		return total;
	}
	
	
 }
