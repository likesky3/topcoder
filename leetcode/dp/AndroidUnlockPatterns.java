package dp;

import java.util.*;

public class AndroidUnlockPatterns {
	public static void main(String[] args) {
		AndroidUnlockPatterns obj = new AndroidUnlockPatterns();
		System.out.println(obj.numberOfPatterns(1, 2));
	}
	
	public int numberOfPatterns(int m, int n) {
		visited = new boolean[10];
		skip = new int[10][10];
		skip[1][3] = skip[3][1] = 2;
		skip[1][7] = skip[7][1] = 4;
		skip[3][9] = skip[9][3] = 6;
		skip[7][9] = skip[9][7] = 8;
		skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 
				skip[4][6] = skip[6][4] = skip[2][8] = skip[8][2] = 5; 
		int result = 0;
		for (int i = m; i <= n; i++) {
			result += recur(1, i - 1) * 4; // 1, 3, 7, 9 are symmetric
			result += recur(2, i - 1) * 4; // 2, 4, 6, 8 are symmetric
			result += recur(5, i - 1);
		}
		return result;
	}
	
	private int recur(int curr, int remain) {
		if (remain == 0)
			return 1;
		visited[curr] = true;
		int result = 0;
		for (int i = 1; i < 10; i++) {
			if (!visited[i] && (skip[curr][i] == 0 || (visited[skip[curr][i]]))) {
				result += recur(i, remain - 1);
			}
		}
		visited[curr] = false;
		return result;
	}
	private boolean[] visited;
	private int[][] skip;
	// not fully understand the content of the problem, we do not need to go through any nodes
	// when we want to go from node 1 to node 6
	public int numberOfPatterns2(int m, int n) {
		map = new HashMap<>();
		map.put("1_6", new int[]{2, 5});
		map.put("1_8", new int[]{4, 5});
		map.put("1_9", new int[]{5});
		map.put("1_3", new int[]{2});
		map.put("1_7", new int[]{4});
		map.put("2_7", new int[]{4, 5});
		map.put("2_9", new int[]{5, 6});
		map.put("2_8", new int[]{5});
		map.put("3_4", new int[]{2, 5});
		map.put("3_8", new int[]{5, 6});
		map.put("3_7", new int[]{5});
		map.put("3_1", new int[]{2});
		map.put("3_9", new int[]{6});
		map.put("4_3", new int[]{2, 5});
		map.put("4_9", new int[]{5, 8});
		map.put("4_6", new int[]{5});
		map.put("6_1", new int[]{2, 5});
		map.put("6_7", new int[]{5, 8});
		map.put("6_4", new int[]{5});
		map.put("7_2", new int[]{4, 5});
		map.put("7_6", new int[]{5, 8});
		map.put("7_3", new int[]{5});
		map.put("7_1", new int[]{4});
		map.put("7_9", new int[]{8});
		map.put("8_1", new int[]{4, 5});
		map.put("8_3", new int[]{6, 5});
		map.put("8_2", new int[]{5});
		map.put("9_2", new int[]{6, 5});
		map.put("9_4", new int[]{5, 8});
		map.put("9_1", new int[]{5});
		map.put("9_3", new int[]{6});
		map.put("9_7", new int[]{8});
		neighs = new int[][]{{}, {2, 4, 5}, {1, 3, 4, 5, 6}, {2, 5, 6}, {1, 2, 5, 7, 8}, {1, 2, 3, 4, 6, 7, 8, 9},
				{2, 3, 5, 8, 9}, {4, 5, 8}, {7, 4, 5, 6, 9}, {5, 6, 8}};
		int result = 0;
		for (int need = m; need <= n; need++) {
			result += recur(m, (1 << 10) - 2, 0);
		}
		return result;
    }
	
	private int recur(int need, int remainKeys, int prev) {
		if (need == 0) {
			return 1;
		}
		int result = 0;
		for (int i = 1; i <= 9; i++) {
			if (i == prev) continue;
			if ((remainKeys & (1 << i)) > 0) {
				boolean isAvailable = prev == 0;
				if (!isAvailable) {
					for (int elem : neighs[i]) {
						if (prev == elem) {
							isAvailable = true;
							break;
						}
					}
					if (!isAvailable) {
						isAvailable = true;
//						System.out.printf("%d_%d\n", i, prev);
						for (int elem : map.get(i + "_" + prev)) {
							if ((remainKeys & (1 << elem)) > 0) { 
								isAvailable = false;
								break;
							}
						}
					}
				}
				if (isAvailable) {
					result += recur(need - 1, remainKeys ^ (1 << i), i);
				}
			}
		}
		return result;
	}
	
	private Map<String, int[]> map;
	private int[][] neighs;
}
