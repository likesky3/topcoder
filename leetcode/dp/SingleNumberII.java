package dp;

public class SingleNumberII {

	public static void main(String[] args) {

	}
	
	public int singleNumber(int[] nums) {
		int x1 = 0, x2 = 0;
		int mask = 0;
		for (int num : nums) {
			x2 ^= x1 & num;
			x1 ^= num;
			mask = ~(x2 & x1);
			x2 &= mask;
			x1 &= mask;
		}
		return x1;
	}
}
