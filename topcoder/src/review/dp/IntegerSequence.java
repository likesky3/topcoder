package review.dp;

public class IntegerSequence {

	public static void main(String[] args) {
		IntegerSequence obj = new IntegerSequence();
		int[] numbers = {2, 2, 2, 2};
		System.out.println(obj.maxSubsequence(numbers));
	}
	
	public int maxSubsequence(int[] numbers) {
		int N = numbers.length;
		int[] lis = new int[N];
		int[] lds = new int[N];
		for (int i = 0; i < N; i++) {
			lis[i] = 1;
			for (int j = 0; j < i; j++) {
				if (numbers[i] > numbers[j])
					lis[i] = Math.max(lis[i], lis[j] + 1);
			}
		}
		for (int i = N - 1; i >= 0; i--) {
			lds[i] = 1;
			for (int j = i + 1; j < N; j++) {
				if (numbers[i] > numbers[j])
					lds[i] = Math.max(lds[i], lds[j] + 1);
			}
		}
		int maxLen = 0;
		for (int i = 0; i < N; i++) {
			maxLen = Math.max(maxLen, lis[i] + lds[i] - 1);
		}
		return N - maxLen;
	}

}
