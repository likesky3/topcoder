package dp;

public class StrIIRec {
	public String recovstr(int n, int minInv, String minStr) {
		StringBuilder res = new StringBuilder();
		boolean[] used = new boolean[n];
		int invCnt = 0;
		boolean smaller = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// select the smallest letter which has not been used 
				// and not smaller than letter of minStr in the same position
				if (used[j] || (smaller && i < minStr.length() && ((char) j + 'a') < minStr.charAt(i))) {
					continue;
				}
				if (i >= minStr.length() || (char)j + 'a' > minStr.charAt(i)) {
					smaller = false;
				}
				used[j] = true;
				int cnt = 0; // inv count brought by current letter
				for (int k = 0; k < n; k++) {
					if (!used[k] && k < j) {
						cnt++;
					}
				}
				int r = n - (i + 1); // maximum inv count we can expect with the remaining unused letters
				if (invCnt + cnt + r * (r - 1) / 2 >= minInv) {
					res.append((char)(j + 'a'));
					invCnt += cnt;
					break;
				} else {
					used[j] = false;
				}
				
			}
		}
		return res.toString();
	}
	
	public String recovstr1(int n, int minInv, String minStr) {
		boolean used[] = new boolean[n];
		for (int i = 0; i < minStr.length(); i++) {
			used[minStr.charAt(i) - 'a'] = true;
		}
		StringBuilder sb = new StringBuilder(minStr);
		for (int i = 0; i < n; i++) {
			if (!used[i]) {
				sb.append((char)(i + 'a'));
			}
		}
		String s = sb.toString();
		while (countInv(s) < minInv) {
			s = next(s);
		}
		return s;
	}
	
	private int countInv(String s) {
		int cnt = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) > s.charAt(j))
					cnt++;
			}
		}
		return cnt;
	}
	
	private String next(String s) {
		for (int i = s.length() - 2; i >= 0; i--) {
			for (int j = s.length() - 1; j > i; j--) {
				if (s.charAt(i) < s.charAt(j)) {
					StringBuilder sb = new StringBuilder();
					sb.append(s.substring(0, i));
					sb.append(s.charAt(j));
					sb.append(s.substring(i + 1, j));
					sb.append(s.charAt(i));
					sb.append(s.substring(j + 1));
					return sb.toString();
				}
			}
		}
		return "";
	}

	
	public static void main(String[] args) {
		StrIIRec obj = new StrIIRec();
		System.out.println(obj.recovstr1(9, 1, "efcdgab"));
	}
}
