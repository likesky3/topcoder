package dp;

import java.util.Comparator;
import java.util.PriorityQueue;

public class UglyNumberII {

	public static void main(String[] args) {
		UglyNumberII obj = new UglyNumberII();
		for (int i = 1; i <= 10; i++) {
//			System.out.println(obj.nthUglyNumber(i));
		}
		System.out.println(obj.nthUglyNumber(1600));
		long a = (long)439453125 * 5;
		System.out.println(a);
		System.out.println((long)(439453125 * 5 ) >= Integer.MAX_VALUE);
	}
	
	public int nthUglyNumber(int n) {
		MyComp myComp = new MyComp();
		PriorityQueue<Node> pq = new PriorityQueue<>(100, myComp);
		pq.offer(new Node(1, 1));
		Node curr = null;
		for (int i = 1; i <= n; i++) {
			curr = pq.poll();
			if ((long)curr.val * 2 >= Integer.MAX_VALUE)
				continue;
			if (curr.lastFactor <= 2) {
					pq.offer(new Node(curr.val * 2, 2));
			}
			if ((long)curr.val * 3 >= Integer.MAX_VALUE)
				continue;
			if (curr.lastFactor <= 3) {
				pq.offer(new Node(curr.val * 3, 3));
			}
			if ((long)curr.val * 5  >= Integer.MAX_VALUE)
				continue;
			if (curr.lastFactor <= 5) {
				pq.offer(new Node(curr.val * 5, 5));
			}
			System.out.printf("i=%d: %d*2=%d, *3=%d, *5=%d\n", i, curr.val, curr.val * 2, curr.val * 3, curr.val * 5);
		}
		return curr.val;
	}
	
	private class Node{
		int val;
		int lastFactor;
		public Node(int value, int factor) {
			this.val = value;
			this.lastFactor = factor;
		}
	}
	
	private class MyComp implements Comparator {

		@Override
		public int compare(Object o1, Object o2) {
			return ((Node)o1).val - ((Node)o2).val;
		}
	}

}
