package leetcode.algorithms;

//Given the head of a linked list, return the list after sorting it in ascending
// order.
//
// Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.
//e. constant space)?
//
//
// Example 1:
//
//
//Input: head = [4,2,1,3]
//Output: [1,2,3,4]
//
//
// Example 2:
//
//
//Input: head = [-1,5,3,4,0]
//Output: [-1,0,3,4,5]
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
// The number of nodes in the list is in the range [0, 5 * 104].
// -105 <= Node.val <= 105
//
// Related Topics Linked List Sort
// 👍 4158 👎 175

import javafx.util.Pair;
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

public class _0148SortList {

    /**
     * 解法：归并排序
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 计算链表总长度找到中间节点 （也可以用快慢指针，慢指针走一步，快指针走两步）
        int len = 0;
        ListNode p = head;
        while (p != null) {
            len ++;
            p = p.next;
        }
        ListNode midNode = findMidNode(head, len);

        // 1.从中间切割链表
        ListNode head2 = midNode.next;
        midNode.next = null;

        // 2.左右链表排序
        ListNode leftHead = sortList(head);
        ListNode rightHead = sortList(head2);

        // 3.合并左右排序链表
        ListNode left = leftHead;
        ListNode right = rightHead;
        ListNode fakeHead = new ListNode(-1);
        ListNode k = fakeHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                k.next = left;
                left = left.next;
            } else {
                k.next = right;
                right = right.next;
            }
            k = k.next;
        }
        // 拼接多出的部分
        k.next = left == null ? right : left;

        return fakeHead.next;
    }

    private ListNode findMidNode(final ListNode head, int len) {
        int steps = (len + 1) / 2;
        ListNode p = head;
        int step = 1;
        while (p != null) {
            if (step == steps) {
                return p;
            }
            p = p.next;
            step ++;
        }
        return null;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {
        }
        ListNode(int val) {
            this.val = val;
        }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Test
    public void test() {
        ListNode p1 = new ListNode(4);
        ListNode p2 = new ListNode(2);
        ListNode p3 = new ListNode(1);
        ListNode p4 = new ListNode(3);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;

        ListNode head = sortList(p1);
        ListNode p = head;
        while (p != null) {
            System.out.println(p.val);
            p = p.next;
        }
    }
}
