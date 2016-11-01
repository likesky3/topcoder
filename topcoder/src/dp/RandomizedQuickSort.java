package dp;

import java.util.Arrays;

public class RandomizedQuickSort {

	public static void main(String[] args) {
		RandomizedQuickSort obj = new RandomizedQuickSort();
		System.out.println(obj.getExpectedTime(3, 1, 1, 1));
	}
	
	public  double getExpectedTime(int listSize, int S, int a, int b) {
		this.S = S;
		this.a = a;
		this.b = b;
		memo = new double[listSize + 1];
		Arrays.fill(memo, -1); 
		return recur(listSize);
	}
	
	private double[] memo;
	private int S, a, b;
	private double recur(int length) {
		if (memo[length] != -1) {
			return memo[length];
		}
		double res = 0;
		if (length <= S) {
			memo[length] = b * length * length;
			return memo[length];
		}
		for (int i = 1; i < length; i++) {
			res += recur(i) + recur(length - i);
		}
		memo[length] = res / length + a * length;
		return memo[length];
	}
}
