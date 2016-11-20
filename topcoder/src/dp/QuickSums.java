package dp;

public class QuickSums {
	public int minSums(String numbers, int sum) {
		minOps = numbers.length();
		recur(numbers, (long)sum, 0);
		if (minOps < numbers.length())
			return minOps;
		else
			return -1;
	}
	private int minOps;
	private void recur(String s, long sum, int ops) {
//		System.out.printf("s=%s, sum=%d, ops=%d\n", s, sum, ops);
		if (s.isEmpty() && sum == 0) {
			minOps =Math.min(minOps, ops - 1);
			return;
		}
		if (s.isEmpty() || sum < 0) {
			return;
		}
		for (int i = 1; i <= s.length(); i++) {
			recur(s.substring(i), sum - Long.parseLong(s.substring(0, i)), ops + 1);
		}
	}
	public static void main(String[] args) {
		QuickSums obj = new QuickSums();
//		System.out.println(obj.minSums("1110", 3) == 3);
		System.out.println(obj.minSums2("123", 7) == -1);
		System.out.println(obj.minSums2("9230560001", 71) == 4);
	}

	public int minSums2(String numbers, int sum) {
		if (Long.parseLong(numbers) == sum)
			return 0;
		int N = numbers.length();
		int result = N;
		for (int mask = 1; mask < (1 << (N - 1)); mask++) {
			int total = 0;
			int left = 0;
			
			for (int i = 0; i < N - 1; i++) {
				if ((mask & (1 << i)) > 0) {
					total += Integer.parseInt(numbers.substring(left, i + 1));
					left = i + 1;
//					System.out.printf("mask=%d, total=%d,left=%d, i=%d\n", mask, total, left, i);
				}
			}
			if (left < N)
				total += Integer.parseInt(numbers.substring(left));
//			System.out.printf("mask=%d, total=%d,left=%d\n", mask, total, left);
			if (total == sum) {
				result = Math.min(result, Integer.bitCount(mask));
			}
		}
		return result == N ? -1 : result;
	}
}
