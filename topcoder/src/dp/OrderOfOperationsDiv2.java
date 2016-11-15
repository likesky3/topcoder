package dp;

import java.util.Arrays;

public class OrderOfOperationsDiv2 {
	// Method 1, fail at test 3, greedy
	public int minTime1(String[] s) {
		int n = s.length;
		int m = s[0].length();
		int[][] needMemo = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				needMemo[i][j] = s[i].charAt(j) - '0';
			}
		}
		int totalCost = 0;
		for (int i = 0; i < n; i++) {
			// scan for the least memory instruction
			int minMemo = m + 1;
			int minIndex = -1;
			for (int p = 0; p < n; p++) {
				int c = 0;
				for (int q = 0; q < m; q++) {
					if (needMemo[p][q] != 0)
						c++;
				}
				if (c < minMemo) {
					minMemo = c;
					minIndex = p;
				}
			}
			
			// add cost
			totalCost += minMemo * minMemo;
			// update the needMemo[][]
			
			for (int p = 0; p < n; p++) {
				if (needMemo[p][0] == -1 || p == minIndex) {
					continue;
				}
				for (int q = 0; q < m; q++) {
					if (needMemo[minIndex][q] == 1) {
						needMemo[p][q] = 0;
					}
				}
			}
			// update the executed instruction
			for (int q = 0; q < m; q++) {
				needMemo[minIndex][q] = -1;
			}
			// DEBUG
			System.out.printf("i=%d, minMemo=%d, minIndex=%d, cost=%d \n", i, minMemo, minIndex, totalCost);
//			for (int p = 0; p < n; p++) {
//				for (int q = 0; q < m; q++) {
//					System.out.print(needMemo[p][q] + " ");
//				}
//				System.out.println();
//			}
		}
		return totalCost;
	}
	
	// the point of view here: the access status of memory
	public int minTime(String[] s) {
		int n = s.length;
		int m = s[0].length();
		int[] masks = new int[n];
		int fmask = 0;
		// get the bit masks for each instruction and the final needed mask 
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (s[i].charAt(j) == '1') {
					masks[i] |= (1 << j);
				}
			}
			fmask |= masks[i];
		}
		// fmask might have some zeros in it.
		int[] dist = new int[1 << m];
		Arrays.fill(dist, 10000000); // special attention, not use Integer.MAX_VALUE
		dist[0] = 0;
		for (int mask = 0; mask < (1 << m); mask++) {
			for (int i = 0; i < n; i++) {
				// union
				int nmask = mask | masks[i];
				int emask = nmask - mask;
				int k = Integer.bitCount(emask);
				dist[nmask] = Math.min(dist[nmask], dist[mask] + k * k);
//				System.out.printf("mask=%d, i=%d, dist[%d]=%d\n", mask, i, nmask, dist[nmask]);
			}
		}
		return dist[fmask];
	}
	
	public static void main(String[] args) {
		OrderOfOperationsDiv2 obj = new OrderOfOperationsDiv2();
		String[] s = {
				 "111",
				 "001",
				 "010"
				};
//		System.out.println(obj.minTime2(s) == 3);
		
		s = new String[] {
						 "11101",
						 "00111",
						 "10101",
						 "00000",
						 "11000"
						};
//		System.out.println(obj.minTime2(s) == 9);
		
		//test 3
		s = new String[]{"01000100000000001101", 
						"00000000000000010001", 
						"00000000000000000010", 
						"01100000010000000000", 
						"00000010000100110000", 
						"00010101000001000000", 
						"00000010010000010000", 
						"00010010000001000000", 
						"10000000000000000000", 
						"00001011001001010001", 
						"00000000000111000000", 
						"00101001000000000010", 
						"01000000000001100000", 
						"00101001000100000000", 
						"01100101100010000000", 
						"00000100000101011110", 
						"00010001000001001011", 
						"00100000100000010100", 
						"00000100000010000010", 
						"00000000010010000000"};
//		System.out.println(obj.minTime2(s) == 26);
		
		s = new String[] {"11111111111111111111"};
		System.out.println(obj.minTime2(s) == 400);
	}

	// the point of view here: the execution status of instructions 
	public int minTime2(String[] s) {
		//prepare information
		int N = s.length;
		M = s[0].length();
		int[] t = new int[N]; // transformed each string in s to a int in t
		int[] dp = new int[1<<N]; // dp[i] is an executed status mask, if the k-th bit in dp[i] is 1, then the k-th instruction has been executed
		int[] mem = new int[1<<N]; // memory status corresponding to each executed status, the k-th bit in mem[i] is 1 says the k-th cell is in the cache
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				t[i] = t[i] * 2 + s[i].charAt(j) - '0';
			}
		}
		
		// start dp
		for (int status = 1; status < 1 << N; status++) {
			dp[status] = 4000; // limit time cost is 20^2=400
			for (int i = 0; i < N; i++) {
				if (((1 << i) & status) > 0) { // instruction i should be executed when come to "status"
					int prevStatus = status & (~(1 << i)); // clear one bit in status
					int newCost = dp[prevStatus] + calCost(t[i], mem[prevStatus]); 
					if ( newCost < dp[status]) {
						dp[status] = newCost;
						mem[status] = updateMem(mem[prevStatus], t[i]); 
					}
				}
			}
		}
		return dp[(1<<N) - 1];
	}
	
	private int M;
	private int calCost(int status, int mem) {
		int k = 0;
		for (int i = 0; i < M; i++) {
			if (((1 << i) & status) > 0 && ((1 << i) & mem) == 0) {
				// cell needed in status is not in cache yet
				k++;
			}
		}
		return k * k;
	}
	
	private int updateMem(int prevMem, int status) {
		int mem = prevMem;
		for (int i = 0; i < M; i++) {
			if (((1 << i) & status) > 0 && ((1 << i) & prevMem) == 0) {
				mem |= (1 << i);
			}
		}
		return mem;
	}
}
