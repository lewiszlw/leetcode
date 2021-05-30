package leetcode.algorithms;

//Given the head of a singly linked list, reverse the list, and return the rever
//sed list.
//
//
// Example 1:
//
//
//Input: head = [1,2,3,4,5]
//Output: [5,4,3,2,1]
//
//
// Example 2:
//
//
//Input: head = [1,2]
//Output: [2,1]
//
//
// Example 3:
//
//
//Input: head = []
//Output: []
//
//
//
// Constraints:
//
//
// The number of nodes in the list is the range [0, 5000].
// -5000 <= Node.val <= 5000
//
//
//
// Follow up: A linked list can be reversed either iteratively or recursively. C
//ould you implement both?
// Related Topics Linked List
// 👍 7135 👎 134

import org.junit.Test;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class _0206ReverseLinkedList {

    /**
     * 解法：三个指针
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = head;
        ListNode pNext = head.next;
        ListNode pNextNext = head.next.next;
        while (pNext != null) {
            pNext.next = p;
            p = pNext;
            pNext = pNextNext;
            pNextNext = pNextNext == null ? null : pNextNext.next;
        }
        head.next = null;
        return p;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    @Test
    public void test() {
        ListNode p5 = new ListNode(5);
        ListNode p4 = new ListNode(4, p5);
        ListNode p3 = new ListNode(3, p4);
        ListNode p2 = new ListNode(2, p3);
        ListNode p1 = new ListNode(1, p2);
        ListNode p = reverseList(p1);
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}
