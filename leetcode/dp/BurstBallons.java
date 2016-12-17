package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BurstBallons {

	public static void main(String[] args) {
		BurstBallons obj = new BurstBallons();
//		int[] nums = {8,2,6,8,9,8,1,4,1,5,3,0,7,7,0,4,2};
//		int[] nums = {3,2,3,4};
		int[] nums = {3,1, 5, 8};
		System.out.println(obj.maxCoins(nums));
		System.out.println(obj.maxCoins2(nums));
	}

	
	
	public int maxCoins(int[] nums) {
		int n = nums.length;
		dp = new int[n][n][MAXBOUND][MAXBOUND];
		for (int[][][] arr3 : dp) {
			for (int[][] arr2 : arr3) {
				for (int[] arr1 : arr2) {
					Arrays.fill(arr1, -1);
				}
			}
		}
		this.nums = nums;
		return recur3(0, n - 1, 1, 1);
	}
	
	private int recur3(int i, int j, int x, int y) {
		if (i >= nums.length || j < 0)
			return 0;
		if (dp[i][j][x][y] != -1) {
			return dp[i][j][x][y];
		}
		if (i == j) {
//			System.out.printf("dp[%d][%d][%d][%d] = %d\n", i, j, x, y, nums[i] * x * y);
			return dp[i][j][x][y] = nums[i] * x * y;
		}
		if (i + 1 == j) {
			dp[i][j][x][y] = Math.max(x * nums[i] * nums[j] + x * nums[j] * y, nums[i] * nums[j] * y + x * nums[i] * y);
//			System.out.printf("dp[%d][%d][%d][%d] = %d\n", i, j, x, y, dp[i][j][x][y]);
			return dp[i][j][x][y];
		}
		int res = 0;
		for (int k = i; k <= j; k++) {
			res = Math.max(recur3(i, k - 1, x, nums[k]) + recur3(k + 1, j, nums[k], y) + nums[k] * x * y, res);
		}
		dp[i][j][x][y] = res;
//		System.out.printf("dp[%d][%d][%d][%d] = %d\n", i, j, x, y, dp[i][j][x][y]);
		return res;
	}
	
	//wrong: take nums[k] as the first burst ballon
    public int maxCoins3(int[] nums) {
		int n = nums.length;
		dp = new int[n][n][MAXBOUND][MAXBOUND];
		for (int[][][] arr3 : dp) {
			for (int[][] arr2 : arr3) {
				for (int[] arr1 : arr2) {
					Arrays.fill(arr1, -1);
				}
			}
		}
		this.nums = nums;
		return recur2(0, n - 1, 1, 1);
	}
	
	private int recur2(int i, int j, int p, int q) {
		if (dp[i][j][p][q] != -1)
			return dp[i][j][p][q];
		if (i == j) {
			return dp[i][j][p][q] = p * nums[i] * q;
		}
		if (i + 1 == j) {
			return dp[i][j][p][q] = Math.max(p * nums[i] * nums[j] + p * nums[j] * q, 
					nums[i] * nums[j] * q + p * nums[i] * q);
		}
		int result = 0;
		for (int k = i + 1; k < j; k++) {
			int common = nums[k - 1] * nums[k] * nums[k + 1];
			result = Math.max(recur2(i, k - 1, p, nums[k + 1]) + recur2(k + 1, j, 1, q) + common, result);
			result = Math.max(recur2(i, k - 1, p, 1) + recur2(k + 1, j, nums[k - 1], q) + common, result);
		}
		return dp[i][j][p][q] = result;
	}
	
	// the following 2 version would time out exceed
	public int maxCoins2(int[] nums) {
		if (nums.length == 0) return 0;
		if (nums.length == 1) return nums[0];
		int n = nums.length;
		// check memory
		StringBuilder key = new StringBuilder();
		for (int i = 0; i < n; i++) {
			key.append(nums[i]).append('_');
		}
		if (cache.containsKey(key.toString()))
			return cache.get(key.toString());
		// enumerate
		int res = 0;
		for (int i = 0; i < n; i++) {
			int curr = (i > 0 ? nums[i - 1] : 1) * nums[i] * (i < n - 1 ? nums[i + 1] : 1);
			int[] nums2 = new int[n - 1];
			int k = 0;
			for (int j = 0; j < n; j++) {
				if (j != i)
					nums2[k++] = nums[j];
			}
			res = Math.max(curr + maxCoins(nums2), res);
		}
		cache.put(key.toString(), res);
		return res;
    }
	
	private Map<String, Integer> cache = new HashMap<>();
	
	
	public int maxCoins1(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.memo = new HashMap<>();
        boolean[] isBurned = new boolean[n];
        return recur(isBurned, n);
    }
    
    private int recur(boolean[] isBurned, int remainNum) {
        if (remainNum == 0)
            return 0;
        String key = getKeyString(isBurned);
        if (memo.containsKey(key)) {
//        	System.out.printf("memo[%s]=%d\n", key, memo.get(key));
            return memo.get(key);
        }
        int res = 0;
//        System.out.println("--start " + key);
        for (int i = 0; i < n; i++) {
            if (isBurned[i]) continue;
            isBurned[i] = true;
            int curr = findARemain(isBurned, i, false) * nums[i] * findARemain(isBurned, i, true);
//            System.out.printf("i=%d, num[%d]=%d, curr=%d\n", i, i, nums[i], curr);
            res = Math.max(curr + recur(isBurned, remainNum - 1), res);
            isBurned[i] = false;
        }
//        System.out.println("---end " + key + " res=" + res + "\n");
        memo.put(key, res);
        return res;
    }
    
    private String getKeyString(boolean[] isBurned) {
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < n; i++) {
            key.append(isBurned[i] ? 1 : 0);
        }
        return key.toString();
    }
    
    private int findARemain(boolean[] isBurned, int i, boolean forward) {
    	if (forward) {
    		for (i = i + 1; i < n; i++) {
    			if (!isBurned[i]) {
    				return nums[i];
    			}
    		}
    		return 1;
    	} else {
    		for (i = i - 1; i >= 0; i--) {
    			if (!isBurned[i])
    				return nums[i];
    		}
    		return 1;
    	}
    }
    
    private int n;
    private int[] nums;
    private Map<String, Integer> memo;
	private int[] nums2;
	private int[][][][] dp;
	private final int MAXBOUND = 101;
}
