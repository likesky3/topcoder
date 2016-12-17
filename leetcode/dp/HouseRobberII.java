package dp;

public class HouseRobberII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int rob(int[] nums) {
		if (nums == null) return 0;
		if (nums.length == 1) return nums[0];
		if (nums.length == 2) return nums[0] > nums[1] ? nums[0] : nums[1];
		return Math.max(dp(nums, 0, nums.length - 2), dp(nums, 1, nums.length - 1));
	}
	
	private int dp2(int[] nums, int from, int to) {
		int[] dp = new int[to - from + 1];
		dp[from] = nums[from];
		dp[from + 1] = Math.max(nums[from], nums[from + 1]);
		for (int i = from + 2; i <= to; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]); 
		}
		return dp[to];
	}
	private int dp(int[] nums, int from, int to) {
		int prev2;
		int prev1 = nums[from];
		int curr = Math.max(nums[from], nums[from + 1]);
		for (int i = from + 2; i <= to; i++) {
			prev2 = prev1;
			prev1 = curr;
			curr = Math.max(prev1, prev2 + nums[i]);
		}
		return curr;
	}

}
