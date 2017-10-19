package com.ctci6.ch01;

/**
 * Implement a method to perform basic string compression using the counts
 * of repeated characters. 
 * <blockquote><pre> For example, 
 * The string aabcccccaaa would become a2blc5a3. </pre></blockquote>
 * If the "compressed"string would not become smaller than the original string, your method should return
 * the original string. You can assume the string has only uppercase and lowercase letters (a - z).
 * 
 * @author Sunil
 */
public class StringCompression {

	public static void main(String[] args) {
		String str = "b";
		System.out.println(compress1(str));
		
		str = "aa";
		System.out.println(compress1(str));
	}
	
	public static String compress1(String str) {
		
		int size = str==null ? 0 : str.length();
		if(size==1) {
			return str;
		}
		
		StringBuilder compressed = new StringBuilder();
		int countConsecutive = 0;
		boolean isCompressed=false;
		System.out.println("size--->"+size);
		for (int i = 0; i < size; i++) {
			countConsecutive++;
			System.out.println("i--->"+i);
			/* If next character is different than current, append this char to result.*/
			if (i + 1 >= size || str.charAt(i) != str.charAt(i + 1)) {
				compressed.append(str.charAt(i));
				compressed.append(countConsecutive);
				countConsecutive = 0;
				isCompressed=true;
			}
		}
		if(isCompressed) {
			return compressed.toString();
		}
		
		return  str;
	}
	

}
