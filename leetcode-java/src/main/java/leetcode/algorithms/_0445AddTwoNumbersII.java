package leetcode.algorithms;

/*
 * @lc app=leetcode id=445 lang=java
 *
 * [445] Add Two Numbers II
 *
 * https://leetcode.com/problems/add-two-numbers-ii/description/
 *
 * algorithms
 * Medium (49.54%)
 * Total Accepted:    84.6K
 * Total Submissions: 170.7K
 * Testcase Example:  '[7,2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The most significant digit comes first and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Follow up:
 * What if you cannot modify the input lists? In other words, reversing the
 * lists is not allowed.
 *
 *
 *
 * Example:
 *
 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 8 -> 0 -> 7
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

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * 将两个链表节点遍历分别读到List中，然后从尾开始相加，注意进位
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public class _0445AddTwoNumbersII {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = new ArrayList();
        while (l1 != null) {
            list1.add(l1.val);
            l1 = l1.next;
        }

        List<Integer> list2 = new ArrayList();
        while (l2 != null) {
            list2.add(l2.val);
            l2 = l2.next;
        }

        List<Integer> result = new ArrayList<>();
        // 已读取的节点
        int readNodes = 0;
        // 是否产生进位
        boolean flag = false;
        while (readNodes < list1.size() && readNodes < list2.size()) {
            int tmp = list1.get(list1.size() - 1 - readNodes) + list2.get(list2.size() - 1 - readNodes);
            tmp += flag? 1: 0;
            flag = tmp > 9? true: false;
            result.add(tmp > 9? tmp - 10: tmp);
            readNodes++;
        }
        if (readNodes <= list1.size() - 1) {
            for (int i = list1.size() - 1 - readNodes; i >= 0; i--) {
                // 这里还可能存在进位 [9, 9] [1]
                int tmp = flag? list1.get(i) + 1: list1.get(i);
                flag = tmp > 9? true: false;
                result.add(tmp > 9? tmp - 10: tmp);
            }
        }
        if (readNodes <= list2.size() - 1) {
            for (int i = list2.size() - 1 - readNodes; i >= 0; i--) {
                int tmp = flag? list2.get(i) + 1: list2.get(i);
                flag = tmp > 9? true: false;
                result.add(tmp > 9? tmp - 10: tmp);
            }
        }
        // 可能两个链表一起读完，但是会有进位 比如[5], [5]
        if (flag) {
            result.add(1);
        }

        ListNode head = new ListNode(0);
        ListNode p = head;
        for (int i = result.size() - 1; i >= 0; i--) {
            ListNode node = new ListNode(result.get(i));
            p.next = node;
            p = node;
        }
        return head.next;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    @Test
    public void test() {
        ListNode l1 = new ListNode(7);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);
        ListNode l4 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;

        ListNode p1 = new ListNode(5);
        ListNode p2 = new ListNode(6);
        ListNode p3 = new ListNode(4);
        p1.next = p2;
        p2.next = p3;

        ListNode listNode = addTwoNumbers(l1, p1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }

    }

    @Test
    public void test2() {
        ListNode l1 = new ListNode(0);

        ListNode p1 = new ListNode(7);
        ListNode p2 = new ListNode(3);
        p1.next = p2;

        ListNode listNode = addTwoNumbers(l1, p1);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
