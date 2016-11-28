package review.dp;

import java.util.*;

public class DequeSort {

	public static void main(String[] args) {
		DequeSort obj = new DequeSort();
		int[] data = {3, 6, 0, 9, 5, 4};
		System.out.println(obj.minDeques(data));
		data = new int[]{0, 2, 1, 4, 3, 6, 5, 8, 7, 9};
		System.out.println(obj.minDeques(data));
	}
	
	public int minDeques(int[] data) {
		List<int[]> deques = new ArrayList<>();
		deques.add(new int[]{data[0], data[0]});
		int N = data.length;
		for (int i = 1; i < N; i++) {
			boolean findADeque = false;
			for (int j = 0; j < deques.size(); j++) {
				int front = deques.get(j)[0];
				int end = deques.get(j)[1];
				boolean goToEnd = data[i] > end;
				boolean okToEnter = true;
				for (int k = i + 1; k < N; k++) {
					if (goToEnd) {
						if (data[k] > end && data[k] < data[i]) {
							okToEnter = false;
							break;
						}
					} else {
						if (data[i] < data[k] && data[k] < end) {
							okToEnter = false;
							break;
						}
					}
				}
				if (okToEnter) {
					front = Math.min(front, data[i]);
					end = Math.max(end, data[i]);
					findADeque = true;
					break;
				}
			}
			if (!findADeque) {
				deques.add(new int[]{data[i], data[i]});
			}
		}
		return deques.size();
	}
	
}
