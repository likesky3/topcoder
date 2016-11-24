package review.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ContestSchedule {

	public static void main(String[] args) {
		ContestSchedule obj = new ContestSchedule();
		String[] contests = {"0 10 100", "10 20 100", "20 30 100"};
//		System.out.println(obj.expectedWinnings(contests));
		
		contests = new String[] {"1 20 20", "30 40 60", "15 35 90"};
		System.out.printf("recv=%f, correct=%b\n", obj.expectedWinnings(contests), 
				obj.expectedWinnings(contests) == 0.9);
		contests = new String[]{"1 10 100", "20 30 100"};
		System.out.printf("recv=%f, correct=%b\n", obj.expectedWinnings(contests), 
				obj.expectedWinnings(contests) == 2.0);
		contests = new String[] {
				"1361955 8940967 10","628145516 644285978 17","883515280 910484865 36",
				"762888635 769291174 52","98152102 136328674 1","9580638 20450682 50",
				"646139319 664648341 20","484027666 505290970 57","841082555 879295329 99",
				"940563715 966953344 4","579113528 595261527 25","925801172 962952912 9",
				"285845005 307916055 45","403573723 410697485 10","9467290 25698952 90",
				"631265400 650639733 3","520690249 559261759 96","491747709 531061081 86",
				"643215695 672420161 94","614608448 617341712 44","456517316 491770747 24",
				"806956091 828414045 88","528156706 559510719 15","158398859 190439269 4",
				"743974602 761975244 49","882264704 887725893 1","877444309 884479317 59",
				"785986538 806192822 19","732553407 747696021 81","132099860 148305810 97",
				"555144412 572785730 99","506507263 535927950 82","489726669 505160939 90",
				"793692316 804153358 99","901329638 919179990 10","29523791 44318662 58",
				"570497239 595701008 73","706091347 730328348 23","118054686 135301010 39",
				"307178252 337804001 93","896162463 900667971 39","271356542 273095245 80",
				"865692962 891795950 91","593986003 596160000 58","807688147 831190344 59",
				"468916697 496462595 92"
				};
		System.out.println(obj.expectedWinnings(contests));
	}
	
	// not correct algorithm, case like {"0 10 50", "10 20 30", "15 25 100"}
	public double expectedWinnings(String[] contests) {
		// preprocess information
		// parse the contests
		int N = contests.length;
		Contest[] array = new Contest[N]; 
		for (int i = 0; i < N; i++) {
			String[] tmp = contests[i].split(" ");
			array[i] = new Contest(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), Integer.parseInt(tmp[2]));
		}
		// sort contests base on the start time
		Arrays.sort(array, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				return ((Contest)o1).start - ((Contest)o2).start;
			}
		});
//		boolean[] used = new boolean[N];
		double maxProb = 0.0;
//		List<List<Contest>> opts = new ArrayList<>();
		for (int i = 0; i < N; i++) {
//			if (used[i])
//				continue;
//			List<Contest> opt = new ArrayList<Contest>();
//			opt.add(array[i]);
			Contest last = array[i];
			double prob = last.p;
			for (int j = i + 1; j < N; j++) {
				if (array[j].start >= last.end) {
//					opt.add(array[j]);
					last = array[j];
//					used[j] = true;
					prob += last.p;
				}
			}
			maxProb = Math.max(prob / 100.0, maxProb);
//			opts.add(opt);

		}
		
//		double maxProb = 0.0;
//		for (List<Contest> opt : opts) {
//			double prob = 0.0;
//			for (Contest g : opt) {
//				prob += g.p;
//			}
//			maxProb = Math.max(maxProb, prob);
//		}
		return maxProb;
	}
	
	private class Contest {
		public int start;
		public int end;
		public int p;
		public Contest(int s, int t, int p) {
			start = s;
			end = t;
			this.p = p;
		}
	}
	
}


