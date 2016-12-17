package search;

public class BinarySearch {

	public static void main(String[] args) {
		int[] nums = {0, 2, 2, 3, 3, 3};
		BinarySearch obj = new BinarySearch();
		System.out.println(obj.binarySearchMostRight(2, nums));
		System.out.println(obj.binarySearchMostRight(3, nums));
		System.out.println(obj.binarySearchMostRight(1, nums));

		System.out.println(obj.binarySearchMostLeft(2, nums));
		System.out.println(obj.binarySearchMostLeft(3, nums));
		System.out.println(obj.binarySearchMostLeft(1, nums));
	}

	public int binarySearchMostRight(int x, int[] nums) {
		int left = 0, right = nums.length - 1;
		while (right - left >= 2) {
			int mid = (left + right) / 2;
			if (x >= nums[mid]) {
				left = mid;
			} else {
				right = mid - 1;
			}
		}
		return x == nums[right] ? right : (x == nums[left] ? left : -1);
	}
	
	public int binarySearchMostLeft(int x, int[] nums) {
		int left = 0, right = nums.length - 1;
		while (right - left >= 2) {
			int mid = (left + right) / 2;
			if (x > nums[mid]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return x == nums[left] ? left : (x == nums[right] ? right : -1);
	}
	
}
