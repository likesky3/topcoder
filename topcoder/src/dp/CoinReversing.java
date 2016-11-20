package dp;

public class CoinReversing {
	public double expectedHeads(int N, int[] a) {
		double evenP = 1.0; // at step 0, each coin is reversed 0 times
		for (int t = 0; t < a.length; t++) {
			double oddP = (double)a[t] / N; // the probability that a coin is chosen to be reversed in t-th step
			evenP = (1 - evenP) * oddP + evenP * (1 - oddP); // the even probablity for t + 1 steps is evenP 
		}
		// evenP holds the probablity that a coin will be picked up for even times
		return evenP * N;
	}
	public static void main(String[] args) {
		int[] a = {2, 2};
		CoinReversing obj = new CoinReversing();
		System.out.println(obj.expectedHeads(3, a));
	}
}
