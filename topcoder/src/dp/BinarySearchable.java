package dp;

import java.util.Arrays;

public class BinarySearchable {
	public int howMany(int[] sequence) {
		boolean[] searchable = new boolean[sequence.length];
		Arrays.fill(searchable, true);
		for (int i = 0; i < sequence.length; i++) {
			for (int j = i + 1; j < sequence.length; j++) {
				if (sequence[i] > sequence[j]) {
					searchable[i] = false;
					searchable[j] = false;
				}
			}
		}
		int count = 0;
		for (boolean item : searchable) {
			if (item)
				count++;
		}
		return count;
	}
	
	public int howMany2(int[] sequence) {
		int N = sequence.length;
		int[] maxTo = new int[N];
		int[] minFrom = new int[N];
		boolean[] searchable = new boolean[N];
		Arrays.fill(searchable, true);
		maxTo[0] = sequence[0];
		minFrom[N - 1] = sequence[N - 1];
		for (int i = 1; i < N; i++) {
			maxTo[i] = Math.max(maxTo[i - 1], sequence[i]);
			if (sequence[i] < maxTo[i - 1]) {
				searchable[i] = false;
			}
		}
		for (int i = N - 2; i >= 0; i--) {
			minFrom[i] = Math.min(minFrom[i + 1], sequence[i]);
			if (sequence[i] > minFrom[i + 1]) {
				searchable[i] = false;
			}
		}
		int cnt = 0;
		for (boolean val : searchable) {
			if (val)
				cnt++;
		}
		return cnt;
	}
	public static void main(String[] args) {
		BinarySearchable obj = new BinarySearchable();
		int[] sequence = {1, 3, 2};
		System.out.println(obj.howMany2(sequence) == 1);
		sequence = new int[] {1,2,3,4,5};
		System.out.println(obj.howMany2(sequence) == 5);
		sequence = new int[]{5, 4, 3, 2, 1};
		System.out.println(obj.howMany2(sequence) == 0);
	}
}
