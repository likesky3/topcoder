package dp;

public class Clicountingd2 {

	public static void main(String[] args) {
//		String[] g = {"0???","?0??","??0?","???0"};a
		String[] g = {"0??????", "?0????0", "??001??", "??00???", "??1?0??", "?????0?", "?0????0"};
		Clicountingd2 obj = new Clicountingd2();
		System.out.println(obj.count(g));
	}

	public int count(String[] g) {
		int n = g.length;
		int k = 0;
		int[][] h = new int[n][n];
		
		// set idx for each edge with '?'
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (g[i].charAt(j) != '?') {
					h[i][j] = h[j][i] = -1;
				} else {
					h[i][j] = h[j][i] = k++;
				}
			}
		}
		
		// dp[nbitMask] = kBitMask, each bit 1 in nbitMask is a vertex of a clique
		// to make the clique possible, some edges are required to be 1 as shown in kBitMask
		//int[] dp = new int[1<<n]; 
		// rdp[kBitMask] = nBits1, is a reverse to dp[nbitMask],
		// nBits1 is the number of bit 1 in the nbitMask
		int[] rdp = new int[1<<k];
		for (int nbitMask = 0; nbitMask < 1<<n; nbitMask++) {
			int kbitMask = 0;
			boolean possibble = true;
			for (int i = 0; i < n; i++) {
				if (!possibble)
					break;
				if ((nbitMask & (1 << i)) == 0)
					continue;
				for (int j = i + 1; j < n; j++) {
					if ((nbitMask & (1 << j)) == 0)
						continue;
					if (g[i].charAt(j) == '0') {
						possibble = false;
						break;
					} else if (g[i].charAt(j) == '?') {
						kbitMask |= 1 << h[i][j];
					}
				}
			}
			if (possibble) {
				//dp[nbitMask] = kbitMask;
				rdp[kbitMask] = Math.max(kbitMask, Integer.bitCount(nbitMask));
//				System.out.printf("dp[%s]=%d, rdp[%s]=%d\n", Integer.toBinaryString(nbitMask), dp[nbitMask],
//						Integer.toBinaryString(kbitMask), rdp[kbitMask]);
			}
		}

		int sum = 0;
		int[] f = new int[1<<k]; //f[i]: size of largest clique at kbitMask
		for (int kbitMask = 0; kbitMask < 1 << k; kbitMask++) {
			int max = rdp[kbitMask];
			for (int i = 0; i < k; i++) {
				max = Math.max(max, f[kbitMask & (~(1<<i))]);
			}
			f[kbitMask] = max;
			sum += max;
		}
		return sum;
	}
}
