package dp;

import java.util.ArrayList;
import java.util.List;

public class DequeSort {

	public static void main(String[] args) {
		DequeSort obj = new DequeSort();
		int[] data = {3,4,5,2,1,0};
//		System.out.println(obj.minDeques(data));
		data = new int[]{0, 2, 1, 4, 3, 6, 5, 8, 7, 9};
		System.out.println(obj.minDeques(data));
	}
	
	public int minDeques(int[] data) {
		List<Integer> fronts = new ArrayList<>();
		List<Integer> backs = new ArrayList<>();
		for (int i = 0; i < data.length; i++) {
			boolean hasBeenPushed = false;
			for (int j = 0; j < fronts.size(); j++) {
				if (data[i] < fronts.get(j)) {
					boolean isSafe = true;
					for (int k = 0; k < data.length; k++) {
						if (data[i] < data[k] && data[k] < fronts.get(j)) {
							isSafe = false;
							break;
						}
					}
					if (isSafe) {
						System.out.printf("i=%d, data[%d]=%d, front[%d]\n", i, i, data[i], j);
						fronts.set(j, data[i]);
						hasBeenPushed = true;
						break;
					}
				}
				if (!hasBeenPushed && data[i] > backs.get(j)) {
					boolean isSafe = true;
					for (int k = 0; k < data.length; k++) {
						if (backs.get(j) < data[k] && data[k] < data[i]) {
							isSafe = false;
							break;
						}
					}
					if (isSafe) {
						System.out.printf("i=%d, data[%d]=%d, back[%d]\n", i, i, data[i], j);
						backs.set(j, data[i]);
						hasBeenPushed = true;
						break;
					}
				}
			}
			if (!hasBeenPushed) {
				fronts.add(data[i]);
				backs.add(data[i]);
			}
		}
		return fronts.size();
	}
}
