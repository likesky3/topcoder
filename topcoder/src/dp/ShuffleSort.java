package dp;

import java.util.Arrays;

public class ShuffleSort {

	public static void main(String[] args) {
//		int[] cards = {15,16,4,8,43,42,4};
		int[] cards = {1, 1, 1, 2};
		ShuffleSort obj = new ShuffleSort();
		System.out.println(obj.shuffle(cards));
		System.out.println(obj.shuffle2(cards));
		System.out.println(obj.shuffle3(cards));
		cards = new int[]{2, 3, 1};
//		System.out.println(obj.shuffle3(cards));
	}
	
	// how to understand f(n-1) -1?
	// f(n) : shuffle first, then judge
	// t(n): judge
	// the probability that at least one card is removed (if t of the n cards are equal to the minimum card,
	// then the probability is t / n
	// f(n) = 1 + t(n)
	// t(n) = t/n * t(n - 1) + (1 - t/n) * f(n)
	// so f(n) = 1 + t/n * (f(n-1) - 1) + (1 - t/n) * f(n)
	//--> f(n) = f(n - 1) + n/t - 1;
	//--> f(n) - f(n - 1) = n/t - 1;
	
	// wrong implementation, use the other two to understand the meaning of t
	public double shuffle(int[] cards) {
		Arrays.sort(cards);
		double f = 1; // base case: dp[0] = 1
		double t = 1.0;
		int n = cards.length;
		for (int i = 1; i < n; i++) {
			if (cards[i] == cards[i - 1]) {
				t += 1;
			} else {
				t = 1;
			}
			f = f + (i + 1) * 1.0/ t - 1; 
		}
		return f;
	}
	
	public double shuffle2(int[] cards) {
		Arrays.sort(cards);
		int n = cards.length;
		double[] dp = new double[n + 1];
		dp[n - 1] = 1;
		for (int i = n - 2; i >= 0; i--) {
			double t = 1.0;
			for (int j = i + 1; j < n; j++)
				if (cards[j] == cards[i]) {
					t += 1;
			} 
			dp[i] = dp[i + 1] + (n - i) * 1.0 / t - 1; 
		}
		return dp[0];
	}
	
	public double shuffle3(int[] cards) {
		Arrays.sort(cards);
		int n = cards.length;
		double deltaSum = 0.0;
		for (int i = 0; i < n; i++) {
			int sameNum = 1;
			for (int j = i + 1; j < n; j++) {
				if (cards[j] == cards[i])
					sameNum++;
			}
			deltaSum += (n - i) * 1.0 / sameNum - 1;
		}
		return deltaSum + 1;
	}
}
