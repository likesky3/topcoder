package dp;

import java.util.Arrays;

public class MaximalSquare {

	public static void main(String[] args) {
		char[][] matrix = {"0001".toCharArray(), "1101".toCharArray(), "1111".toCharArray(), "0111".toCharArray(), "0111".toCharArray()};
		MaximalSquare obj = new MaximalSquare();
		System.out.println(obj.maximalSquare(matrix));
	}
	
	public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] h = new int[2][n];
        int[][] maxEdge = new int[2][n];
        int prevWidth, currWidth = 0;
        int globalMaxEdge = 0;
        int prev, curr = 0;
        for (int i = 0; i < m; i++) {
            prev = curr;
            curr = 1 - prev;
            currWidth = 0;
            for (int j = 0; j < n; j++) {
                prevWidth = currWidth;
                if (matrix[i][j] == '1') {
                    h[curr][j] = h[prev][j] + 1;
                    currWidth = prevWidth + 1;
                    if (j > 0) {
                    	maxEdge[curr][j] = Math.min(maxEdge[prev][j - 1] + 1, Math.min(h[curr][j], currWidth));
                    } else {
                    	maxEdge[curr][j] = 1;
                    }
//                    System.out.printf("maxEdge[%d][%d]=%d, h=%d, w=%d\n", i, j, maxEdge[curr][j], h[curr][j], currWidth);
                    globalMaxEdge = Math.max(maxEdge[curr][j], globalMaxEdge);
                } else {
                     h[curr][j] = 0;
                    currWidth = 0;
                     maxEdge[curr][j] = 0;
                }
                
            }
        }
        return globalMaxEdge * globalMaxEdge;
    }

}
