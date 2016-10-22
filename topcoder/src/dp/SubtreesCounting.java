package dp;

import java.util.ArrayList;
import java.util.List;

public class SubtreesCounting {
	private final int MOD = 1000000007;
	public int sumOfSizes(int n, int a0, int b, int c, int m) {
		List<List<Integer>> g = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			g.add(new ArrayList<>());
		}
		int[] parent = new int[n];
		parent[0] = -1;
		int[] a = new int[n];
		a[0] = a0;
		for (int i = 1; i < n - 1; i++) {
//			a[i] = (b * a[i - 1] + c) % m; // overflow at test cast 5
			a[i] = (int)((b * (long)a[i - 1] + c) % m);
		}
		for (int i = 1; i < n; i++) {
			parent[i] = a[i - 1] % i; // parent[i] is ensured < i
			g.get(parent[i]).add(i);
		}
		
		// countSubtrees[i][t]: number of subtrees with root at i with the first t children
		List<List<Integer>> countSubtrees = new ArrayList<>();
		List<List<Integer>> sumSize = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			countSubtrees.add(new ArrayList<>());
			sumSize.add(new ArrayList<>());
		}
		
		long res = 0;
		for (int i = n - 1; i >= 0; i--) {
			countSubtrees.get(i).add(1); // countSubtress[i][0]
			sumSize.get(i).add(1); // sumSize[i][0]
			for (int t = 1; t <= g.get(i).size(); t++) {
				int j = g.get(i).get(t - 1);
				long p = countSubtrees.get(i).get(t - 1);
				long q = sumSize.get(i).get(t - 1);
				long x = countSubtrees.get(j).get(g.get(j).size()) + 1;
				long y = sumSize.get(j).get(g.get(j).size());
				countSubtrees.get(i).add(t, (int) ((p * x) % MOD));
				sumSize.get(i).add(t, (int) ((p * y + q * x) % MOD));
//				System.out.printf("i=%d, j=%d, t=%d, count[%d][%d]=%d, sum[%d][%d]=%d\n",
//						i, j, t, i, t, countSubtrees[i][t], i, t, sumSize[i][t]);
			}
			res =(res + sumSize.get(i).get(g.get(i).size())) % MOD;
		}
		return (int)res;
	}
	
	public static void main(String[] args) {
		SubtreesCounting obj = new SubtreesCounting();
		System.out.println(obj.sumOfSizes(5,1,2,3,100));
		System.out.println(obj.sumOfSizes(100000, 123, 46645, 4564579, 1000000000));
	}
}

// split the original problem into smaller sub problem, 
// here sub problem is what's the size of a subtree whose root is i with t children
// then, a smaller sub problem again, with the first 0, 1, 2,... t children
