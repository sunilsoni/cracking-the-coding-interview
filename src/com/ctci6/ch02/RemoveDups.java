package com.ctci6.ch02;

import java.util.HashSet;

import com.ctci6.utils.AssortedMethods;
import com.ctci6.utils.LinkedListNode;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * <p>FOLLOW UP</p>
 * How would you solve this problem if a temporary buffer is not allowed?
 * 
 * @author Sunil
 */
public class RemoveDups {

	public static void main(String[] args) {
		//LinkedListNode first = new LinkedListNode(0, null, null); 
		LinkedListNode first = AssortedMethods.randomLinkedList(1000, 0, 2);
		
		LinkedListNode head = first;
		LinkedListNode second = first;
		for (int i = 1; i < 8; i++) {
			second = new LinkedListNode(i % 2, null, null);
			first.setNext(second);
			second.setPrevious(first);
			first = second;
		}
		System.out.println(head.printForward());
		//deleteDups1(head);
		//deleteDups2(head);
		deleteDups3(head);
		System.out.println(head.printForward());
	}

	/*  We simply iterate through the linked list, adding each element to a hash table.When we discover a duplicate element,
	 *  we remove the element and continue iterating. We can do this all in one pass since we are using a linked list.
	 *  Time: o(n)
	 *  Space: o(n)
	 */
	public static void deleteDups1(LinkedListNode n) {
		HashSet<Integer> set = new HashSet<Integer>();
		LinkedListNode previous = null;
		while (n != null) {
			if (set.contains(n.data)) {
				previous.next = n.next;
			} else {
				set.add(n.data);
				previous = n;
			}
			n = n.next;
		}
	}
	
	/*  If we don't have a buffer, we can iterate with two pointers: current which iterates through the linked list,and runner which 
	 *  checks all subsequent nodes for duplicates.
	 *  Time: o(n^2)
	 *  Space: o(1)
	 */
	public static void deleteDups2(LinkedListNode head) {
		LinkedListNode current = head;
		while (current != null) {
			/* Remove all future nodes that have the same value */
			LinkedListNode runner = current;
			while (runner.next != null) { 
				if (runner.next.data == current.data) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}
			current = current.next;
		}
	}
	
	public static void deleteDups3(LinkedListNode head) {
		if (head == null) return;
		LinkedListNode previous = head;
		LinkedListNode current = previous.next;
		while (current != null) {
			// Look backwards for dups, and remove any that you see.
			LinkedListNode runner = head;
			while (runner != current) { 
				if (runner.data == current.data) {
					LinkedListNode tmp = current.next;
					previous.next = tmp;
					current = tmp;
					/* We know we can't have more than one dup preceding
					 * our element since it would have been removed 
					 * earlier. */
				    break;
				}
				runner = runner.next;
			}
			
			/* If runner == current, then we didn't find any duplicate elements in the previous for loop.  We then need to increment current.  
			 * If runner != current, then we must have hit the break condition, in which case we found a dup and current has already been incremented.
			 */
			if (runner == current) {
				previous = current;
		        current = current.next;
			}
		}
	}
	
}
