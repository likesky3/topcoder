package dp;

public class IncreasingSubsequences {

	public static void main(String[] args) {
		IncreasingSubsequences obj = new IncreasingSubsequences();
		int[] a = {1, 3, 2, 6, 4, 5};
		System.out.println(obj.count(a));
		a = new int[]{10, 1};
		System.out.println(obj.count(a));
		a = new int[]{564,234,34,4365,424,2234,306,21,934,592,195,2395,2396,29345,13295423,23945,2};
		System.out.println(obj.count(a));
	}
	
	public long count(int[] a) { // latest version, happy~
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
	
	public long count2(int[] a) {
		int N = a.length;
		int[] dp = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[i])
					continue;
				int k = j + 1;
				while (k < i) {
					if (a[k] < a[i] && a[k] > a[j])
						break;
					k++;
				}
				if (k == i)
					dp[i] += dp[j];
			}
			if (dp[i] == 0)
				dp[i] = 1;
//			System.out.printf("dp[%d]=%d\n", i, dp[i]);
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			boolean isMaximal = true;
			for (int j = i + 1; j < N; j++) {
				if (a[j] > a[i])
					isMaximal = false;
			}
			if (isMaximal)
				res += dp[i];
		}
		return res;
	}
	public long count1(int[] a) {
		int N = a.length;
		// dp[i]: number of maximal LIS end at i
		int[] dp = new int[N];
		//f[i][j]: there is some number less than a[j] && greater than a[i] from i+1 to j -1, inclusively
		boolean[][] f = new boolean[N][N + 1];
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = i + 1; k < j; k++) {
					if (a[k] < a[j] && a[k] > a[i])
						f[i][j] = true;
				}
				//System.out.printf("f[%d][%d]=%b\n", i, j, f[i][j]);
			}
			for (int k = i + 1; k < N; k++) {
				if (a[k] > a[i])
					f[i][N] = true;
			}
		}
		dp[0] = 1;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] < a[i] && !f[j][i])
					dp[i] += dp[j];
			}
			if (dp[i] == 0)
				dp[i] = 1;
//			System.out.printf("dp[%d]=%d\n", i, dp[i]);
		}
		
		int res = 0;
		for (int i = 0; i < N; i++) {
			if (!f[i][N])
				res += dp[i];
		}
		return res;
	}
}
