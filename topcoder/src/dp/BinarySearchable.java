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
	public static void main(String[] args) {
		BinarySearchable obj = new BinarySearchable();
		int[] sequence = {1, 3, 2};
		System.out.println(obj.howMany(sequence) == 1);
		sequence = new int[] {1,2,3,4,5};
		System.out.println(obj.howMany(sequence) == 5);
		sequence = new int[]{5, 4, 3, 2, 1};
		System.out.println(obj.howMany(sequence) == 0);
	}
}
