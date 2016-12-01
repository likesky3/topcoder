package review.dp;

public class DeserializeSequence {

	public static void main(String[] args) {
		DeserializeSequence obj = new DeserializeSequence();
		System.out.println(obj.howMany("10010010010010010010010010010010010010010010010010"));
		System.out.println(obj.howMany2("10010010010010010010010010010010010010010010010010"));
		System.out.println(obj.howMany("9786543210"));
		System.out.println(obj.howMany2("9786543210"));
	}

	public int howMany(String str) {
		return recur(str, 1);
	}
	
	private int recur(String str, int prev) {
		if (str.length() == 0)
			return 1;
		int res = 0, curr = 0;
		for (int i = 0; i < str.length(); i++) {
			curr = curr * 10 + str.charAt(i) - '0';
			if (curr < prev) continue;
			if (curr > 1000000) break;
			res += recur(str.substring(i + 1), curr);
		}
		return res;
	}
	
	public int howMany2(String str) {
		return recur2(str, 1);
	}
	
	private int recur2(String str, int prev) {
		if (str.isEmpty())
			return 1;
		int res = 0;
		for (int i = 1; i <= str.length(); i++) {
			int curr = Integer.parseInt(str.substring(0, i));
			if (curr < prev) continue;
			if (curr > 1000000) break;
			res += recur(str.substring(i), curr);
		}
		return res;
	}
	
}
