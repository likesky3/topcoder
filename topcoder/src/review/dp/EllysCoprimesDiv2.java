package review.dp;

import java.util.Arrays;

public class EllysCoprimesDiv2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EllysCoprimesDiv2 obj = new EllysCoprimesDiv2();
		int[] numbers = {2200, 42, 2184, 17};
		System.out.println(obj.getCount(numbers));
		numbers = new int[] {55780, 44918, 55653, 4762, 41536, 40083, 79260, 7374, 24124, 91858, 7856,
				 12999, 64025, 12706, 19770, 71495, 32817, 79309, 53779, 8421, 97984, 34586,
				 893, 64549, 77792, 12143, 52732, 94416, 54207, 51811, 80845, 67079, 14829,
				 25350, 22976, 23932, 62273, 58871, 82358, 13283, 33667, 64263, 1337, 42666};
		System.out.println(obj.getCount(numbers));
	}
	
	public int getCount(int[] numbers) {
		Arrays.sort(numbers);
		int N = numbers.length;
		int count = 0;
		for (int i = 0; i < N - 1; i++) {
			if (gcd(numbers[i], numbers[i + 1]) > 1) {
				boolean onlyNeedOne = false; 
				for (int j = numbers[i] + 1; j < numbers[i + 1]; j++) {
					if (gcd(numbers[i], j) == 1 && gcd(j, numbers[i + 1]) == 1) {
						onlyNeedOne = true;
						break;
					}
				}
				if (onlyNeedOne)
					count += 1;
				else
					count += 2;
			}
		}
		return count;
	}
	
	private int gcd(int a, int b) {
		if (a < b)
			return gcd(b, a);
		int c = a % b;
		if (c == 0)
			return b;
		else
			return gcd(b, c);
	}

}
