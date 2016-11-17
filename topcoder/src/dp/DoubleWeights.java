package dp;

public class DoubleWeights {

	public static void main(String[] args) {
		DoubleWeights obj = new DoubleWeights();
		String[] weight1 = {"..14",
				 "..94",
				 "19..",
				 "44.."};
		String[] weight2 = {"..94",
				 "..14",
				 "91..",
				 "44.."};
		System.out.println(obj.minimalCost(weight1, weight2));
	}
	
	public int minimalCost(String[] weight1, String[] weight2) {
		N = weight1.length;
		w1 = weight1;
		w2 = weight2;
		used = new boolean[N];
		minCost = Integer.MAX_VALUE;
		used[0] = true;
		recur(0, 0, 0);
		return minCost == Integer.MAX_VALUE ? -1 : minCost;
	}

	private int N;
	private String[] w1, w2;
	private boolean[] used;
	private int minCost;

	private void recur(int p, int cost1, int cost2) {
		if (p == 1) {
			minCost = Math.min(minCost, cost1 * cost2);
			return;
		}
		if (cost1 * cost2 > minCost)
			return;
		for (int i = 0; i < N; i++) {
			if (i == p || used[i] || w1[p].charAt(i) == '.')
				continue;
			used[i] = true;
			recur(i, cost1 + w1[p].charAt(i) - '0', cost2 + w2[p].charAt(i) - '0');
			used[i] = false;
		}
	}

}
