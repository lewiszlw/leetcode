package leetcode.algorithms;

/*
 * @lc app=leetcode id=83 lang=java
 *
 * [83] Remove Duplicates from Sorted List
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 *
 * algorithms
 * Easy (42.10%)
 * Total Accepted:    315.6K
 * Total Submissions: 747.1K
 * Testcase Example:  '[1,1,2]'
 *
 * Given a sorted linked list, delete all duplicates such that each element
 * appear only once.
 *
 * Example 1:
 *
 *
 * Input: 1->1->2
 * Output: 1->2
 *
 *
 * Example 2:
 *
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
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
 * @date 2019-04-15
 */
public class _0083RemoveDuplicatesFromSortedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode p = head.next;
        // Duplicates value
        int tmp = head.val;
        while (p != null) {
            if (p.val == tmp) {
                // 删除节点
                ListNode post = p.next;
                pre.next = post;
                p = post;
            } else {
                // 更新值
                tmp = p.val;
                pre = p;
                p = p.next;
            }
        }
        return head;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    @Test
    public void test() {
        ListNode p1 = new ListNode(1);
        ListNode p2 = new ListNode(1);
        ListNode p3 = new ListNode(2);
        p1.next = p2;
        p2.next = p3;
        ListNode head1 = deleteDuplicates(p1);
        Assert.assertEquals(head1.val, 1);
        Assert.assertEquals(head1.next.val, 2);
    }
}
