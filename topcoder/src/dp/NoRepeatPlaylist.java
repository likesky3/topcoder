package dp;
import java.util.Arrays;

public class NoRepeatPlaylist {
	public int numberPlaylists(int N, int M, int P) {
		this.N = N;
		this.M = M;
		this.P = P;
		dp = new int[P + 1][N + 1];
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
		return (int)recur(0, 0);
	}
	
	private int[][] dp;
	private int N, M, P;
	private int MOD = 1000000007;
	private long recur(int position, int usedSize) {
		if (position == P) { // the playlist is full
			return usedSize == N ? 1 : 0; // check the completeness rule
		}
		if (dp[position][usedSize] != -1)
			return dp[position][usedSize];
		long result = 0;
		if (usedSize < N) // use the song from haven't played set
			result += (N - usedSize) * recur(position + 1, usedSize + 1);
		if (usedSize > M) // use the song from already added to the playlist set
			result += (usedSize - M) * recur(position + 1, usedSize);
		result %= MOD;
		return dp[position][usedSize] = (int)result;
	}
	
	public int numberPlaylists2(int N, int M, int P) {
		// dp[i][j]: number of ways of using the first j songs to make playlist of length i
		long [][] dp = new long[P + 1][N + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= P; i++) {
			for (int j = 1; j <= i && j <= N; j++) {
				dp[i][j] = dp[i - 1][j - 1] * (N - (j - 1)) + dp[i - 1][j] * Math.max(j - M, 0);
				dp[i][j] %= MOD;
			}
		}
		return (int)dp[P][N];
	}
	
	public static void main(String[] args) {
		NoRepeatPlaylist obj = new NoRepeatPlaylist();
		System.out.println(obj.numberPlaylists(1, 0, 1));
		System.out.println(obj.numberPlaylists(50, 5, 100));
		System.out.println(obj.numberPlaylists2(50, 5, 100));
	}
}
