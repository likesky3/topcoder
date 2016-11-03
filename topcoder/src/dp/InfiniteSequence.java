package dp;

import java.util.HashMap;
import java.util.Map;

public class InfiniteSequence {
	private Map<Long, Long> memo = new HashMap<>();
	public long calc(long n, int p, int q) {
		if (n == 0)
			return 1;
		if (memo.containsKey(n))
			return memo.get(n);
		long res = calc(n/p, p, q) + calc(n/q, p, q);
		memo.put(n, res);
		return res;
	}
	public static void main(String[] args) {
		InfiniteSequence obj = new InfiniteSequence();
		System.out.println(obj.calc(0, 2, 300));
		System.out.println(obj.calc(7, 2, 3));
	}
}
