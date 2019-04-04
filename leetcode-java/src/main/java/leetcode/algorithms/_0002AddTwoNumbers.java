package leetcode.algorithms;

/*
 * @lc app=leetcode id=2 lang=java
 *
 * [2] Add Two Numbers
 *
 * https://leetcode.com/problems/add-two-numbers/description/
 *
 * algorithms
 * Medium (30.80%)
 * Total Accepted:    815.1K
 * Total Submissions: 2.6M
 * Testcase Example:  '[2,4,3]\n[5,6,4]'
 *
 * You are given two non-empty linked lists representing two non-negative
 * integers. The digits are stored in reverse order and each of their nodes
 * contain a single digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Example:
 *
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
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
 * 两个指针同时遍历l1, l2链表，需要注意进位，当前位的值保存到链表中，
 * 结果直接返回l1/l2即可，无需重新建立链表
 *
 * 注意情况：
 * 1.遍历完之后还有进位情况，需要新建节点到链表尾部
 * 2.同时遍历两个链表时，退出循环时可能p1 p2都为null，此时可能会有进位
 *
 * @author zhanglinwei02
 * @date 2019-04-04
 */
public class _0002AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;

        // 记录p1的前节点, 防止遍历后p1 p2为null
        ListNode preP1 = new ListNode(0);
        preP1.next = p1;
        ListNode preP2 = new ListNode(0);
        preP2.next = p2;

        // 进位
        boolean flag = false;
        while (p1 != null && p2 != null) {
            int tmp = p1.val + p2.val;
            // 有进位，则加1
            tmp += flag? 1: 0;
            // 判断是否有进位
            flag = tmp > 9? true: false;
            // 保存当前位到两个链表
            p1.val = tmp > 9? tmp - 10: tmp;
            p2.val = tmp > 9? tmp - 10: tmp;

            preP1 = p1;
            preP2 = p2;
            p1 = p1.next;
            p2 = p2.next;
        }
        if (p1 != null) {
            while (p1 != null) {
                int tmp = p1.val;
                tmp += flag? 1: 0;
                flag = tmp > 9? true: false;
                p1.val = tmp > 9? tmp - 10: tmp;

                preP1 = p1;
                p1 = p1.next;
            }
            if (flag) {
                // 还有进位则新建节点
                preP1.next = new ListNode(1);
            }
            return l1;
        }
        if (p2 != null){
            while (p2 != null) {
                int tmp = p2.val;
                tmp += flag? 1: 0;
                flag = tmp > 9? true: false;
                p2.val = tmp > 9? tmp - 10: tmp;

                preP2 = p2;
                p2 = p2.next;
            }
            if (flag) {
                preP2.next = new ListNode(1);
            }
            return l2;
        }

        // p1 p2 均为null
        if (flag) {
            preP1.next = new ListNode(1);
        }
        return l1;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    @Test
    public void test() {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode p1 = new ListNode(5);
        ListNode p2 = new ListNode(6);
        ListNode p3 = new ListNode(4);
        p1.next = p2;
        p2.next = p3;

        ListNode node = addTwoNumbers(l1, p1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    @Test
    public void test1() {
        ListNode l1 = new ListNode(5);

        ListNode p1 = new ListNode(5);

        ListNode node = addTwoNumbers(l1, p1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    @Test
    public void test2() {
        ListNode l1 = new ListNode(1);

        ListNode p1 = new ListNode(9);
        ListNode p2 = new ListNode(9);
        p1.next = p2;

        ListNode node = addTwoNumbers(l1, p1);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }
}
