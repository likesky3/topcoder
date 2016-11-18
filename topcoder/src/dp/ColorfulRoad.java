package dp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ColorfulRoad {

	public static void main(String[] args) {
		ColorfulRoad obj = new ColorfulRoad();
		System.out.println(obj.getMin("RBRGBGBGGBGRGGG"));
	}
	
public int getMin(String road) {
		Map<Character, Character> map = new HashMap<>();
		map.put('R', 'B');
		map.put('G', 'R');
		map.put('B', 'G');
		int N = road.length();
		boolean[] canArrive = new boolean[N];
		canArrive[0] = true;
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i < N; i++) {
			char prevColor = map.get(road.charAt(i));
			for (int j = i - 1; j >= 0; j--) {
				if (road.charAt(j) == prevColor && canArrive[j]) {
					dp[i] = Math.min(dp[i],dp[j] + (j - i) * (j - i));
				}
			}
			if (dp[i] < Integer.MAX_VALUE) {
				canArrive[i] = true;
			}
		}
		if (canArrive[N - 1])
			return dp[N - 1];
		else
			return -1;
	}

}
