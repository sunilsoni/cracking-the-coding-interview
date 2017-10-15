package com.ctci6.ch01;


/**
 * Given a string, write a function to check if it is a permutation of a
 * palindrome. A palindrome is a word or phrase that is the same forwards and
 * backwards. A permutation is a rearrangement of letters. The palindrome does
 * not need to be limited to just dictionary words. 
 * <p>Details Example:</p> 
 * <blockquote><pre>
 * Input: Tact coa 
 * Output: True (permutation: “taco cat”, “atco cta”, etc . )
 * </blockquote></pre>
 * 
 * @URL https://www.hackerearth.com/problem/algorithm/palindrome-check-2-1/
 * @author Sunil
 */
public class PalindromePermutation {

	public static void main(String[] args) {
		String[] strings = { "Rats live on no evil star", "A man, a plan, a canal, panama", "Lleve", "Tacotac",
				"asda" };
		for (String s : strings) {
			boolean a = isPermutationOfPalindrome1(s);
			boolean b = isPermutationOfPalindrome2(s);
			boolean c = isPermutationOfPalindrome3(s);
			System.out.println(s);
			if (a == b && b == c) {
				System.out.println("Agree: " + a);
			} else {
				System.out.println("Disagree: " + a + ", " + b + ", " + c);
			}
			System.out.println();
		}

	}

	/*
	 * Map each character to a number. a -) 0J b - ) IJ C - ) 2J etc. This is case
	 * insensitive. Non-letter characters map to -1.
	 */
	public static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');

		int val = Character.getNumericValue(c);
		if (a <= val && val <= z) {
			return val - a;
		}
		return -1;
	}

	/* Count how many times each character appears. */
	public static int[] buildCharFrequencyTable(String phrase) {
		//System.out.println("z getNumericValue-->" + Character.getNumericValue('z'));
		//System.out.println("a getNumericValue-->" + Character.getNumericValue('a'));
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : phrase.toCharArray()) {
			int x = getCharNumber(c);
			//System.out.println(c + "-->" + x);
			if (x != -1) {
				table[x]++;
			}
		}
		return table;
	}

	/**
	 * This algorithm takes O(N) time, where N is the length of the string
	 */

	/* Check that no more than one character has an odd count. */
	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count : table) {
			if (count % 2 == 1) {
				if (foundOdd) {
					return false;
				}
				foundOdd = true;
			}
		}
		return true;
	}

	public static boolean isPermutationOfPalindrome1(String phrase) {
		int[] table = buildCharFrequencyTable(phrase);
		//System.out.println("table-->" + Arrays.toString(table));// [2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 2, 0, 2, 2, 0, 0,
																// 2, 2, 2, 0, 2, 0, 0, 0, 0]
		return checkMaxOneOdd(table);
	}

	/**
	 * This algorithm also takes O(N) time, where N is the length of the string
	 */
	public static boolean isPermutationOfPalindrome2(String phrase) {
		int countOdd = 0;
		int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
		for (char c : phrase.toCharArray()) {
			int x = getCharNumber(c);
			if (x != -1) {
				table[x]++;
				if (table[x] % 2 == 1) {
					countOdd++;
				} else {
					countOdd--;
				}
			}
		}
		return countOdd <= 1;
	}

	/**
	 * This algorithm also takes O(N) time, where N is the length of the string
	 */

	/* Toggle the ith bit in the integer. */
	public static int toggle(int bitVector, int index) {
		if (index < 0)
			return bitVector;

		int mask = 1 << index;
		if ((bitVector & mask) == 0) {
			bitVector |= mask;
		} else {
			bitVector &= ~mask;
		}
		return bitVector;
	}

	/*
	 * Create bit vector for string. For each letter with value i, toggle the ith
	 * bit.
	 */
	public static int createBitVector(String phrase) {
		int bitVector = 0;
		for (char c : phrase.toCharArray()) {
			int x = getCharNumber(c);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}

	/*
	 * Check that exactly one bit is set by subtracting one from the integer and
	 * ANDing it with the original integer.
	 */
	public static boolean checkExactlyOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}

	public static boolean isPermutationOfPalindrome3(String phrase) {
		int bitVector = createBitVector(phrase);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	}

}
