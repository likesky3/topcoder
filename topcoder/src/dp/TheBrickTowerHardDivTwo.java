package dp;

public class TheBrickTowerHardDivTwo {

	public static void main(String[] args) {
		TheBrickTowerHardDivTwo obj = new TheBrickTowerHardDivTwo();
//		System.out.println(obj.find(1, 0, 1) == 0);
//		System.out.println(obj.find(1, 1, 1) == 0);
//		System.out.println(obj.find(1, 2, 1) == 0);
//		System.out.println(obj.find(1, 3, 1) == 0);
//		System.out.println(obj.find(1, 4, 1) == 1);
//		System.out.println(obj.find(1, 4, 2) == 0);
//		System.out.println(obj.find(1, 8, 2) == 1);
//		System.out.println(obj.find(2, 3, 1) == 14);
//		System.out.println(obj.find(1, 2, 2) == 0);
		System.out.println(obj.find(4,7,47) == 1008981254);
		
	}
	
	public int find(int C, int K, int H) {
		colorsNum = C;
		dp = new int[C + 1][C + 1][C + 1][C + 1][K + 1][H + 1];
		used = new boolean[C + 1][C + 1][C + 1][C + 1][K + 1][H + 1];
		int res = 0;
		for (int h = 1; h <= H; h++) { 
			res = (int)(((long)res + rec(C, C, C, C, K, h)) % MOD); 
		}
		return res;
	}
	
	private int[][][][][][] dp;
	private boolean[][][][][][] used;
	private final int MOD = 1234567891;
	private int colorsNum;
	
	private int rec(int A, int B, int C, int D, int K, int H) {
		if (H == 0)
			return 1;
		int res = dp[A][B][C][D][K][H];
		if (!used[A][B][C][D][K][H]) {
			used[A][B][C][D][K][H] = true;
			res = 0;
			for (int a = 0; a < colorsNum; a++) {
				for (int b = 0; b < colorsNum; b++) {
					for (int c = 0; c < colorsNum; c++) {
						for (int d = 0; d < colorsNum; d++) {
							int k = K;
							if (a == A) k--;
							if (b == B) k--;
							if (c == C) k--;
							if (d == D) k--;
							if (a == b) k--;
							if (a == c) k--;
							if (b == d) k--;
							if (c == d) k--;
							if (k >= 0) {
								res = (int)(((long)res + rec(a, b, c, d, k, H - 1)) % MOD); // caution, pass k, not K
							}
						}
					}
				}
			}
		}
		return dp[A][B][C][D][K][H] = res;
	}
}
