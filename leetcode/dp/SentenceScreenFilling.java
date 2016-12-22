package dp;

public class SentenceScreenFilling {

	public static void main(String[] args) {
		SentenceScreenFilling obj = new SentenceScreenFilling();
		String[] sentence = {"a", "bcd", "e"};
		System.out.println(obj.wordsTyping(sentence, 3, 6));
	}
	
	public int wordsTyping(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0)
            return 0;
        int n = sentence.length;
        int total = 0;
        for (String s : sentence) {
            total += s.length() + 1;
        }
        int times = 0;
        int r = 0, c = 0;
        int i = 0;
        
        while (r < rows && c < cols) {
            int cycles = (cols - c) / total;
            times += cycles;
            c += cycles * total;
            while (c + sentence[i].length() <= cols) {
                c += sentence[i].length() + 1;
                i++;
                if (i ==n) {
                    times++;
                    i = 0;
                }
                if (c >= cols) {
                	r++;
                	c = 0;
                	break;
                }
            }
            if (c > 0) {
            	r++;
            	c = 0;
            }
        }
        return times;
    }
	
	public int wordsTyping1(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0)
            return 0;
        int n = sentence.length;
        int times = 0;
        int r = 0, c = 0;
        int i = 0;
        while (r < rows && c < cols) {
            if (c + sentence[i].length() <= cols) {
                c += sentence[i].length() + 1;
                if (c >= cols) {
                	r++;
                	c = 0;
                }
                i++;
                if (i ==n) {
                    times++;
                    i = 0;
                }
            } else {
                r++;
                c = 0;
            }
            System.out.printf("r=%d, c=%d, i=%d, times=%d\n", r, c, i, times);
        }
        return times;
    }
}
