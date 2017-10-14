package com.ctci6.ch01;

import com.ctci6.utils.AssortedMethods;

/**
 * Write a method to replace all spaces in a string with'%20'. You may assume that
 * the string has sufficient space at the end of the string to hold the additional
 * characters, and that you are given the "true" length of the string. 
 * (Note: if implementing in Java, please use a character array so that you can perform this operation in place.)
 * EXAMPLE
 * Input: "Mr John Smith  "
 * Output: "Mr%20Dohn%20Smith"
 * @author Sunil
 *
 */
public class URLify {

	// Assume string has sufficient free space at the end
		public static void replaceSpaces(char[] str, int trueLength) {
			int spaceCount = 0, index, i = 0;
			for (i = 0; i < trueLength; i++) {
				if (str[i] == ' ') {
					spaceCount++;
				}
			}
			index = trueLength + spaceCount * 2;
			if (trueLength < str.length) str[trueLength] = '\0';
			for (i = trueLength - 1; i >= 0; i--) {
				if (str[i] == ' ') {
					str[index - 1] = '0';
					str[index - 2] = '2';
					str[index - 3] = '%';
					index = index - 3;
				} else {
					str[index - 1] = str[i];
					index--;
				}
			}
		}
		
		public static int findLastCharacter(char[] str) {
			for (int i = str.length - 1; i >= 0; i--) {
				if (str[i] != ' ') {
					return i;
				}
			}
			return -1;
		}
		
		public static void main(String[] args) {
			String str = "Mr John Smith    ";
			char[] arr = str.toCharArray();
			int trueLength = findLastCharacter(arr) + 1;
			replaceSpaces(arr, trueLength);	
			System.out.println("\"" + AssortedMethods.charArrayToString(arr) + "\"");
		}

}
