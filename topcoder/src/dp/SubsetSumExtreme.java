package dp;
import java.util.*;
public class SubsetSumExtreme {

	public static void main(String[] args) {
		SubsetSumExtreme obj = new SubsetSumExtreme();
		int[] block = {1, 2, 3};
		int[] face = {6, 5};
		System.out.println(obj.getExpectation(block, face));
		block = new int[]{1, 2, 1};
		face = new int[] {1, 2};
		System.out.println(obj.getExpectation(block, face));
		block = new int[]{10,11,12};
		face = new int[]{3,4,5,6};
		System.out.println(obj.getExpectation(block, face));
		block = new int[] {1,1,1,1};
		face = new int[]{1};
		System.out.println(obj.getExpectation(block, face));
		block = new int[]{968,423,592,419,321,253,62,42,12,32,2,4};
		face = new int[]{968,423,592,419,321,253,62,42,12,32,2,4};
		System.out.println(obj.getExpectation(block, face));
	}
	
	public double getExpectation(int[] block, int[] face) {
		// prepare information
		this.block = block;
		this.face = face;
		N = face.length;
		M = block.length;
		memo = new double[1 << M];
		Arrays.fill(memo, -1);
		return recur((1 << M) - 1);
	}
	private int[] block;
	private int[] face;
	private int N; // face.length
	private int M; // block.length
	private double[] memo;
	private double recur(int remains) {
		if (memo[remains] != -1) {
			return memo[remains];
		}
		double ans = 0; 
		for (int i = 0; i < N; i++) {
			List<Integer> solutions = checkSum(remains, face[i]);
			if (solutions.size() > 0) {
//				System.out.printf("remains=%s, checkSum().size=%d\n", Integer.toBinaryString(remains), solutions.size());
				double minExp = 12001;
				for (int opt : solutions) {
					minExp = Math.min(minExp, recur(remains ^ opt));
				}
				ans += minExp;
			} else {
				for (int j = 0; j < M; j++) {
					if ((remains & (1 << j)) > 0) {
						ans += block[j];
					}
				}
			}
		}
		return memo[remains] = ans / N;
	}
	
	private List<Integer> checkSum(int remains, int target) {
		List<Integer> result = new ArrayList<Integer>();
		for (int opt = 1; opt <= remains; opt++) {
//			System.out.printf("remains=%s, opt=%s\n", Integer.toBinaryString(remains), Integer.toBinaryString(target));
			// judge is bitmask b is subset of bitmask a 
//			boolean valid = true;
//			for (int i = 0; i < M; i++) {
//				if ((opt & (1 << i)) > 0 && (remains & (1 << i)) == 0) {
//					valid = false;
//					break;
//				}
//			}
//			if (valid) {
			if ((remains | opt) == remains) {
				int sum = 0;
				for (int i = 0; i < M; i++) {
					if ((opt & (1 << i)) > 0) {
						sum += block[i];
					}
				}
				if (sum == target) {
					result.add(opt);
				}
			}
		}
		return result;
	}

}
