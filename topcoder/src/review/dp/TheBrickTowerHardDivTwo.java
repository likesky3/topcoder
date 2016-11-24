package review.dp;


public class TheBrickTowerHardDivTwo {

	public static void main(String[] args) {
		TheBrickTowerHardDivTwo obj = new TheBrickTowerHardDivTwo();
		System.out.println(obj.find(1, 0, 1) == 0);
		System.out.println(obj.find(1, 1, 1) == 0);
		System.out.println(obj.find(1, 2, 1) == 0);
		System.out.println(obj.find(1, 3, 1) == 0);
		System.out.println(obj.find(1, 4, 1) == 1);
		System.out.println(obj.find(1, 4, 2) == 1);
		System.out.println(obj.find(1, 8, 2) == 1);
		System.out.println(obj.find(2, 3, 1) == 14);
		System.out.println(obj.find(1, 2, 2) == 0);
		System.out.println(obj.find(4,7,47) == 1008981254);

	}
	
	private final int mod = 1234567891;
	private int[][][][][][] dp;
	private boolean[][][][][][] known;
	private int colorsNum;
	public int find(int C, int K, int H) {
		dp = new int[C + 1][C + 1][C + 1][C + 1][K + 1][H + 1];
		known = new boolean[C + 1][C + 1][C + 1][C + 1][K + 1][H + 1];
		colorsNum = C;
		long res = 0;
		// top-down, imagine there is layer h + 1, and the colors of its four bricks are all C, 
		// which would be different to layer h, no matter what it is
		for (int h = 1; h <= H; h++) {
			res = (res + recur(C, C, C, C, K, h)) % mod;
		}
		return (int)res;
	}
	
	// at layer H, the colors of the four bricks are A, B, C and D
	private long recur(int A, int B, int C, int D, int K, int H) {
		if (H == 0) // finishing building a nice tower
			return 1;
		if (known[A][B][C][D][K][H]) {
			return dp[A][B][C][D][K][H];
		} else {
			known[A][B][C][D][K][H] = true;
			long res = 0;
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
							if (c == d) k--;
							if (b == d) k--;
							if (k >= 0) {
								res = (res + recur(a, b, c, d, k, H - 1)) % mod;
							}
						}
					}
				}
			}
			return dp[A][B][C][D][K][H] = (int)res;
		}
	}

}
