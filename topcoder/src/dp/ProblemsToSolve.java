package dp;import javax.swing.text.PlainDocument;

public class ProblemsToSolve {
	// This is greedy strategy, the correctness should be proved, often it would fail at some case, e.g. test case 2
	public int minNumber1(int[] pleasantness, int varity) {
		int min = pleasantness[0];
		int minIdx = 0;
		int max = pleasantness[0];
		int maxIdx = 0;
		for (int i = 1; i < pleasantness.length; i++) {
			System.out.printf("i=%d, p[%d]=%d, min=%d, minIdx=%d, max=%d, maxIdx=%d\n",
					i, i, pleasantness[i], min, minIdx, max, maxIdx);
			if (Math.abs(pleasantness[i] - min) >= varity) {
				return (i - minIdx + 1) / 2 + (minIdx + 1) / 2 + 1;
			} else if (Math.abs(pleasantness[i] - max) >= varity) {
				return (i - maxIdx + 1) / 2 + (maxIdx + 1) / 2 + 1;
			}
			if (pleasantness[i] < min) {
				min = pleasantness[i];
				minIdx = i;
			}
			if (pleasantness[i] > max) {
				max = pleasantness[i];
				maxIdx = i;
			}
		}
		return pleasantness.length;
	}
	
	public int minNumber(int[] pleasantness, int varity) {
		int min = pleasantness.length;
		for (int i = 1; i < pleasantness.length; i++) {
			for (int j = 0; j < i; j++) {
				if (Math.abs(pleasantness[i] - pleasantness[j]) >= varity) {
					min = Math.min(min, (i - j + 1) / 2 + (j + 1) / 2 + 1);
				}
			}
		}
		return min;
	}
	public static void main(String[] args) {
		ProblemsToSolve obj = new ProblemsToSolve();
		int[] A = {1, 2, 3};
//		System.out.println(obj.minNumber(A, 2));
		System.out.println(obj.minNumber2(A, 2) == 2);
		
		// test case 2
		A = new int[]{9, 11, 2, 7, 3, 1, 15, 2, 15, 14, 15, 4, 13, 7, 3, 12, 17, 7, 4, 3};
		System.out.println(obj.minNumber2(A, 11) == 4);
	}
	
	public int minNumber2(int[] pleasantness, int variety) {
		int N = pleasantness.length;
		if (N <= 1)
			return 1;
		int needDo = N;
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (Math.abs(pleasantness[i] - pleasantness[j]) >= variety) {
					needDo = Math.min(needDo, ((i - j) + 1) / 2 + (j + 1) / 2 + 1);
				}
			}
			
		}
		return needDo;
	}
}
