package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class AllienDictionary {
	public static void main(String[] qrgs) {
		AllienDictionary obj = new AllienDictionary();
		String[] words = {
		                  "wrt",
		                  "wrf",
		                  "er",
		                  "ett",
		                  "rftt"
		};
		words = new String[]{"z", "z"};
		System.out.println(obj.alienOrder(words));
		words = new String[]{"zy", "zx"};
		System.out.println(obj.alienOrder(words));
		words = new String[]{"zy", "zyx"};
		System.out.println(obj.alienOrder(words));
		words = new String[]{"zyx", "zy"};
		System.out.println(obj.alienOrder(words));

	}
	public String alienOrder(String[] words) {
        if (words == null || words.length == 0)
            return "";
        // deduplicate
        List<String> tmp = new ArrayList<String>();
        tmp.add(words[0]);
        for (int i = 1; i < words.length; i++) {
        	if (words[i].equals(words[i-1])) {
        		continue;
        	}
        	tmp.add(words[i]);
        }
        String[] uniqWords = new String[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
        	uniqWords[i] = tmp.get(i);
        }
        words = uniqWords;
        int n = words.length;
//        if (n == 1) {
//        	return words[0];
//        }
        // get all letters in the words[]
        Set<Character> alphabet = new HashSet<Character>();
        for (String w : words) {
        	for (char c : w.toCharArray()) {
        		alphabet.add(c);
        	}
        }
        // get dependency relation
       List<char[]> predList = new ArrayList<>(); 
        for (int i = 1; i < n; i++) {
            int len = Math.min(words[i - 1].length(), words[i].length());
            int j = 0;
            for (;j < len; j++) {
                if (words[i-1].charAt(j) != words[i].charAt(j)) {
                	char[] pair = new char[2];
                    pair[0] = words[i-1].charAt(j);
                    pair[1] = words[i].charAt(j);
                    predList.add(pair);
                    break;
                }
            }
            if (j == len && j < words[i - 1].length())
            	return "";
        }
        String orderedStr = "";
        if (predList.size() != 0) {
        	// toplogic sort
        	char[][] pred = new char[predList.size()][2];
        	int k = 0;
        	for (char[] pair : predList) {
        		pred[k++] = pair;
        	}
            orderedStr =  topsort(pred);
            if (orderedStr.equals("NOT VALID"))
            	return "";
        }
        
        // check if there is some letter not take part in toplogic sort
        for (char c : orderedStr.toCharArray()) {
        	alphabet.remove(c);
        }
        StringBuilder res = new StringBuilder(orderedStr);
        for (char c : alphabet) {
        	res.append(c);
        }
        return res.toString();
    }
    private String topsort(char[][] pred) {
        Node[] graph = readGraph(pred);
        Queue<Node> queue = new ArrayDeque<Node>();
        int validCharNum = 0;
        for (int i = 0; i < 256; i++) {
            if (graph[i].inorder == 0 && graph[i].neighs.isEmpty())
                continue;
            validCharNum++;
//            System.out.printf("lable=%c, inorder=%d, neigh.size=%d\n", graph[i].label, graph[i].inorder, graph[i].neighs.size());
            if (graph[i].inorder == 0)
                queue.offer(graph[i]);
        }
        int count = 0;
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            count++;
            Node curr = queue.poll();
            res.append(curr.label);
//            System.out.printf("res=%s, count=%d\n", res.toString(), count);
            for (Node w : curr.neighs) {
                if (--w.inorder == 0)
                    queue.offer(w);
            }
        }
        if (count == validCharNum)
            return res.toString();
        else
            return "NOT VALID";
    }
    private Node[] readGraph(char[][] pred) {
        Node[] graph = new Node[256];
        for (int i = 0; i < 256; i++) {
            graph[i] = new Node((char)i);
        }
        for (char[] p : pred) {
            graph[p[1]].inorder++;
            graph[p[0]].neighs.add(graph[p[1]]);
//            System.out.println(p[0] + "--->" + p[1]);
        }
        return graph;
    }
    private class Node {
        char label;
        int inorder;
        List<Node> neighs;
        public Node(char c) {
            label = c;
            inorder = 0;
            neighs = new ArrayList<Node>();
        }
    }
}
