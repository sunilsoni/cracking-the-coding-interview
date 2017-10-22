package com.ctci6.ch02;

import com.ctci6.utils.AssortedMethods;
import com.ctci6.utils.LinkedListNode;


/**
 * Implement an algorithm to find the kth to last element of a singly linked list.
 * 
 * @author Sunil
 */
public class ReturnKthToLast {

	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 4, 5, 6};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		System.out.println(head.printForward());
		
		for (int i = 0; i <= array.length + 1; i++) {
			printKthToLast(head, i);
		}
	}
	
	/*
	 * For this solution, we have defined k such that passing in 
	 * k = 1 would return the last element, 
	 * k = 2 would return to the second to last element, and so on.It is equally acceptable to define k such that
	 * k = 0 would return the last element.
	 */
	public static int printKthToLast(LinkedListNode head, int k) {
		if (head == null) {
			return 0;
		}
		int index = printKthToLast(head.next, k) + 1;
		if (index == k) {
			System.out.println(k + "th to last node is " + head.data);
		}
		return index;
	}
	
	
	//Approach C: Create a Wrapper Class.
	public static class Index {
		public int value = 0;
	}	
	
	public static LinkedListNode kthToLast(LinkedListNode head, int k) {
		Index idx = new Index();
		return kthToLast(head, k, idx);
	}
	
	public static LinkedListNode kthToLast(LinkedListNode head, int k, Index idx) {
		if (head == null) {
			return null;
		}
		LinkedListNode node = kthToLast(head.next, k, idx);
		idx.value = idx.value + 1;
		if (idx.value == k) {
			return head;
		} 
		return node;
	}	

}
