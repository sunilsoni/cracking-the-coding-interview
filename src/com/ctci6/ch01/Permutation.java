package com.ctci6.ch01;

/**
 * Given two strings, write a method to decide if one is a permutation of the other.
 * 
 * @author Sunil
 */
public class Permutation {

	public static void main(String[] args) {
		String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
		for (String[] pair : pairs) {
			String word1 = pair[0];
			String word2 = pair[1];
			boolean anagram1 = permutation1(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram1);
			System.out.println("----------------------------");
			boolean anagram2 = permutation2(word1, word2);
			System.out.println(word1 + ", " + word2 + ": " + anagram2);
			System.out.println("============================");
		}
	}

	public static String sort(String s) {
		char[] content = s.toCharArray();
		java.util.Arrays.sort(content);
		return new String(content);
	}
	
	public static boolean permutation1(String s, String t) {
		return sort(s).equals(sort(t));
	}
	
	public static boolean permutation2(String s, String t) {
		if (s.length() != t.length()) return false; // Permutations must be same length
		
		int[] letters = new int[128]; // Assumption: ASCII
		for (int i = 0; i < s.length(); i++) {
			letters[s.charAt(i)]++;
		}
		  
		for (int i = 0; i < t.length(); i++) {
			letters[t.charAt(i)]--;
		    if (letters[t.charAt(i)] < 0) {
		    	return false;
		    }
		}
		  
		return true; // letters array has no negative values, and therefore no positive values either
	}
}
