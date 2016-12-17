package dp;

import java.util.Stack;

public class MaximalRectangle {

	public static void main(String[] args) {
//		["01101","11010","01110","11110","11111","00000"]
		char[][] matrix = {"01101".toCharArray(), "11010".toCharArray(), "01110".toCharArray(), "11110".toCharArray(), 
				"11111".toCharArray(), "00000".toCharArray()};
		MaximalRectangle obj = new MaximalRectangle();
		System.out.println(obj.maximalRectangle(matrix));
	}
	
	// time limit exceed
	public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] h = new int[2][n];
        int ans = 0;
        int prev, curr = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < m; i++) {
            prev = curr;
            curr = 1 - prev;
            for (int j = 0; j < n; j++) {
            	h[curr][j] = 0; // init to 0
                if (matrix[i][j] == '1') {
                    h[curr][j] = h[prev][j] + 1;
                }
//                System.out.printf("h[%d][%d]=%d ", i, j, h[curr][j]);
                while (!stack.isEmpty() && h[curr][stack.peek()] > h[curr][j]) {
                	ans = Math.max(ans, getRectArea(stack, h, curr, j));
                }
                System.out.println("-----");
                stack.push(j);
            }
            while (!stack.isEmpty()) {
               	ans = Math.max(ans, getRectArea(stack, h, curr, n));
            }
        }
        return ans;
    }
    
    private int getRectArea(Stack<Integer> stack, int[][] h, int curr, int firstOuterIdx) {
    	int right = stack.pop();
        while(!stack.isEmpty() && h[curr][stack.peek()] == h[curr][right]) {
            stack.pop();
        }
        // special attention, not use right - stack.peek(), consider case ["01101", "11010", "01110"]
        int width = stack.isEmpty() ? firstOuterIdx: firstOuterIdx- stack.peek() - 1;
//        System.out.printf("width=%d, height=%d, area=%d\n", width, h[curr][right], width * h[curr][right]);
        return width * h[curr][right];
    }
}
