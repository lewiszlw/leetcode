package leetcode.algorithms;

/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 *
 * https://leetcode.com/problems/merge-k-sorted-lists/description/
 *
 * algorithms
 * Hard (33.47%)
 * Likes:    2510
 * Dislikes: 163
 * Total Accepted:    397.2K
 * Total Submissions: 1.1M
 * Testcase Example:  '[[1,4,5],[1,3,4],[2,6]]'
 *
 * Merge k sorted linked lists and return it as one sorted list. Analyze and
 * describe its complexity.
 *
 * Example:
 *
 *
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
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

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-14
 */
public class _0023MergeKSortedLists {

    /**
     * 解法一：
     * 每次比较lists中每个链表头结点，比较出最小后，将所在的链表头结点向后移动一位，
     * 直到所有链表都为空。
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode head = new ListNode(0);
        ListNode p = head;
        while (true) {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++) {
                ListNode node = lists[i];
                if (node != null && node.val < min) {
                    min = node.val;
                    minIndex = i;
                }
            }
            if (minIndex == -1) {
                // 全部node已为null
                break;
            }

            // 接到新链表上
            p.next = lists[minIndex];
            p = p.next;

            // 该链表向后移一位
            lists[minIndex] = lists[minIndex].next;
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
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(4);
        ListNode p3 = new ListNode(5);
        p1.next = p2; p2.next = p3;

        ListNode q1 = new ListNode(1);
        ListNode q2 = new ListNode(3);
        ListNode q3 = new ListNode(4);
        q1.next = q2; q2.next = q3;

        ListNode k1 = new ListNode(2);
        ListNode k2 = new ListNode(6);
        k1.next = k2;

        ListNode[] lists = new ListNode[] {p1, q1, k1};

        ListNode p = mergeKLists(lists);
        Assert.assertTrue(p.val == 1);
        p = p.next;
        Assert.assertTrue(p.val == 1);
        p = p.next;
        Assert.assertTrue(p.val == 2);
        p = p.next;
        Assert.assertTrue(p.val == 3);
        p = p.next;
        Assert.assertTrue(p.val == 4);
        p = p.next;
        Assert.assertTrue(p.val == 4);
        p = p.next;
        Assert.assertTrue(p.val == 5);
        p = p.next;
        Assert.assertTrue(p.val == 6);
    }
}
