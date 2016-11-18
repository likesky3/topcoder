package dp;
import java.util.Arrays;

public class EmotionsDiv2 {

	public static void main(String[] args) {
		EmotionsDiv2 obj = new EmotionsDiv2();
		System.out.println(obj.printSmiles2(99));
	}

	// wrong, fail at cases like 99, 333
	public int printSmiles0(int smiles) {
		if (smiles == 2) {
			return 2;
		} else if (smiles % 2 == 1) {
			return smiles;
		} else if (smiles % 10 == 0) {
			int k = 0;
			while (smiles % 10 == 0) {
				k++;
				smiles /= 10;
			}
			return 7 * k + printSmiles(smiles) - 1;
		} else if ((smiles / 2) % 2 == 0) {
			return printSmiles(smiles / 2) + 2;
		} else {
			return smiles / 2 + 2;
		}
	}
	
	private static final int N = 1001;
	private static final int MAX = 1000;
	int[][] dp = new int[N][N];
	boolean[][] visited = new boolean[N][N];
	int _smiles;
	public int printSmiles(int smiles) {
		_smiles = smiles;
		for (int[] a : dp) {
			Arrays.fill(a, MAX);
		}
		return dfs(1, 0);
	}
	private int dfs(int num, int paste) {
		if (visited[num][paste])
			return dp[num][paste];
		visited[num][paste] = true;
		if (num == _smiles) {
			dp[num][paste] = 0;
			return 0;
		}
		if (num + paste <= _smiles)
			dp[num][paste] = Math.min(dp[num][paste], 1 + dfs(num + paste, paste)); // keep paste as what it is
		dp[num][paste] = Math.min(dp[num][paste], 1 + dfs(num, num)); // increase paste to num we already have.
		return dp[num][paste];
	}
	
	public int printSmiles2(int smiles) {
		_smiles = smiles;
		for (int[] d : dp) {
			Arrays.fill(d, -1);
		}
		return f(1, 0);
	}
	private int f(int num, int paste) {
		if (num == _smiles)
			return 0;
		if (dp[num][paste] != -1)
			return dp[num][paste];
		dp[num][paste] = MAX;
		// use the current paste
		if (paste > 0 && num + paste <= _smiles) {
			dp[num][paste] = Math.min(dp[num][paste], 1 + f(num + paste, paste));
		}
		// use all the current num as the new paste
		if (num + num < _smiles) {
			dp[num][paste] = Math.min(dp[num][paste], 2 + f(num + num, num));
		}
		return dp[num][paste];
	}
}
