package dp;

import java.util.Arrays;

public class EllysCoprimesDiv2 {

	public static void main(String[] args) {
		EllysCoprimesDiv2 obj = new EllysCoprimesDiv2();
		int[] numbers = {2, 3, 7};
		System.out.println(obj.getCount(numbers) == 0);
		numbers = new int[]{2, 6};
		System.out.println(obj.getCount(numbers) == 1);
		numbers = new int[]{13, 1, 6, 20, 33};
		System.out.println(obj.getCount(numbers) == 0);
	}
	
	public int getCount(int[] numbers) {
		Arrays.sort(numbers);
		int res = 0;
		for (int i = 1; i < numbers.length; i++) {
			res += minimumFill(numbers[i - 1], numbers[i]);
		}
		return res;
	}
	private int minimumFill(int a, int b) {
		if (gcd(a, b) == 1) {
			return 0;
		} else {
			for (int i = a + 1; i <= b; i++) {
				if (gcd(a, i) == 1 && gcd(i, b) == 1)
					return 1;
			}
			return 2;
		}
	}
	private int gcd(int a, int b) {
		while (b > 0) {
			int c = a % b;
			a = b;
			b = c;
		}
		return a;
	}
}
