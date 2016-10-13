package dp;

// when multiply, remember two negative number will produce a large positive result 
public class FoxPlayingGame {
	public double theMax(int nA, int nB, int paramA, int paramB) {
		double[][] max = new double[nA + 1][nB + 1];
		double[][] min = new double[nA + 1][nB + 1];
		double scoreA = paramA / 1000.0;
		double scoreB = paramB / 1000.0;
		for (int i = 1; i <= nA; i++) {
			max[i][0] = max[i - 1][0] + scoreA;
			min[i][0] = min[i - 1][0] + scoreA;
			for (int j = 1; j <= nB; j++) {
				max[i][j] = Math.max(max[i - 1][j] + scoreA, Math.max(max[i][j - 1] * scoreB, min[i][j - 1] * scoreB));
				min[i][j] = Math.min(min[i - 1][j] + scoreA, Math.min(max[i][j - 1] * scoreB, min[i][j - 1] * scoreB));
//				System.out.printf("max[%d][%d]=%f, min[%d][%d]=%f, max[%d][%d]=%f, min[%d][%d]=%f,max[%d][%d]=%f, min[%d][%d]=%f \n",
//						i, j, max[i][j], i, j, min[i][j],
//						(i-1), j, max[i-1][j], (i-1), j, min[i-1][j],
//						i, j-1, max[i][j-1], i, j-1,min[i][j-1]);
			}
		}
		return max[nA][nB];
	}
	public static void main(String[] args) {
		FoxPlayingGame obj = new FoxPlayingGame();
//		System.out.println(obj.theMax(5, 4, 3000, 2000));
//		System.out.println(obj.theMax(3, 3, 2000, 100));
//		System.out.println(obj.theMax(4, 3, -2000, 2000));	
//		System.out.println(obj.theMax(5, 5, 2000, -2000));	
//		System.out.println(obj.theMax(41, 34, 9876, -1234));
		
		System.out.println(Math.abs(obj.theMax(23, 34, -9422, -180) - 39.00708) < 1e-9);
	}
}
