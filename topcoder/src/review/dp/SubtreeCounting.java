package review.dp;

import java.util.ArrayList;
import java.util.List;

public class SubtreeCounting {

	public static void main(String[] args) {
		SubtreeCounting obj = new SubtreeCounting();
//		System.out.println(obj.sumOfSizes(1, 1, 1, 1, 1));
//		System.out.println(obj.sumOfSizes(2,5,6,7,8));
//		System.out.println(obj.sumOfSizes(5,1,2,3,100));
		System.out.println(obj.sumOfSizes(100000, 123, 46645, 4564579, 1000000000));
		
	}
	
	public int sumOfSizes(int n, int a0, int b, int c, int m) {
		int[] parent = new int[n];
		parent[0] = -1;
		int[] a = new int[n];
		a[0] = a0;
		for (int i = 1; i < n -1; i++) {
			a[i] = (int)(((long)b * a[i - 1] + c) % m);
		}
		List<List<Integer>> childrenInfo = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			childrenInfo.add(new ArrayList<>());
		}
		for (int i = 1; i < n; i++) {
			parent[i] = a[i - 1] % i;
			childrenInfo.get(parent[i]).add(i);
		}
		List<List<Integer>> countSubtrees = new ArrayList<>();
		List<List<Integer>> sumSizes = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			countSubtrees.add(new ArrayList<>());
			sumSizes.add(new ArrayList<>());
		}
		long total = 0;
		for (int i = n - 1; i >= 0; i--) {
			countSubtrees.get(i).add(1);
			sumSizes.get(i).add(1);
			int childrenNum = childrenInfo.get(i).size();
			for (int t = 1; t <= childrenNum; t++) {
				int j = childrenInfo.get(i).get(t - 1);
				int childrenNum_j = childrenInfo.get(j).size();
				long A = countSubtrees.get(i).get(t - 1);
				long B = countSubtrees.get(j).get(childrenNum_j) + 1;
				long C = sumSizes.get(i).get(t - 1);
				long D = sumSizes.get(j).get(childrenNum_j);
				countSubtrees.get(i).add((int)(A * B % mod));
				sumSizes.get(i).add((int)((A * D + B * C) % mod));
			}
			total = (total + sumSizes.get(i).get(childrenNum)) % mod;
			System.out.printf("i=%d, total=%d\n", i, total);
		}
		return (int)total;
	}
	private final int mod = 1000000007;

}
