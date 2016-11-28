package review.dp;

import java.util.Arrays;

public class RandomizedQuickSort {

	public static void main(String[] args) {
		RandomizedQuickSort obj = new RandomizedQuickSort();
		System.out.println(obj.getExpectedTime(3, 1, 1, 1));
	}
	
	public double getExpectedTime(int listSize, int S, int a, int b) {
		this.S = S;
		this.a = a;
		this.b = b;
		memo = new double[listSize + 1];
		Arrays.fill(memo, -1);
		return recur(listSize);
	}
	
	private double recur(int N) {
		if (memo[N] >= 0) {
			return memo[N];
		}
		if (N <= S) {
			memo[N] = b * N * N;
		} else {
			double E = 0;
			for (int i = 0; i < N; i++) {
				E += recur(i) + recur(N - 1 - i) ;
			}
			memo[N] = E * 1.0 / N + a * N;
		}
		return memo[N];
	}
	
	private double[] memo;
	private int S, a, b;

}
