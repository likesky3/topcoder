package dp;

public class EvenRoute {
	// The parity of the size of a path starting at (0, 0) and ends at (x, y) depends solely on the points (x, y).
	// It does not matter what the shape or route or points visited in the middle of the route.
	// Prove it. Try one coordinate at a time. 
	public String isItPossible(int[] x, int[] y, int wantedParity) {
		for (int i = 0; i < x.length; i++) {
			if ((Math.abs(x[i]) + Math.abs(y[i])) % 2 == wantedParity) {
				return "CAN";
			}
		}
		return "CANNOT";
	}
}
