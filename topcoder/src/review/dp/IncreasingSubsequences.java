package review.dp;

public class IncreasingSubsequences {

	public static void main(String[] args) {
		IncreasingSubsequences obj = new IncreasingSubsequences();
		int[] a = {564,234,34,4365,424,2234,306,21,934,592,195,2395,2396,29345,13295423,23945,2};
		System.out.println(obj.count(a));
		a = new int[] {100, 10};
		System.out.println(obj.count(a));
		a = new int[] {1, 10};
		System.out.println(obj.count(a));

	}
	
	public long count(int[] a) {
		int N = a.length;
		int[] dp = new int[N]; // dp[i]: number of LIS which end at i
		for (int i = 0; i < N; i++) {
			int last = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (a[j] < a[i] && a[j] > last) {
					dp[i] += dp[j];
					last = a[j];
				}
			}
			if (dp[i] == 0) // all of the previous numbers are larger than a[i], then itself forms a LIS
				dp[i] = 1;
		}
		int result = 0;
		int last = 0;
		for (int i = N - 1; i >= 0; i--) {
			if (a[i] > last) {
				result += dp[i];
				last = a[i];
			}
		}
		return result;
	}

}
