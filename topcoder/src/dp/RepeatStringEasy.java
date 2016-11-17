package dp;

public class RepeatStringEasy {

	public static void main(String[] args) {
		RepeatStringEasy obj = new RepeatStringEasy();
		System.out.println(obj.maximalLength("ababaaaa"));
	}
	
	public int maximalLength(String s) {
		this.s = s;
		this.N = s.length();
		int max = 0;
		for (int i = 0; i < N; i++) {
			memo = new int[N][N];
			max = Math.max(max, recur(0, i, i));
		}
		return max;
	}
	
	private int recur(int i, int j, int divIdx) {
		if (i >= divIdx || j >= N) 
			return 0;
		if (memo[i][j] != 0) {
			return memo[i][j] - 1;
		}
		int ans = 0;
		if (s.charAt(i) == s.charAt(j)) {
			ans = Math.max(ans, recur(i + 1, j + 1, divIdx) + 2);
		} else {
			ans = Math.max(ans, recur(i, j + 1, divIdx));
			ans = Math.max(ans, recur(i + 1, j, divIdx));
		}
		memo[i][j] = ans + 1;
		return ans;
	}
	private int[][] memo;
	private int N;
	private String s;

}
