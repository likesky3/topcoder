package review.dp;

import java.util.Arrays;

public class WallGameDiv2 {

	public static void main(String[] args) {
		String[] costs = {"0xxxx"
				,"1x111"
				,"1x1x1"
				,"11191"
				,"xxxx1"};
		WallGameDiv2 obj = new WallGameDiv2();
//		System.out.println(obj.play2(costs));
//		System.out.println(obj.play3(costs));
		costs = new String[]{"0698023477896606x2235481563x59345762591987823x663"
				,"1x88x8338355814562x2096x7x68546x18x54xx1077xx5131"
				,"64343565721335639575x18059738478x156x781476124780"
				,"2139850139989209547489708x3466104x5x3979260330074"
				,"15316x57171x182167994729710304x24339370252x2x8846"
				,"459x479948xx26916349194540891252317x99x4329x34x91"
				,"96x3631804253899x69460666282614302698504342364742"
				,"4x41693527443x7987953128673046855x793298x8747219x"
				,"7735427289436x56129435153x83x37703808694432026643"
				,"340x973216747311x970578x9324423865921864682853036"
				,"x1442314831447x860181542569525471281x762734425650"
				,"x756258910x0529918564126476x481206117984425792x97"
				,"467692076x43x91258xx3xx079x34x29xx916574022682343"
				,"9307x08x451x2882604411x67995x333x045x0x5xx4644590"
				,"4x9x088309856x342242x12x79x2935566358156323631235"
				,"04596921625156134477422x2691011895x8564609x837773"
				,"223x353086929x27222x48467846970564701987061975216"
				,"x4x5887805x89746997xx1419x758406034689902x6152567"
				,"20573059x699979871151x444449x5170122650576586x898"
				,"683344308229681464514453186x51040742xx138x5170x93"
				,"1219892x9407xx63107x24x4914598xx4x478x31485x69139"
				,"856756530006196x8722179365838x9037411399x41126560"
				,"73012x9290145x1764125785844477355xx827269976x4x56"
				,"37x95684445661771730x80xx2x459547x780556228951360"
				,"54532923632041379753304212490929413x377x204659874"
				,"30801x8716360708478705081091961915925276739027360"
				,"5x74x4x39091353819x10x433010250089676063173896656"
				,"03x07174x648272618831383631629x020633861270224x38"
				,"855475149124358107x635160129488205151x45274x18854"
				,"091902044504xx868401923845074542x50x143161647934x"
				,"71215871802698346x390x2570413992678429588x5866973"
				,"87x4538137828472265480468315701832x24590429832627"
				,"9479550007750x658x618193862x80317248236583631x846"
				,"49802902x511965239855908151316389x972x253946284x6"
				,"053078091010241324x8166428x1x93x83809001454563464"
				,"2176345x693826342x093950x12x7290x1186505760xx978x"
				,"x9244898104910492948x2500050208763770568x92514431"
				,"6855xx7x145213846746325656963x0419064369747824511"
				,"88x15690xxx31x20312255171137133511507008114887695"
				,"x391503034x01776xx30264908792724712819642x291x750"
				,"17x1921464904885298x58x58xx174x7x673958x9615x9230"
				,"x9217049564455797269x484428813681307xx85205112873"
				,"19360179004x70496337008802296x7758386170452xx359x"
				,"5057547822326798x0x0569420173277288269x486x582463"
				,"2287970x0x474635353111x85933x33443884726179587xx9"
				,"0x697597684843071327073893661811597376x4767247755"
				,"668920978869307x17435748153x4233659379063530x646x"
				,"0019868300350499779516950730410231x9x18749463x537"
				,"00508xx083203827x42144x147181308668x3192478607467"};
//		System.out.println(obj.play2(costs));
		System.out.println(obj.play3(costs));
	}
	
	private final int blockedCost = -250000;
	int[] dRow = {0, 0, -1};
	int[] dCol = {1, -1, 0};
	// wrong algorithm, when we calculate dp[i][j], the optimal path may comes from dp[i][j +1] which we haven't calculated
	// yet according to the algorithm, in order to make it correct, add one more dimension to track the previous cell,
	// mark the direction between the previous one and the current one is enough 
	public int play(String[] costs) {
		int n = costs.length;
		int m = costs[0].length();
		char[][] board = new char[n + 1][m + 1];
		for (int i = 0; i < n; i++) {
			board[i] = costs[i].toCharArray();
		}
		int[][] dp = new int[n][m];
		for (int[] p : dp)
			Arrays.fill(p, blockedCost);
		dp[0][0] = 0;
		// process row 0
		for (int j = 1; j < m; j++) {
			if (board[0][j] != 'x') {
				dp[0][j] = dp[0][j - 1] + board[0][j] - '0';
			} else {
				break;
			}
		}
		// process row from 1 to n - 2
		for (int i = 1; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'x')
					continue;
				for (int k = 0; k < dRow.length; k++) {
					int prevRow = i + dRow[k];
					int prevCol = j + dCol[k];
					if (prevRow < 0 || prevCol < 0 || prevCol >= m)
						continue;
					dp[i][j] = Math.max(dp[i][j], dp[prevRow][prevCol]); 
				}
				if (dp[i][j] != blockedCost) {
					dp[i][j] += board[i][j] - '0';
				}
//				System.out.printf("dp[%d][%d]=%d\n", i, j, dp[i][j]);
			}
		}
		// process the last row
		int maxCost = 0;
		for (int j = 0; j < m; j++) {
			if (board[n - 1][j] != 'x' && dp[n - 2][j] != blockedCost) {
				maxCost = Math.max(maxCost, dp[n - 2][j] + board[n - 1][j] - '0');
			}
		}
		return maxCost;
	}
	
	private final int UP = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	
	// time: O(n*m*m)
	// space: O(n*m)
	public int play2(String[] costs) {
		int n = costs.length;
		int m = costs[0].length();
		// dp[i][j][UP]: max cost with the prev being upwards
		// dp[i][j][LEFT]: max cost with the prev being left
		// dp[i][j][RIGHT]: max cost with the prev being right
		int[][][] dp = new int[n][m][3];
		for (int[][] a : dp) {
			for (int[] b : a) {
				Arrays.fill(b, Integer.MIN_VALUE);
			}
		}
		// dp2[i][j]: max cost from [0][0] to [i][j]
		int[][] dp2 = new int[n][m];
		for (int[] a : dp2) {
			Arrays.fill(a, Integer.MIN_VALUE);
		}
		// base case
		for (int i = 0; i < 3; i++) {
			dp[0][0][i] = 0;
		}
		dp2[0][0] = 0;
		
		// start dp: process row 1 to n - 2
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				if (costs[i].charAt(j) == 'x')
					continue;
				int currCost = costs[i].charAt(j) - '0';
				// calculate cost with the prev beging upwards first
				if (i > 0) {
					dp[i][j][UP] = dp2[i - 1][j] + currCost; 
				}
				// calculate cost with the prev being left
				if (j > 0)
					dp[i][j][LEFT] = Math.max(dp[i][j - 1][LEFT], dp[i][j - 1][UP]) + currCost;
			}
			
			if (i > 0) {
//				dp[i][m - 1][RIGHT] = dp2[i - 1][m - 1]; // not needed, since each time calculate dp[i][j][RIGHT] would consider dp[i][j + 1][UP]
				for (int j = m - 2; j >= 0; j--) {
					if (costs[i].charAt(j) == 'x')
						continue;
					// calculate cost with the prev being right
					dp[i][j][RIGHT] = Math.max(dp[i][j + 1][RIGHT], dp[i][j + 1][UP]) + costs[i].charAt(j) - '0';
				}
			}
			for (int j = 0; j < m; j++) {
				dp2[i][j] = Math.max(dp[i][j][UP], Math.max(dp[i][j][LEFT], dp[i][j][RIGHT]));
			}
		}
		int maxCost = 0;
		for (int j = 0; j < m; j++) {
			if (costs[n - 1].charAt(j) != 'x') {
				maxCost = Math.max(maxCost, dp2[n - 2][j] + costs[n - 1].charAt(j) - '0');
			}
		}
		return maxCost;
	}
	
	// time: O(n*m*m)
	// space: O(n*m*m)
	public int play3(String[] costs) {
		int n = costs.length;
		int m = costs[0].length();
		// dp[i][out][in]: max cost when arrived in row i from col 'in' in row i - 1, and go to row i + 1 from 'out'
		int[][][] dp = new int[n][m][m];
		for (int[][] a2 : dp) {
			for (int[] a1: a2) {
				Arrays.fill(a1, Integer.MIN_VALUE);
			}
		}
		dp[0][0][0] = 0;
		// dp2[i][j]: max cost when go out of row i from col j
		int[][] dp2 = new int[2][m];
		for (int[] a1: dp2) {
			Arrays.fill(a1, Integer.MIN_VALUE);
		}
		dp2[0][0] = 0;
		// process row 0, each cell in row 0 can only be arrived from cell[0][0]
		for (int out = 1; out < m; out++) {
			if (costs[0].charAt(out) != 'x') {
				dp[0][out][0] = dp[0][out - 1][0] + costs[0].charAt(out) - '0';
				dp2[0][out] = dp[0][out][0];
			} else {
				break;
			}
		}
		// process the rows 1 --- n - 2
		for (int i = 1; i < n - 1; i++) {
			for (int out = 0; out < m; out++) {
				if (costs[i].charAt(out) == 'x')
					continue;
				// c[beg][end]: cost from cell[i][beg] to cell[i][end]
				int[][] c = new int[m][m];
				for (int beg = 0; beg < m; beg++) {
					c[beg][beg] = costs[i].charAt(beg) == 'x' ? Integer.MIN_VALUE : costs[i].charAt(beg) - '0';
					for (int end = beg + 1; end < m; end++) {
						if (c[beg][end - 1] == Integer.MIN_VALUE || costs[i].charAt(end) == 'x') {
							c[beg][end] = Integer.MIN_VALUE;
						} else {
							c[beg][end] = c[beg][end - 1] + costs[i].charAt(end) - '0';
						}
						c[end][beg] = c[beg][end];
					}
				}
				int maxCost = Integer.MIN_VALUE; // max cost when go out at 'out'
				for (int in = 0; in < m; in++) {
					if (dp2[(i - 1) % 2][in] != Integer.MIN_VALUE && c[in][out] != Integer.MIN_VALUE) { // check if reachable
						dp[i][out][in] = dp2[(i - 1) % 2][in] + c[in][out];
						maxCost = Math.max(maxCost, dp[i][out][in]);
					}
				}
				dp2[i % 2][out] = maxCost;
//				System.out.printf("dp2[%d][%d]=%d\n", i, out, dp2[i][out]);
			}
		}
		// process the last row
		int maxCost = 0;
		for (int out = 0; out < m; out++) {
			if (costs[n - 1].charAt(out) != 'x')
				maxCost = Math.max(maxCost, dp2[(n - 2) % 2][out] + costs[n - 1].charAt(out) - '0');
		}
		
		return maxCost;
	}
}
