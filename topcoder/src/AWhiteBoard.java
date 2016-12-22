import java.util.*;

public class AWhiteBoard {

	public static void main(String[] args) {
		AWhiteBoard obj = new AWhiteBoard();
		int[] nums1 = {6,3,9,0,5,6}; 
		int[] nums2 = {2,2,5,2,1,4,4,5,7,8,9,3,1,6,9,7,0};
//		int[] nums1 = {9,0,5,6}; 
//		int[] nums2 = {9,7,0};
		int[] res = obj.maxNumber(nums1, nums2, 23);
		if (res != null) {
			for (int n : res)
				System.out.print(n);
		}
	}
	
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int m = nums1.length; 
		int n = nums2.length;
		int minLenFrom1 = Math.max(0, k - nums2.length);
		int maxLenFrom1 = Math.min(k, nums1.length);
		int[] ans = new int[k];
		for (int i = minLenFrom1; i <= maxLenFrom1; i++) {
			int[] curr = merge(maxArray(nums1, i), maxArray(nums2, k - i));
			if (isGreater(curr, 0, ans, 0)) {
				ans = curr;
			}
		}
		return ans;
    }
	
	private int[] maxArray(int[] nums, int k) {
		int[] ans = new int[k];
		for (int i = 0, j = 0; i < nums.length; i++) {
			while (j > 0 && j + nums.length - i > k && nums[i] > ans[j - 1]) {
				j--;
			}
			if (j < k) {
				ans[j++] = nums[i];
			}
		}
		return ans;
	}
	
	private int[] merge(int[] nums1, int[] nums2) {
		int[] ans = new int[nums1.length + nums2.length];
		for (int i = 0, j = 0, k = 0; k < ans.length; k++) {
			ans[k] = isGreater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
		}
		return ans;
	}
	
	private boolean isGreater(int[] a, int aBeg, int[] b, int bBeg) {
		int i = aBeg, j = bBeg;
		for (; i < a.length && j < b.length; i++, j++) {
			if (a[i] > b[j])
				return true;
			else if (a[i] < b[j])
				return false;
		}
		return j == b.length;
	}
	
	private boolean isGreater2(int[] a, int aBeg, int[] b, int bBeg) {
		int i = aBeg, j = bBeg;
		while (i < a.length && j < b.length && a[i] == b[j]) {
			i++;
			j++;
		}
		return j == b.length || i < a.length && a[i] > b[j];	
	}
	
    
    
    private void printArray(int[] nums) {
    	for (int n : nums) {
    		System.out.print(n);
    	}
    	System.out.println();
    }
    
}
