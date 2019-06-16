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

import java.util.ArrayList;
import java.util.List;

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


    /**
     * 解法二：分治法
     * 两两合并，然后合并结果继续两两合并，直至最后合并成一个链表
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode[] newLists = new ListNode[(lists.length + 1) / 2];
        int index = 0;

        int i = 0;
        while (i < lists.length) {
            ListNode root1 = lists[i ++];
            ListNode root2 = null;
            // 此时i可能超过length
            if (i < lists.length) {
                root2 = lists[i ++];
            }
            newLists[index ++] = mergeTwoSortedLists(root1, root2);
        }
        return mergeKLists2(newLists);
    }

    /**
     * 合并两个有序链表
     */
    private ListNode mergeTwoSortedLists(ListNode root1, ListNode root2) {
        if (root2 == null) {
            return root1;
        }
        ListNode p1 = root1;
        ListNode p2 = root2;
        ListNode head = new ListNode(0);
        ListNode p = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if (p1 != null) {
            while (p1 != null) {
                p.next = p1;
                p = p.next;
                p1 = p1.next;
            }
        }
        if (p2 != null) {
            while (p2 != null) {
                p.next = p2;
                p = p.next;
                p2 = p2.next;
            }
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

    @Test
    public void test2() {
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

        ListNode p = mergeKLists2(lists);
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
