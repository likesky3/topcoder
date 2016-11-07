package dp;

import java.util.HashMap;
import java.util.Map;

public class Arrfix {
	public int mindiff1(int[] A, int[] B, int[] F) {
		int N = A.length;
		// step 1: try to use F[i] which will decrease the unmatched number between A[] and B[]
		boolean[] used = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (A[i] != B[i]) {
				for (int j = 0; j < F.length; j++) {
					if (F[j] == B[i] && F[j] != -1) {
						A[i] = F[j];
						F[j] = -1;
						used[i] = true;
						break;
					}
				}
			}
		}
		// step 2: try to use F[i] == A[x] == B[x] which will not increase the unmatched number
		for (int i = 0; i < N; i++) {
			if (A[i] == B[i] && used[i] == false){
				for (int j = 0; j < F.length; j++) {
					if (F[j] == A[i] && F[j] != -1) {
						A[i] = F[j];
						F[j] = -1;
						used[i] = true;
						break;
					}
				}
			}
		}
		// step 3: try to use F[i] where A[x] != B[x], this will not increase the unmatced number
		for (int i = 0; i < N; i++) {
			if (A[i] != B[i]) {
				for (int j = 0; j < F.length; j++) {
					if (F[j] != -1) {
						A[i] = F[j];
						F[j] = -1;
						used[i] = true;
						break;
					}
				}
			}
		}
		// step 4: just use up F[i], use it where A[x] has not been used
		for (int i = 0; i < F.length; i++) {
			if (F[i] != -1) {
				for (int j = 0; j < A.length; j++) {
					if (used[j] == false) {
						A[j] = F[i];
						F[i] = -1;
						used[j] = true;
						break;
					}
				}
			}
		}
		int c = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != B[i]) {
				c++;
			}
		}
		return c;
	}
	
	public int mindiff(int[] A, int[] B, int[] F) {
		Map<Integer, Integer> matched = new HashMap<>();
		Map<Integer, Integer> unmatched = new HashMap<>();
		int c = 0; // number of unmatched pair between original A[] and B[]
		for (int i = 0; i < A.length; i++) {
			if (A[i] == B[i]) {
				if (!matched.containsKey(B[i]))
					matched.put(B[i], 0);
				matched.put(B[i], matched.get(B[i]) + 1);
//				System.out.printf("matched, key=%d, value=%d\n", B[i], matched.get(B[i]));
			} else {
				if (!unmatched.containsKey(B[i]))
					unmatched.put(B[i], 0);
				unmatched.put(B[i], unmatched.get(B[i]) + 1);
				c++;
			}
		}
		int cc = 0; // number of new unmatched pair between F[] and B[]
		for (int i = 0; i < F.length; i++) {
			// unmatched should come first before matched, this will decrease the final number of unmatched pair
			// notice that unmatched may have same keys to mathced, e.g. test case 4
			if(unmatched.containsKey(F[i])){
				unmatched.put(F[i], unmatched.get(F[i]) - 1);
				if (unmatched.get(F[i]) == 0)
					unmatched.remove(F[i]);
//				System.out.printf("F[%d]=%d unmatched, unmatched remain=%d\n", i, F[i], unmatched.get(F[i]));
				c--;
			} else if (matched.containsKey(F[i])) {
				matched.put(F[i], matched.get(F[i]) - 1);
//				System.out.printf("scan F[], matched, key=%d, value=%d\n", F[i], matched.get(F[i]));
				if (matched.get(F[i]) == 0)
					matched.remove(F[i]);
//				System.out.printf("F[%d]=%d matched, matched remain=%d\n", i, F[i], matched.get(F[i]));
			} else {
				cc++;
			}
		}
		System.out.printf("c=%d, cc=%d\n", c, cc);
		return Math.max(c, cc);
	}
	
	public static void main(String[] args) {
		Arrfix obj = new Arrfix();
		int[] A = {1, 3};
		int[] B = {4, 8};
		int[] F = {2, 4};
//		System.out.println(obj.mindiff(A, B, F));
		
		A = new int[]{1, 1, 1};
		B = new int[]{2, 2, 1};
		F = new int[]{2, 2};
//		System.out.println(obj.mindiff(A, B, F));
		
		A = new int[]{1, 3, 3, 3};
		B = new int[]{2, 3, 3, 3};
		F = new int[]{2, 2};
//		System.out.println(obj.mindiff(A, B, F));
		
		// test case 4
		A =new int[]{375, 837, 597, 837, 837, 603, 21, 375, 597, 351, 351, 837, 837, 597, 292, 42, 375, 42, 292, 597, 375, 21, 603, 375, 351, 375, 42, 597, 292, 42, 603, 597, 837, 21, 597, 42, 42, 837};
		B = new int[]{603, 375, 21, 292, 375, 375, 597, 292, 292, 351, 597, 42, 42, 351, 351, 837, 837, 21, 603, 375, 21, 603, 375, 42, 21, 351, 837, 21, 292, 21, 603, 21, 597, 597, 42, 837, 597, 351};
		F = new int[] {351, 42, 597, 42, 21, 351, 21, 292, 603};
		System.out.println("minddiff(): " + obj.mindiff(A, B, F));
		System.out.println("minddiff1()" + obj.mindiff1(A, B, F));
	}

}
