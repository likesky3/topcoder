package dp;

public class RangeSumQuery2D {

	public static void main(String[] args) {
		int[][] matrix = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
		RangeSumQuery2D obj = new RangeSumQuery2D(matrix);
		System.out.println(obj.sumRegion(0, 0, 0, 2));
		System.out.println(obj.sumRegion(1, 2, 2, 2));
	}
	
	public RangeSumQuery2D(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int rows = matrix.length;
        int cols = matrix[0].length;
        sum = new int[rows][cols];
        sum[0][0] = matrix[0][0];
        for (int j = 1; j < cols; j++) {
            sum[0][j] = sum[0][j - 1] + matrix[0][j];
        }
        for (int i = 1; i < rows; i++) {
            sum[i][0] = sum[i - 1][0] + matrix[i][0];
            System.out.printf("sum[%d][%d]=%d\n", i, 0, sum[i][0]);
            int prev, curr = matrix[i][0];
            for (int j = 1; j < cols; j++) {
            	prev = curr;
                curr = prev  + matrix[i][j];
                sum[i][j] = sum[i - 1][j] + curr;
                System.out.printf("curr=%d, sum[%d][%d]=%d\n", curr, i, j, sum[i][j]);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0)
            return sum[row2][col2];
        if (row1 == 0)
            return sum[row2][col2] - sum[row2][col1 - 1];
        if (col1 == 0)
            return sum[row2][col2] - sum[row1 - 1][col2];
        return sum[row2][col2] - sum[row1 - 1][col2] - sum[row2][col1 - 1] + sum[row1 - 1][col1 - 1];
    }
    
    private int[][] sum;

}
