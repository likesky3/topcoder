package dp;

public class EllysCheckers {

	public static void main(String[] args) {
		EllysCheckers obj = new EllysCheckers();
		System.out.println(obj.getWinner(".o...ooo..oo.."));
	}
	
	public String getWinner(String board) {
		// prepare information
		N = board.length();
		memo = new int[1 << N];
		int initStatus = 0;
		for (int i = 0; i < N; i++) { // better to use bit operation |
			initStatus = initStatus * 2 + (board.charAt(i) == 'o' ? 1 : 0);
		}
		// start recursion
		return recur(initStatus) ? "YES" : "NO";
	}
	
	private int N;
	private int[] memo;
	private boolean recur(int status) {
//		System.out.println(status);
		status &= ~1; // clear the last bit
		if (status == 0)
			return false;
		if (memo[status] != 0) {
			return memo[status] == 1 ? true : false;
		}
		boolean succ = false;
		
		// check each checker from left to right	
		for (int i = 0; i < N - 1; i++) {
			int offset = N - 1 - i; // offset to the right edge
			if ((status & (1 << offset)) > 0) { // find a checker
				if ((status & (1 << (offset - 1))) == 0) { // next pos is an empty cell
					int newStatus = (status & (~(1 << offset))) | (1 << (offset - 1));
					if (!recur(newStatus))
						succ = true;
				}
				if (i + 3 < N && (status & (1 << (offset -1))) > 0
						&& (status & (1 << (offset - 2))) > 0
						&& (status & (1 << (offset - 3))) == 0) { // ok to have a jump
					int newStatus = (status & (~(1 << offset))) | (1 << (offset - 3));
					if (!recur(newStatus))
						succ = true;
				}
			}
		}
		memo[status] = succ ? 1 : -1;
		return succ;
	}

	private int n;
	private int[] mem;
	public String getWinner2(String board) {
		this.n = board.length();
		mem = new int[1 << n];
		int mask = 0;
		for (int i = 0; i < n - 1; i++) {
			if (board.charAt(i) == 'o') {
				mask |= 1 << i;
			}
		}
		return recur2(mask) ? "YES" : "NO"; 
	}
	private boolean hasBit(int mask, int i) {
		return (mask & (1 << i)) != 0;
	}
	private boolean recur2(int mask) {
		if (mem[mask] != 0) {
			return mem[mask] == 1 ? true : false;
		}
		boolean can = false;
		
		// check each checker from right to left
		for (int i = 0; i < n - 1; i++) {
			if (!hasBit(mask, i)) // empty cell
				continue;
			if (!hasBit(mask, i + 1)) { // take a walk move
				int newMask = mask;
				newMask ^= (1 << i); // clear bit i
				if (i + 1 < n - 1) { // checker move to position i+1
					newMask |= (1 << (i + 1));
				}
				can = can || !recur(newMask);
			}
			if ((i < n - 3) && hasBit(mask, i + 1) && hasBit(mask, i + 2) && !hasBit(mask, i + 3)) {
				int newMask = mask;
				newMask ^= 1 << i;
				if (i + 3 < n - 1) {
					newMask |= 1 << (i + 3);
				}
				can = can || !recur(newMask);
			}
		}
		mem[mask] = can ? 1 : 2;
		return can;
	}
}
