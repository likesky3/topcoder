package dp;

public class RandomPancakeStackDiv2 {
	public double expectedDeliciousness(int[] d) {
		int N = d.length;
		// dp[i][j]:the expected deliciousness with i pancakes remaining, and the current size of serving stack is j 
		double[][] dp = new double[N + 1][N + 1];
		for (int t = 1; t <= N; t++) {
			for (int s = 0; s < N; s++) {
				dp[t][s] = 0;
				for (int i = 0; i < t; i++) {
					dp[t][s] += d[i] + dp[i][s + 1];
				}
				dp[t][s] /= N - s;
			}
		}
		return dp[N][0];
	}
	
	public static void main(String[] args) {
		RandomPancakeStackDiv2 obj = new RandomPancakeStackDiv2();
		int[] d = {1, 1, 1};
		System.out.println(obj.expectedDeliciousness(d));
		System.out.println(obj.expectedDeliciousness2(d));
		d = new int[]{3,6,10,9,2};
		System.out.println(obj.expectedDeliciousness(d));
		System.out.println(obj.expectedDeliciousness2(d));
	}
	
	public double expectedDeliciousness2(int[] d) {
		int N = d.length;
		double[][] dp = new double[N + 1][N + 1];
		for (int s = N - 1; s >= 0; s--) {
			for (int t = 0; t <= N - s; t++) {
				for (int i = 0; i < t; i++) {
					dp[s][t] += d[i] + dp[s + 1][i];
				}
				dp[s][t] /= 1.0 * (N - s);
			}
		}
		return dp[0][N];
	}
}
