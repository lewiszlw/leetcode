package leetcode.algorithms;

/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
 *
 * https://leetcode.com/problems/merge-two-sorted-lists/description/
 *
 * algorithms
 * Easy (46.32%)
 * Total Accepted:    540.2K
 * Total Submissions: 1.2M
 * Testcase Example:  '[1,2,4]\n[1,3,4]'
 *
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 *
 *
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

import org.junit.Test;

/**
 * Desc:
 * 首先创建头指针p指向一个虚拟头结点head，然后遍历两个链表，如果l1指针节点值小于l2指针节点值，
 * 则将l1节点放到p指针后面，p和l1指针后移一位，否则将l2节点放到p指针后面，p和l2指针后移一位，
 * 最后将未遍历完的链表接到p指针后面即可
 *
 * @author zhanglinwei02
 * @date 2019-04-02
 */
public class _0021MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;

        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(3);
        ListNode p3 = new ListNode(4);
        p1.next = p2;
        p2.next = p3;

        ListNode node = mergeTwoLists(l1, p1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

}
