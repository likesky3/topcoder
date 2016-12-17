package dp;

import java.util.PriorityQueue;

public class SuperUglyNumber {

	public static void main(String[] args) {

	}
	
	public int nthSuperUglyNumber(int n, int[] primes) {
		int[] ugly = new int[n];
		int k = primes.length;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < k; i++) {
			pq.offer(new Node(primes[i], 0, primes[i]));
		}
		ugly[0] = 1;
		for (int i = 1; i < n; i++) {
			Node next = pq.poll();
			ugly[i] = next.val;
			pq.offer(new Node(next.prime * ugly[next.idx + 1], next.idx + 1, next.prime));
			while (pq.peek().val == ugly[i]) {
				next = pq.poll();
				pq.offer(new Node(next.prime * ugly[next.idx + 1], next.idx + 1, next.prime));
			}
		}
		return ugly[n - 1];
	}
	
	private class Node implements Comparable<Node> {
		int val;
		int idx;
		int prime;
		public Node(int val, int idx, int prime) {
			this.val = val;
			this.idx = idx;
			this.prime = prime;
		}
		@Override
		public int compareTo(Node o) {
			return this.val - o.val;
		}
		
	}
}
