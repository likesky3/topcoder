package dp;

public class DeserializeSequence {

	public static void main(String[] args) {
		DeserializeSequence obj = new DeserializeSequence();
		System.out.println(obj.howMany("001"));
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
			if(curr < prev) 
				continue;
			if (curr > 1000000)
				break;
			res += recur(str.substring(i + 1), curr);
		}
		return res;
	}

}
