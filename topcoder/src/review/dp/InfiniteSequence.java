package review.dp;
import java.util.*;

public class InfiniteSequence {

	public static void main(String[] args) {
		InfiniteSequence obj = new InfiniteSequence();
		System.out.println(obj.calc(10000000, 3, 3));
	}
	
	public long calc(long n, int p, int q) {
		if (memo.containsKey(n)) {
			return memo.get(n);
		}
		if (n == 0) {
			return 1;
		}
		long res = calc(n / p, p, q) + calc(n / q, p, q);
		memo.put(n, res);
		return res;
	}
	
	private Map<Long, Long> memo = new HashMap<>();
}
