package dp;

public class BitStrings {

	public static void main(String[] args) {
		BitStrings obj = new BitStrings();
		String[] list = {"1", "0", "100", "10"};
		System.out.println(obj.maxStrings(list, 2, 2));
	}
	
	public int maxStrings(String[] list, int numZeros, int numOnes) {
		int N = list.length;
		int[] counter0 = new int[N];
		int[] counter1 = new int[N];
		for (int i = 0; i < N; i++) {
			String s = list[i];
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '0') {
					counter0[i]++;
				} else {
					counter1[i]++;
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i < (1 << N); i++) {
			int total0 = 0, total1 = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) > 0) {
					total0 += counter0[j];
					total1 += counter1[j];
				}
			}
			if (total0 <= numZeros && total1 <= numOnes) {
				res = Math.max(res, Integer.bitCount(i));
			}
		}
		return res;
	}
	
	public int maxStrings1(String[] list, int numZeros, int numOnes) {
		int N = list.length;
		int[] zeros = new int[N];
		int[] ones = new int[N];
		for (int i = 0; i < N; i++) {
			int len = list[i].length();
			for (int j = 0; j < len; j++) {
				if (list[i].charAt(j) == '0')
					zeros[i]++;
			}
			ones[i] = len - zeros[i];
		}
		int[] dp0 = new int[1 << N]; // dp0[mask] : the number of zeros needed when usage of list is represented as mask 
		int[] dp1 = new int[1 << N]; // dp1[mask]: the number of ones needed when ...
		int max = 0;
		for (int mask = 1; mask < (1 << N); mask++) {
			for (int i = 0; i < N; i++) {
				if ((mask & (1 << i)) > 0) {
					dp0[mask] = zeros[i] + dp0[mask ^ (1 << i)];
					dp1[mask] = ones[i] + dp1[mask ^ (1 << i)];
					if (dp0[mask] <= numZeros && dp1[mask] <= numOnes) {
						max = Math.max(max, Integer.bitCount(mask));
					}
					break;
				}
			}
		}
		return max;
	}
	
	public int maxStrings2(String[] list, int numZeros, int numOnes) {
		int N = list.length;
		int[] count0 = new int[N];
		int[] count1 = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < list[i].length(); j++) {
				if (list[i].charAt(j) == '0')
					count0[i]++;
				else
					count1[i]++;
			}
		}
		// do you know why we need two dp arrays ?
		int[][] dp = new int[numZeros + 1][numOnes + 1];
		int[][] dp2 = new int[numZeros + 1][numOnes + 1];
		for (int i = 0; i < N; i++) {
			// try to add list[i] from everywhere we have reached
			for (int used0 = 0; used0 <= numZeros; used0++) {
				for (int used1= 0; used1<= numOnes; used1++) {
					int nused0 = used0 + count0[i]; 
					int nused1 = used1 + count1[i];
					if (nused0 <= numZeros && nused1 <= numOnes) {
						dp2[nused0][nused1] = Math.max(dp2[nused0][nused1], dp[used0][used1] + 1);
//						System.out.printf("i=%d, dp[%d][%d]=%d\n", i, nused0, nused1, dp2[nused0][nused1]);
					}
				}
			}
			
			// copy dp2 to dp
			for (int p = 0; p < dp.length; p++) {
				for (int q = 0; q < dp[0].length; q++) {
					dp[p][q] = dp2[p][q];
				}
			}
		}
		return dp[numZeros][numOnes];
	}

}
