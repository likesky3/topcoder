package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArithmeticSlicesII {

	public static void main(String[] args) {
		ArithmeticSlicesII obj = new ArithmeticSlicesII();
		int[] A = {2147483638,2147483639,2147483640,2147483641,2147483642,2147483643,2147483644,2147483645,2147483646,2147483647,-2147483648,-2147483647,-2147483646,-2147483645,-2147483644,-2147483643,-2147483642,-2147483641,-2147483640,-2147483639};
		System.out.println(obj.numberOfArithmeticSlices(A));
	}

	public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3)
            return 0;
        int n = A.length;
        List<HashMap<Integer, Integer>>  maps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maps.add(new HashMap<Integer, Integer>());
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long diff = (long)A[i] - A[j];
                if (diff > Integer.MAX_VALUE || diff < -Integer.MAX_VALUE) {
                    maps.get(i).put(Integer.MIN_VALUE, maps.get(i).getOrDefault(Integer.MIN_VALUE, 0) + 1);
                } else {
                	int oldValue = maps.get(i).getOrDefault((int)diff, 0);
                	maps.get(i).put((int)diff, oldValue + maps.get(j).getOrDefault((int)diff, 0) + 1);
                }
            }
        }
        int sum = 0;
        for (int i = 2; i < n; i++) {
            for (int key : maps.get(i).keySet()) {
                sum += maps.get(i).get(key);
            }
            sum -= i;
        }
        return sum;
    }
	
	// memory limit exceed
	public int numberOfArithmeticSlices_v1(int[] A) {
        if (A == null || A.length < 3)
            return 0;
        int n = A.length;
        List<HashMap<Long, Integer>>  maps = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            maps.add(new HashMap<Long, Integer>());
        }
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                long diff = (long)A[i] - A[j];
                if (diff > Integer.MAX_VALUE || diff < -Integer.MAX_VALUE) {
                    maps.get(i).put((long)Integer.MIN_VALUE, maps.get(i).getOrDefault((long)Integer.MIN_VALUE, 0) + 1);
                } else {
                	int oldValue = maps.get(i).getOrDefault(diff, 0);
                	maps.get(i).put(diff, oldValue + maps.get(j).getOrDefault(diff, 0) + 1);
                }
            }
        }
        int sum = 0;
        for (int i = 2; i < n; i++) {
            for (long key : maps.get(i).keySet()) {
                sum += maps.get(i).get(key);
//                System.out.printf("maps[%d][%d]=%d\n", i, key, maps.get(i).get(key));
            }
            sum -= i;
//            System.out.printf("i=%d, old sum=%d, new sum=%d\n", i, sum + i, sum);
        }
        return sum;
    }
}
