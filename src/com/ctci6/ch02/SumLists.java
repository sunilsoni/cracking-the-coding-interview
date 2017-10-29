package com.ctci6.ch02;

import com.ctci6.utils.LinkedListNode;

/**
 * You have two numbers represented by a linked list, where each node contains a
 * single digit. The digits are stored in reverse order, such that the 1's digit
 * is at the head of the list. Write a function that adds the two numbers and
 * returns the sum as a linked list.
 * 
 * <p>
 * EXAMPLE:
 * </p>
 * <blockquote>
 * 
 * <pre>
 * Input: (7-> 1 -> 6) + (5 -> 9 -> 2) .That is,617 + 295.
 * Output: 2 - > 1 - > 9.That is, 912.
 * </pre>
 * 
 * </blockquote>
 * 
 * <p>
 * FOLLOW UP:
 * </p>
 * <blockquote>
 * 
 * <pre>
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
 * Output: 9 - > 1 - > 2.That is, 912.
 * </blockquote>
 * </pre>
 * 
 * @author Sunil
 */
public class SumLists {

	public static void main(String[] args) {
		LinkedListNode lA1 = new LinkedListNode(9, null, null);
		System.out.println("lA1  :  " + lA1.printForward());
		LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
		LinkedListNode lA3 = new LinkedListNode(9, null, lA2);

		LinkedListNode lB1 = new LinkedListNode(1, null, null);
		LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
		LinkedListNode lB3 = new LinkedListNode(0, null, lB2);

		
		System.out.println("lA1  :  " + lA1.printForward());
		System.out.println("lB1  :  " + lB1.printForward());
		
		System.out.println("lA2  :  " + lA2.printForward());
		System.out.println("lB2  :  " + lB2.printForward());
		
		System.out.println("lA3  :  " + lA3.printForward());
		System.out.println("lB3  :  " + lB3.printForward());
		System.out.println("=============================");
		
		//LinkedListNode list3 = addLists(lA1, lB1, 0);
		LinkedListNode list3 = addLists(lA1, lB1);

		System.out.println("lA1  :  " + lA1.printForward());
		System.out.println("lB1  :+ " + lB1.printForward());
		System.out.println("list3:= " + list3.printForward());

		int l1 = linkedListToInt(lA1);
		int l2 = linkedListToInt(lB1);
		int l3 = linkedListToInt(list3);

		System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
		System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));
	}

	/**
	 * <p>
	 * Let's walk through this for the below linked list:
	 * </p>
	 * <blockquote>
	 * 
	 * <pre>
	 *   7 -> 1 -> 6
	 * + 5 -> 9 -> 2
	 * </pre>
	 * 
	 * </blockquote>
	 * <p>
	 * We do the following:
	 * </p>
	 * <p>
	 * 1. We add 7 and 5 first, getting a result of 12. 2 becomes the first node in
	 * our linked list, and we "carry" the 1 to the next sum.
	 * </p>
	 * List: 2 -> ?
	 * <p>
	 * 2. We then add 1 and 9, as well as the "carry;' getting a result of 11 . 1
	 * becomes the second element of our linked list, and we carry the 1 to the next
	 * sum.
	 * </p>
	 * List: 2 -> 1 -> ?
	 * <p>
	 * 3. Finally, we add 6, 2 and our "carry;' to get 9. This becomes the final
	 * element of our linked list.
	 * </p>
	 * List: 2 - > 1 - > 9.
	 */
	private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2, int carry) {
		System.out.println("## Start addLists ##");
		if (l1 == null && l2 == null && carry == 0) {
			return null;
		}

		LinkedListNode result = new LinkedListNode();
		int value = carry;
		if (l1 != null) {
			value += l1.data;
		}
		if (l2 != null) {
			value += l2.data;
		}
		result.data = value % 10;/* Second digit of number */
		//System.out.println("result-->"+result.printForward());
		/* Recurse */
		if (l1 != null || l2 != null) {
			LinkedListNode more = addLists(l1 == null ? null : l1.next, l2 == null ? null : l2.next,value >= 10 ? 1 : 0);
			//System.out.println("more-->"+more.printForward());
			result.setNext(more);
		}
		
		System.out.println("## End addLists ## "+result.printForward());
		return result;
	}

	public static int linkedListToInt(LinkedListNode node) {
		int value = 0;
		if (node.next != null) {
			value = 10 * linkedListToInt(node.next);
		}
		return value + node.data;
	}
	
	//Follow Up
	private static int length(LinkedListNode l) {
		if (l == null) {
			return 0;
		} else {
			return 1 + length(l.next);
		}
	}
	
	private static PartialSum addListsHelper1(LinkedListNode l1, LinkedListNode l2) {
		if (l1 == null && l2 == null) {
			PartialSum sum = new PartialSum();
			return sum;
		}
		
		/* Add smaller digits recursively */
		PartialSum sum = addListsHelper1(l1.next, l2.next);
		
		/* Add carry to current data */
		int val = sum.carry + l1.data + l2.data;
		
		/* Insert sum of current digits */
		LinkedListNode full_result = insertBefore(sum.sum, val % 10);
		
		/* Return sum so far, and the carry value */
		sum.sum = full_result;
		sum.carry = val / 10;
		return sum;
	}
	

	private static LinkedListNode addLists(LinkedListNode l1, LinkedListNode l2) {
		int len1 = length(l1);
		int len2 = length(l2);
		/* Pad the shorter list with zeros - see note (1) */
		if (len1 < len2) {
			l1 = padList(l1, len2 - len1);
		} else {
			l2 = padList(l2, len1 - len2);
		}
		
		/* Add lists */
		PartialSum sum = addListsHelper1(l1, l2);
		
		/* I f there was a carry value left over, insert this at the front of the list.
		* Otherwise, just return the linked list. */
		if (sum.carry == 0) {
			return sum.sum;
		} else {
			LinkedListNode result = insertBefore(sum.sum, sum.carry);
			return result;
		}
	}	
	
	/* Pad the list with zeros */
	private static LinkedListNode padList(LinkedListNode l, int padding) {
		LinkedListNode head = l;
		for (int i = 0; i < padding; i++) {
			head = insertBefore(head, 0);
		}
		return head;
	}
	
	/* Helper function to insert node in the front of a linked list */
	private static LinkedListNode insertBefore(LinkedListNode list, int data) {
		LinkedListNode node = new LinkedListNode(data);
		if (list != null) {
			node.next = list;
		}
		return node;
	}
	
	public static int linkedListToInt1(LinkedListNode node) {
		int value = 0;
		while (node != null) {
			value = value * 10 + node.data;
			node = node.next;
		}
		return value;
	}	

}

class PartialSum {
	public LinkedListNode sum = null;
	public int carry = 0;
}
