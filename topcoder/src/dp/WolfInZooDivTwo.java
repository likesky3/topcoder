package dp;
import java.util.*;

public class WolfInZooDivTwo {

	public static void main(String[] args) {
		WolfInZooDivTwo obj = new WolfInZooDivTwo();
		String[] L = {"0 4 2 7"};
		String[] R = {"3 9 5 9"};
//		System.out.println(obj.count(10, L, R));
		L = new String[]{"0 2 2 7 10 1","3 16 22 30 33 38"," 42 44 49 51 57 60 62"," 65 69 72 74 77 7","8 81 84 88 91 93 96"};
		R = new String[]{"41 5 13 22 12 13 ","33 41 80 47 40 ","4","8 96 57 66 ","80 60 71 79"," 70 77 ","99"," 83 85 93 88 89 97 97 98"};
//		System.out.println(obj.count(100, L, R));
		L = new String[]{"0 1"};
		R = new String[] {"2 4"};
//		System.out.println(obj.count(5, L, R));
		L = new String[]{"5 7 12 11 7 8 2 17 14 0 7 11 8 19 20 11 14 4 20 1", "6 0"}; 
		R = new String[]{"14 19 18 15 15 14 15 20 16 1 15 18 19 20 20 18 17", " 16 20 17 12"};
//		System.out.println(obj.count(21, L, R));
		L = new String[] {"40 75 5 82 49 75 70 34 75 63 33 64 88 15 69 72 2 ", "52 73 78 42 35 68 57 83 70 46 66 24 97 11 30 26 2 ", "51 2 21 87 63 91 98 57 7 67 17 85 28 61 65 100 67 ", "27 37 70 7 0 23 62 4 40 50 88 55 15 40 98 26 14 7 ", "72 102 54 57 83 26 69 61 15 61 68 15 46 33 95 60 1", "9 98 62 20 55 82 87 86 39 3 77 80 62 88 1 48 50 20", " 53 2 69 11 74"};
		R = new String[] {"86 94 81 94 73 103 81 58 80 64 58 98 102 66 95 81", " 3 83 106 104 96 66 103 60 93 86 104 92 83 99 42 6", "3 104 80 89 87 103 94 102 101 104 57 100 90 80 107", " 90 105 106 107 87 44 41 86 25 100 102 99 68 99 74", " 89 80 64 85 102 71 43 12 105 103 102 63 89 27 100", " 92 88 90 75 79 105 95 96 75 55 106 62 64 96 90 93", " 91 101 84 103 103 104 106 11 107 74 29 60 21 95 7", "7 99"};
		System.out.println(obj.count(108, L, R));
	}

	public int count(int N, String[] L, String[] R) {
		// transfer L, R into int array
		int[] left = getValues(L);
		int[] right = getValues(R);
		int M = left.length;
		
		// preprocess, remove useless intervals
		List<Interval> intervals = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			intervals.add(new Interval(left[i], right[i]));
		}
		boolean[] shouldRmv = new boolean[M];
		for (int i = 0; i < M; i++) {
			for (int j = i + 1; j < M; j++) {
				Interval a = intervals.get(i);
				Interval b = intervals.get(j);
				System.out.printf("a:%d %d  b:%d %d\n", a.left, a.right, b.left, b.right);
				// ensure we remove at most 1
				if (a.left == b.left && a.right == b.right) {
					shouldRmv[i] = true;
				}
				else if (a.left <= b.left && a.right >= b.right) {
					shouldRmv[i] = true;
				}
				else if (b.left <= a.left && b.right >= a.right) {
					shouldRmv[j] = true;
				}
				
				// produce bug in case, there are two same intervals and would be removed by another interval
//				if (a.left <= b.left && a.right >= b.right) {
//					shouldRmv[i] = true;
//				}
//				if (b.left <= a.left && b.right >= a.right) {
//					shouldRmv[j] = true;
//				}
//				if (a.left == b.left && a.right == b.right) {
//					shouldRmv[j] = false;
//				}
			}
		}
		List<Interval> tmp = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			if (!shouldRmv[i]) {
				tmp.add(intervals.get(i));
				
			}
		}

		Interval[] validInters = new Interval[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			validInters[i] = tmp.get(i);
		}
		Arrays.sort(validInters, new MyComp());
	
//		System.out.println("validIntervals.length=" + validInters.length);
		// start dp
		long[] dp = new long[N];
		Interval first = validInters[0];
		for (int i = 0; i < first.right; i++)
			dp[i] = myPow(2, i + 1) % MOD;
		dp[first.right] = (myPow(2, first.right - first.left + 1) - 1) * (first.left > 0 ? dp[first.left - 1] : 1) % MOD; 
//		System.out.printf("dp[%d]=%d\n", validInters[0].right, dp[validInters[0].right]);
		for (int i = 1; i < validInters.length; i++) {
			Interval p = validInters[i-1];
			Interval q = validInters[i];
			System.out.printf("%d %d\n", q.left, q.right);
			for (int j = p.right + 1; j < q.right; j++) {
				dp[j] = myPow(2, j - p.right) * dp[p.right] % MOD;
			}
			if (p.right < q.left) {
				dp[q.right] = (myPow(2, q.right - q.left + 1) - 1) * dp[q.left - 1] % MOD;
			} else {
				long part1 = (myPow(2, q.right - p.right) - 1) * dp[p.right] % MOD;
				long part2 = 0;
				for (int j = q.left - 1; j < p.right; j++) {
//					System.out.printf("p:%d %d, q:%d, %d\n", p.left, p.right, q.left, q.right);
					part2 += dp[j];
				}
				//System.out.printf("i=%d, part1=%d, part2=%d\n", i, part1, part2);
				dp[q.right] = (part1 + part2) % MOD;
			}
			//System.out.printf("dp[%d]=%d\n", q.right, dp[q.right]);
		}
		Interval last = validInters[validInters.length - 1];
		if (N - 1 > last.right) {
			dp[N - 1] = myPow(2, (N - 1) - last.right) * dp[last.right] % MOD;
		}
		return (int)dp[N - 1];

	}
	
	private class Interval {
		public int left;
		public int right;
		public Interval(int a, int b) {
			left = a;
			right = b;
		}
	}
	
	private class MyComp implements Comparator<Interval> {

		@Override
		public int compare(Interval o1, Interval o2) {
			if (o1.right == o2.right) {
				return o1.left - o2.left;
			} else {
				return o1.right - o2.right;
			}
		}
		
	}
	
	private int[] getValues(String[] A) {
		StringBuilder sb = new StringBuilder();
		for (String s : A) {
			sb.append(s);
		}
		String[] tmp = sb.toString().split(" ");
		int[] result = new int[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			result[i] = Integer.parseInt(tmp[i]);
		}
		return result;
	}
	
	private long myPow(int a, int b) {
		long res = 1;
		for (int i = 0; i < b; i++) {
			res = res * a % MOD;
		}
		return res;
	}
	private final int MOD = 1000000007;
}
