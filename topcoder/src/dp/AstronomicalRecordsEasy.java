package dp;

import java.util.HashSet;
import java.util.Set;

public class AstronomicalRecordsEasy {
	public int minimalPlanets(int[] A, int[] B) {
		int ans = A.length + B.length;
		for (int p : A) {
			for (int q : B) {
				Set<Integer> seen = new HashSet<>();
				for (int x : A) {
					seen.add(q * x);
				}
				for (int y : B) {
					seen.add(p * y);
				}
				ans = Math.min(ans, seen.size());
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		AstronomicalRecordsEasy obj = new AstronomicalRecordsEasy();
		int[] A = {1, 2, 3};
		int[] B = {2, 3, 4};
		System.out.println(obj.minimalPlanets(A, B));
		
		A = new int[]{2, 3, 4};
		B = new int[]{2, 5, 6, 7, 8, 9};
		System.out.println(obj.minimalPlanets(A, B));
		
	}

}
