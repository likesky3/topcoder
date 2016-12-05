package dp;

import java.util.Arrays;

public class Stamp {
	// Method 1: ref others
	private int oo = 1000000;
	public int getMinimumCost(String desiredColor, int stampCost, int pushCost) {
		int N = desiredColor.length();
		int[] A = new int[N];
		int[] cost = new int[N + 1];
		for (int i = 0; i < N; i++) {
			char c = desiredColor.charAt(i);
			if (c == '*')
				A[i] = 7;
			else if (c == 'R')
				A[i] = 1;
			else if (c == 'G')
				A[i] = 2;
			else if (c == 'B')
				A[i] = 4;
		}
		int res = oo;
		//try all the posibility of length of the stamp
		for (int len = 1; len <= N; len++) {
			Arrays.fill(cost, oo);
			cost[0] = 0;
			//dynamic program for the minimum cost
			// why we don't jump to the furthest position one color would arrive
			// consider case RR*BBB
			for (int i = 0; i < N; i++) {
				int color = 7;
				//with each position i, we try to paint until the furthest position j as long as we can use just 1 color
				for (int j = i; j < N; j++) {
					color &= A[j];
					if (color == 0) break; // A[j] is a new color
					int seg = j - i + 1;
					if (seg < len)
						continue;
					if (cost[i] != oo) {
						cost[j + 1] = Math.min(cost[j + 1], cost[i] + ((seg + len - 1) / len) * pushCost);
//						System.out.printf("len=%d, i=%d, j=%d, seg=%d, cost[%d]=%d\n", len, i, j, seg, j + 1, cost[j + 1]);
					}
				}
			}
			if (cost[N] != oo) {
				res = Math.min(cost[N] + len * stampCost, res);
//				System.out.printf("res=%d, len=%d\n", res, len);
			}
		}
//		System.out.println("final result=" + res);
		return res;
	}
	
	//Method 2
	public int getMinimumCost2(String desiredColor, int stampCost, int pushCost) {
		int N = desiredColor.length();
		// dp[i][j] is the push times need for the first i cells, with the last stamp in color j, the stamp length is s
		int[][] dp = new int[N + 1][3];
		int minCost = stampCost + pushCost * N;
		char[] colors = {'R', 'G', 'B'};
		for (int s = 2; s <= N; s++) {
			for (int i = 1; i < s; i++) {
				for (int j = 0; j < 3; j++) {
					dp[i][j] = N + 1;
				}
			}
			boolean canFillAll = false;
			for (int i = s; i <= N; i++) {
				for (int j = 0; j < 3; j++) {
					dp[i][j] = N + 1;
					if (checkColorInSingleStamp(desiredColor, i - s, i - 1, colors[j])) {
						for (int p = 1; p < s; p++)
							dp[i][j] = Math.min(dp[i][j], dp[i - p][j] + 1);
						for (int q = 0; q < 3; q++) 
							dp[i][j] = Math.min(dp[i][j], dp[i-s][q] + 1);
					}
				}
			}
			
			for (int j = 0; j < 3; j++) {
				if (dp[N][j] < N) {
					minCost = Math.min(minCost, s * stampCost + dp[N][j] * pushCost);
					canFillAll = true;
				}
			}
			if (!canFillAll)
				break;
		}
		
		return minCost;
	}
	private boolean checkColorInSingleStamp(String desiredColor, int from, int to, char expectedColor) {
		for (int i = from; i <= to; i++) {
			if (desiredColor.charAt(i) != '*' && desiredColor.charAt(i) != expectedColor) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Stamp obj = new Stamp();
//		System.out.println(obj.getMinimumCost("RRGGBB", 1, 1) == 5);
		System.out.println(obj.getMinimumCost("*B**B**B*BB*G*BBB**B**B*", 5, 2) == 33);
		System.out.println(obj.getMinimumCost("R*RR*GG", 10, 58) == 204);
		
		System.out.println(obj.getMinimumCost2("*B**B**B*BB*G*BBB**B**B*", 5, 2) == 33);
		System.out.println(obj.getMinimumCost2("R*RR*GG", 10, 58) == 204);
	}
}
