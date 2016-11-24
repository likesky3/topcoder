package review.dp;


public class SRMSystemTestPhase {

	public static void main(String[] args) {
		String[] description = {"YNN", "NYN"};
		SRMSystemTestPhase obj = new SRMSystemTestPhase();
		System.out.println(obj.countWays(description));
		System.out.println(obj.countWays(description) == 6);
		description = new String[]{"YYY"};
		System.out.println(obj.countWays(description) == 27);
	}
	public int countWays(String[] description) {
		int N = description.length;
		// dp[i][p][c]: number of different scoreboards considering only coders from i to N - 1, when the (i-1)th coder has p problems passed and c problem challenged
		int[][][] dp = new int[N + 1][4][4];
		for (int p = 0; p <= 3; p++) {
			for (int c = 0; c <= 3 - p; c++) {
				dp[N][p][c] = 1;
			}
		}
		
		for (int i = N - 1; i >= 0; i--) {
			for (int p = 0; p <= 3; p++) {
				for (int c = 0; c <= 3 - p; c++) {
					int[] opt = new int[3];
					for (opt[0] = 0; opt[0] <= 2; opt[0]++) {
						for (opt[1] = 0; opt[1] <= 2; opt[1]++) {
							for (opt[2] = 0; opt[2] <= 2; opt[2]++) { 
								// 0: passed or not submitted
								// 1: challenged
								// 2: failed
								int p_j = 0, c_j = 0;
								boolean valid = true;
								for (int k = 0; k < 3; k++) {
									if (description[i].charAt(k) == 'Y') {
										if (opt[k] == 0) p_j++;
										if (opt[k] == 1) c_j++;
									} else {
										if (opt[k] > 0)
											valid = false;
									}
								}
								if (!valid) continue;
								if (p > p_j || (p == p_j && c <= c_j)) {
									dp[i][p][c] += dp[i + 1][p_j][c_j];
								}
							}
								
						}
					}
				}
			}
		}
		return dp[0][3][0];
	}

}
