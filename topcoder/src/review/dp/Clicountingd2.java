package review.dp;

public class Clicountingd2 {
	public static void main(String[] args) {
		Clicountingd2 obj = new Clicountingd2();
		String[] g = {"0?","?0"};
		System.out.println(obj.count(g));
	}
	public int count(String[] g) {
		int n = g.length;
		// indexing those '?' edges;
		int[][] h = new int[n][n];
		int k = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				char c = g[i].charAt(j);
				if (c != '?') {
					h[i][j] = h[j][i] = -1;
				} else {
					h[i][j] = h[j][i] = k++;
				}
			}
		}
		// start dp
		// dp[vertexMask] = edgeMask: for graph represented by vertexMask to be a clique, some ? edges needed to be set to 1
		// rdp[edgeMask] = cliqueSize: reverse of dp[], its value is the largest clique size we get when those ? edges set to 1 as shown in edgeMask
		// we only need rdp[], dp[] is helpful to understand the idea
		int[] rdp = new int[1 << k];
		for (int vertexMask = 1; vertexMask < 1 << n; vertexMask++) {
			int edgeMask = 0;
			boolean possible = true; // indicates whether graph represented by vertexMask is a clique
			for (int i = 0; i < n; i++) {
				if ((vertexMask & (1 << i)) == 0) continue; // current vertex i is not in the graph
				for (int j = i + 1; j < n; j++) {
					if ((vertexMask & (1 << j)) == 0) continue;
					if (g[i].charAt(j) == '0') {
						possible = false;
						break;
					} else if (g[i].charAt(j) == '?') {
						edgeMask |= 1 << h[i][j];
					}
				}
				if (!possible) break;
			}
			if (possible) {
				rdp[edgeMask] = Math.max(rdp[edgeMask], Integer.bitCount(vertexMask));
				System.out.printf("vertexMask=%s, edgeMask=%s, rdp[%d]=%d\n", 
						Integer.toBinaryString(vertexMask), Integer.toBinaryString(edgeMask), 
						edgeMask, rdp[edgeMask]);
			}
		}
		
		// set optimal value for 2^k graph and sum up
		int sum = 0;
		int[] f = new int[1 << k];
		for (int edgeMask = 0; edgeMask < 1 << k; edgeMask++) {
			f[edgeMask] = rdp[edgeMask];
			for (int i = 0; i < k; i++) {
				if ((edgeMask & (1 << i)) > 0)
					f[edgeMask] = Math.max(f[edgeMask], f[edgeMask ^ (1 << i)]);
			}
			sum += f[edgeMask];
		}
		return sum;
	}
}
