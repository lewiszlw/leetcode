package leetcode.algorithms;

//Given a linked list, return the node where the cycle begins. If there is no cy
//cle, return null.
//
// There is a cycle in a linked list if there is some node in the list that can
//be reached again by continuously following the next pointer. Internally, pos is
//used to denote the index of the node that tail's next pointer is connected to. N
//ote that pos is not passed as a parameter.
//
// Notice that you should not modify the linked list.
//
//
// Example 1:
//
//
//Input: head = [3,2,0,-4], pos = 1
//Output: tail connects to node index 1
//Explanation: There is a cycle in the linked list, where tail connects to the s
//econd node.
//
//
// Example 2:
//
//
//Input: head = [1,2], pos = 0
//Output: tail connects to node index 0
//Explanation: There is a cycle in the linked list, where tail connects to the f
//irst node.
//
//
// Example 3:
//
//
//Input: head = [1], pos = -1
//Output: no cycle
//Explanation: There is no cycle in the linked list.
//
//
//
// Constraints:
//
//
// The number of the nodes in the list is in the range [0, 104].
// -105 <= Node.val <= 105
// pos is -1 or a valid index in the linked-list.
//
//
//
// Follow up: Can you solve it using O(1) (i.e. constant) memory?
// Related Topics Linked List Two Pointers
// 👍 4269 👎 315

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class _0142LinkedListCycleII {

    /**
     * 解法1：哈希表/集合
     * 利用哈希表/集合记录每次遍历过的节点
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode p = head;
        Set<ListNode> visited = new HashSet<>();
        while (p != null) {
            if (visited.contains(p)) {
                return p;
            } else {
                visited.add(p);
            }
            p = p.next;
        }
        return null;
    }


    /**
     * 解法2：快慢指针
     * 两个指针 fast 与 slow,它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，
     * 而 fast 指针向后移动两个位置。如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
     *
     * 快慢指针分别走了 f、s 步，则
     *     f = 2s
     * 链表头部到链表入口有 a 个节点（不计链表入口节点）， 链表环有 b 个节点，
     *     f = s + nb  (fast 比 slow多走了 n 个环的长度)
     * 综上得
     *     s = nb, f = 2nb (即fast和slow 指针分别走了 2n，n 个环的周长)
     * 若要走到环入口，需要走步数
     *     k = a + nb
     * 即需要 slow 指针在跟 fast 指针相遇后再走 a 步
     * 此时可以用另一个指针 ptr 与 slow 同时走，他们必定会在 a 步之后在环入口处相遇
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        // 先各走一次
        ListNode fast = head.next.next, slow = head.next;
        // 快慢指针重合点
        while (fast != slow) {
            if (fast == null || fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        // ptr和slow重合点
        ListNode ptr = head;
        while (ptr != slow) {
            ptr = ptr.next;
            slow = slow.next;
        }
        return ptr;
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }



    @Test
    public void test() {
        ListNode p0 = new ListNode(3);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(0);
        ListNode p3 = new ListNode(4);
        p0.next = p1; p1.next = p2; p2.next = p3;
        p3.next = p1;
        Assert.assertEquals(2, detectCycle(p0).val);
    }

    @Test
    public void test2() {
        ListNode p0 = new ListNode(3);
        ListNode p1 = new ListNode(2);
        ListNode p2 = new ListNode(0);
        ListNode p3 = new ListNode(4);
        p0.next = p1; p1.next = p2; p2.next = p3;
        p3.next = p1;
        Assert.assertEquals(2, detectCycle2(p0).val);
    }
}
