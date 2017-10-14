package com.ctci6.ch01;


/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 * 
 * @author Sunil    
 *
 */
public class IsUniqueChars {

	public static boolean isUniqueChars(String str) {
		if (str.length() > 128) {
			return false;
		}
		boolean[] char_set = new boolean[128];
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i);
			if (char_set[val]) return false;
			char_set[val] = true;
		}
		return true;
	}
	
	/* Assumes only letters a through z. */
	public static boolean isUniqueChars1(String str) {
		if (str.length() > 26) { // Only 26 characters
			return false;
		}
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			System.out.println("val "+i+"-->"+val);
			if ((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
		}
		return true;   
	} 
	
	public static void main(String[] args) {
		String[] words = {"abcde", "hello", "apple", "kite", "padle"};
		for (String word : words) {
			//System.out.println(word + ": " + isUniqueChars(word));
			
			System.out.println(word + ":-> " + isUniqueChars1(word));
		}
		
		System.out.println("=========================");
		for (String word : words) {
			boolean wordA =  isUniqueChars(word);
			boolean wordB =  isUniqueChars1(word);
			if (wordA == wordB) {
				System.out.println(word + ": " + wordA);
			} else {
				System.out.println(word + ": " + wordA + " vs " + wordB);
			}
		}
	}

	//=========================================

}
