package dp;
public class BearPlayDiv2 {

	public static void main(String[] args) {
		BearPlayDiv2 obj = new BearPlayDiv2();
		System.out.println(obj.equalPiles(4, 6, 8));
	}
	
	public String equalPiles(int A, int B, int C) {
		int N = A + B + C;
		can = new boolean[N + 1][N + 1];
		int[] t = {A, B, C};
		if (N % 3 == 0) {
			f(t);
			if (can[N % 3][N % 3])
				return "possible";
		}
		return "impossible";
	}
	
	private boolean[][] can;
	private void f(int[] t) {
		if (can[t[0]][t[1]])
			return;
		can[t[0]][t[1]] = true;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (t[i] < t[j]) {
					int[] t2 = {t[i] * 2, t[j] - t[i], t[0 + 1 + 2 - i - j]};
					f(t2);
				}
			}
		}
	}	
}
