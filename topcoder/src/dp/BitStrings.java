package dp;

public class BitStrings {

	public static void main(String[] args) {
		BitStrings obj = new BitStrings();
		String[] list = {"1", "0", "100", "10"};
		System.out.println(obj.maxStrings(list, 2, 2));
	}
	
	public int maxStrings(String[] list, int numZeros, int numOnes) {
		int N = list.length;
		int[] counter0 = new int[N];
		int[] counter1 = new int[N];
		for (int i = 0; i < N; i++) {
			String s = list[i];
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '0') {
					counter0[i]++;
				} else {
					counter1[i]++;
				}
			}
		}
		
		int res = 0;
		for (int i = 0; i < (1 << N); i++) {
			int total0 = 0, total1 = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) > 0) {
					total0 += counter0[j];
					total1 += counter1[j];
				}
			}
			if (total0 <= numZeros && total1 <= numOnes) {
				res = Math.max(res, Integer.bitCount(i));
			}
		}
		return res;
	}

}
