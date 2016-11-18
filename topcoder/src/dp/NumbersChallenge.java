package dp;
import java.util.HashSet;
import java.util.Set;

public class NumbersChallenge {

	public static void main(String[] args) {

	}

	public int MinNumber(int[] S) {
		int N = S.length;
		int limits = 1 << N;
		Set<Integer> set = new HashSet<Integer>();
		for (int mask = 1; mask < limits; mask++) {
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) > 0)
					sum += S[i];
			}
			set.add(sum);
		}
		
		int maxSum = 0;
		for (int i = 0;i < N; i++)
			maxSum += S[i];
		for (int i = 1; i <= maxSum; i++) {
			if (!set.contains(i))
				return i;
		}
		return maxSum + 1;
	}
	
	public int MinNumber2(int[] S) {
		int[] a = new int[2000001];
		int N = S.length;
		for (int i = 0; i < (1 << N); i++) {
			int sum = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) > 0) {
					sum += S[j];
				}
			}
			a[sum] = 1;
		}
		for (int i = 1; i < a.length; i++) {
			if (a[i] == 0)
				return i;
		}
		return 0;
	}
}
