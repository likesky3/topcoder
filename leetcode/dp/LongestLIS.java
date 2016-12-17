package dp;

import java.util.ArrayList;
import java.util.List;

public class LongestLIS {

	public static void main(String[] args) {
		LongestLIS obj = new LongestLIS();
		int[] nums = {9,2,4,5,3,6};
		System.out.println(obj.lengthOfLIS(nums));
	}

	public int lengthOfLIS1(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		if (nums.length == 1)
			return 1;
	    int n = nums.length;
	    int[] tails = new int[n];
	    tails[0] = nums[0];
	    int size = 1;
	    for (int x : nums) {
	        int left = 0, right = size - 1;
	        while (left < right) {
	            int mid = (left + right) / 2;
	            if (x > tails[mid]) {
	                left = mid + 1;
	            } else if (x < tails[mid]) {
	                right = mid - 1;
	            } else {
	                break;
	            }
	        }
	        System.out.printf("x=%d, tails[%d]=%d, size=%d\n", x, left, tails[left], size);
	        if (x < tails[left]) {
	            tails[left] = x;
	            System.out.printf("update, tails[%d]=%d\n", left, tails[left]);
	        } else if (x > tails[left]) {
	        	tails[left + 1] = x;
	        	if (left == size - 1) size++;
	        	System.out.printf("extend length, size=%d\n", size);
	        }
	        System.out.println();
	    }
	    return size;
    }
	
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length == 0)
			return 0;
		if (nums.length == 1)
			return 1;
	    int n = nums.length;
	    int[] tails = new int[n];
	    tails[0] = nums[0];
	    int size = 1;
	    for (int x : nums) {
	        int left = 0, right = size;
	        // search the minimum number which is greater than x
	        while (left < right) {
	            int mid = (left + right) / 2;
	            if (x > tails[mid]) {
	                left = mid + 1;
	            } else if (x <= tails[mid]) {
	                right = mid;
	            }
	        }
	        if (left == size) {
	            tails[size++] = x;
	        } else {
	        	tails[left] = x;
	        }
	    }
	    return size;
    }
}
