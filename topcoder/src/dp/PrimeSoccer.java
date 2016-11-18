package dp;

public class PrimeSoccer {

	public static void main(String[] args) {
		PrimeSoccer obj = new PrimeSoccer();
		System.out.println(obj.getProbability(50, 50));
	}
	
	public double getProbability(int skillOfTeamA, int skillOfTeamB) {
		comb = new long[19][19];
		for (int a = 1; a <= 18; a++) {
			comb[a][0] = 1;
			comb[a][1] = a;
			for (int b = 2; b <= a; b++) {
				comb[a][b] = comb[a - 1][b] + comb[a - 1][b - 1];
			}
		}
		double primeA = calProb(skillOfTeamA / 100.0);
		double primeB = calProb(skillOfTeamB / 100.0);
		return primeA + primeB - primeA * primeB;
	}
	
	private long[][] comb;
	private int[] primes = {2, 3, 5, 7, 11, 13, 17};
	private double calProb(double p) {
		double result = 0;
		for (int i = 0; i < primes.length; i++) {
			int primeNum = primes[i];
			result += comb[18][primeNum] * Math.pow(p, primeNum) * Math.pow(1 - p, 18 - primeNum); 
		}
		return result;
	}

}
