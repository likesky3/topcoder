package dp;

public class SRMSystemTestPhase {

	public static void main(String[] args) {
		String[] description = {"YNN", "NNY"};
		SRMSystemTestPhase obj = new SRMSystemTestPhase();
		System.out.println(obj.countWays(description));
		description = new String[]{"YYY"};
		System.out.println(obj.countWays(description));
	}

	private final int MOD = 1000000007;
	public int countWays(String[] description) {
		int N = description.length;
		int[][][] dp = new int[N + 1][4][4];
		// base case: empty board
		for (int p = 0; p <= 3; p++){
			for (int c = 0; c <= 3; c++) {
				dp[N][p][c] = 1;
			}
		}
		
		// dp[i][p][c]: the number of different score boards considering only coders from i to N - 1,
		// such that the (i-1)th coder has p passed solutions and c challenged solutions
		for (int i = N - 1; i >= 0; i--) {
			for (int p = 0; p <= 3; p++) {
				for (int c = 0; c <= 3; c++) {
					int[] opt = new int[3];
					for (opt[0] = 0; opt[0] < 3; opt[0]++) {
						for (opt[1] = 0; opt[1] < 3; opt[1]++) {
							for (opt[2] = 0; opt[2] < 3; opt[2]++) {
								// 0: pass or not submit the solution
								// 1: challenged
								// 2: fail
								int p_j = 0, c_j = 0;
								boolean valid = true;
								for (int k = 0; k < 3; k++) {
									if (description[i].charAt(k) == 'Y') {
										if (opt[k] == 0)
											p_j++;
										else if (opt[k] == 1)
											c_j++;
									} else {
										if (opt[k] > 0)
											valid = false;
									}
								}
								if (!valid)
									continue;
								if (p > p_j || (p == p_j && c <= c_j)) {
									dp[i][p][c] = (dp[i][p][c] + dp[i + 1][p_j][c_j]) % MOD;
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
