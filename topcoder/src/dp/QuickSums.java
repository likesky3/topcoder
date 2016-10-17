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
		System.out.printf("s=%s, sum=%d, ops=%d\n", s, sum, ops);
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
		System.out.println(obj.minSums("123", 7) == -1);
	}
}
