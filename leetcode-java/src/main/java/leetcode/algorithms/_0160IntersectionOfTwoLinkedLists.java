package leetcode.algorithms;

/*
 * @lc app=leetcode id=160 lang=java
 *
 * [160] Intersection of Two Linked Lists
 *
 * https://leetcode.com/problems/intersection-of-two-linked-lists/description/
 *
 * algorithms
 * Easy (32.80%)
 * Total Accepted:    291.1K
 * Total Submissions: 876.8K
 * Testcase Example:  '8\n[4,1,8,4,5]\n[5,0,1,8,4,5]\n2\n3'
 *
 * Write a program to find the node at which the intersection of two singly
 * linked lists begins.
 *
 * For example, the following two linked lists:
 *
 *
 * begin to intersect at node c1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA =
 * 2, skipB = 3
 * Output: Reference of the node with value = 8
 * Input Explanation: The intersected node's value is 8 (note that this must
 * not be 0 if the two lists intersect). From the head of A, it reads as
 * [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2
 * nodes before the intersected node in A; There are 3 nodes before the
 * intersected node in B.
 *
 *
 *
 * Example 2:
 *
 *
 *
 * Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3,
 * skipB = 1
 * Output: Reference of the node with value = 2
 * Input Explanation: The intersected node's value is 2 (note that this must
 * not be 0 if the two lists intersect). From the head of A, it reads as
 * [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes
 * before the intersected node in A; There are 1 node before the intersected
 * node in B.
 *
 *
 *
 *
 * Example 3:
 *
 *
 *
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB =
 * 2
 * Output: null
 * Input Explanation: From the head of A, it reads as [2,6,4]. From the head of
 * B, it reads as [1,5]. Since the two lists do not intersect, intersectVal
 * must be 0, while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 *
 *
 *
 *
 * Notes:
 *
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function
 * returns.
 * You may assume there are no cycles anywhere in the entire linked
 * structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 *
 *
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 * 思路：先获取两个链表长度，哪个长些，则指针先移动，
 * 移动到两个指针在同一起跑线，然后同时遍历链表
 *
 * @author zhanglinwei02
 * @date 2019-04-18
 */
public class _0160IntersectionOfTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = len(headA);
        int lenB = len(headB);
        int diff = Math.abs(lenA - lenB);
        ListNode pA = headA;
        ListNode pB = headB;
        // 移动到两指针在同一起跑线
        if (lenA > lenB) {
            int i = 0;
            while (i < diff && pA != null) {
                pA = pA.next;
                i ++;
            }
        } else {
            int i = 0;
            while (i < diff && pB != null) {
                pB = pB.next;
                i ++;
            }
        }
        // 同时遍历两链表，判断有没有节点是同一个节点
        while (pA != null && pB != null) {
            if (pA == pB) {
                return pA;
            }
            pA = pA.next;
            pB = pB.next;
        }
        return null;
    }

    private int len(ListNode head) {
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len ++;
            p = p.next;
        }
        return len;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; next = null; }
    }


    @Test
    public void test() {
        ListNode p1 = new ListNode(4);
        ListNode p2 = new ListNode(1);
        ListNode p3 = new ListNode(8);
        ListNode p4 = new ListNode(4);
        ListNode p5 = new ListNode(5);
        p1.next = p2; p2.next = p3; p3.next = p4; p4.next = p5;

        ListNode q1 = new ListNode(5);
        ListNode q2 = new ListNode(0);
        ListNode q3 = new ListNode(1);
        q1.next = q2; q2.next = q3; q3.next = p3;

        Assert.assertTrue(getIntersectionNode(p1, q1) == p3);
    }
}
