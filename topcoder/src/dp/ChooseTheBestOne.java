package dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ChooseTheBestOne {

	public static void main(String[] args) {
		ChooseTheBestOne obj = new ChooseTheBestOne();
		System.out.println(obj.countNumber(1));
		System.out.println(obj.countNumber(2));
		System.out.println(obj.countNumber(3));
		System.out.println(obj.countNumber(4));
		System.out.println(obj.countNumber(5));
		System.out.println(obj.countNumber(10));
	}
	
	public int countNumber(int N) {
		if (N == 1)
			return 1;
		List<Integer> list = new LinkedList<Integer>();
		for (int i = 2; i <= N; i++) {
			list.add(i);
		}
		
		int startIdx = 0;
		for (int t = 2; t < N; t++) {
			int rmvIdx = (startIdx + t * t * t - 1) % list.size();
			list.remove(rmvIdx);
			startIdx = rmvIdx;
			if (startIdx == list.size())
				startIdx = 0;
		}
		return list.get(0);
	}

	public int countNumber2(int N) {
		List<Integer> nums = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			nums.add(i);
		}
		int pos = 0;
		for (long t = 1; t < N; t++) {
			int counter = 1;
			pos = (int) ((pos + t * t * t - 1) % nums.size()); 
			nums.remove(pos);
			if (pos == nums.size())
				pos = 0;
		}
		return nums.get(0);
	}
}
