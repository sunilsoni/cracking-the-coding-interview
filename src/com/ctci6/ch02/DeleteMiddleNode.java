package com.ctci6.ch02;

import com.ctci6.utils.AssortedMethods;
import com.ctci6.utils.LinkedListNode;

/**
 * Implement an algorithm to delete a node in the middle (i.e., any node but the first and last node, not necessarily the exact middle) 
 * of a singly linked list, given only access to that node.
 * <p>EXAMPLE:</p>
 * <blockquote><pre>
 * Input: the node c from the linked list a - >b- >c - >d - >e- >f
 * Result: nothing is returned, but the new linked list looks like a->b->d->e->f
 * </blockquote></pre>

 * @author Sunil
 */
public class DeleteMiddleNode {
	
	public static void main(String[] args) {
		LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
		System.out.println(head.printForward());
		deleteNode(head.next.next.next.next); // delete node 4
		System.out.println(head.printForward());
	}
	
	/*
	 * In this problem, you are not given access to the head of the linked list. You only have access to that node.
	 * The solution is simply to copy the data from the next node over to the current node, and then to delete the next node.
	 * Note that this problem cannot be solved if the node to be deleted is the last node in the linked list. 
	 */
	public static boolean deleteNode(LinkedListNode n) {
		if (n == null || n.next == null) {
			return false; // Failure
		} 
		LinkedListNode next = n.next;
		n.data = next.data; 
		n.next = next.next; 
		return true;
	}
	
}
