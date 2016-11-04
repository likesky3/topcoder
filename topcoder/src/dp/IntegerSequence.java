package dp;

import java.util.Arrays;

	public class IntegerSequence {

	public static void main(String[] args) {
		int[] numbers = {1, 2, 1, 2, 3, 2, 1, 2, 1};
		IntegerSequence obj = new IntegerSequence();
		System.out.println(obj.maxSubsequence(numbers));
		numbers = new int[]{4,5,65,34,786,45678,987,543,2,6,98,580,4326,754,54,2,1,3,5,6,8,765,43,3,54};
		System.out.println(obj.maxSubsequence(numbers));
	}
	
	public int maxSubsequence(int[] numbers) {
		int N = numbers.length;
		int[] lis = new int[N];
		int[] lds = new int[N];
		Arrays.fill(lis, 1);
		Arrays.fill(lds, 1);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (numbers[j] < numbers[i]) {
					lis[i] = Math.max(lis[i], lis[j] + 1);
				}
			}
//			System.out.printf("lis[%d]=%d\n", i, lis[i]);
		}
		
		for (int i = N - 1; i >= 0; i--) {
			for (int j = N - 1; j > i; j--) {
				if (numbers[j] < numbers[i]) {
					lds[i] = Math.max(lds[i], lds[j] + 1);
				}
			}
//			System.out.printf("lds[%d]=%d\n", i, lds[i]);
		}
		
		int maxSubseq = 0;
		for (int i = 0; i < N; i++) {
			maxSubseq = Math.max(maxSubseq, lis[i] + lds[i] -1);
		}
		return N - maxSubseq;
	}

}
