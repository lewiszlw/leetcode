package leetcode.algorithms;

/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
 *
 * https://leetcode.com/problems/same-tree/description/
 *
 * algorithms
 * Easy (49.61%)
 * Total Accepted:    365.2K
 * Total Submissions: 734K
 * Testcase Example:  '[1,2,3]\n[1,2,3]'
 *
 * Given two binary trees, write a function to check if they are the same or
 * not.
 *
 * Two binary trees are considered the same if they are structurally identical
 * and the nodes have the same value.
 *
 * Example 1:
 *
 *
 * Input:     1         1
 * ⁠         / \       / \
 * ⁠        2   3     2   3
 *
 * ⁠       [1,2,3],   [1,2,3]
 *
 * Output: true
 *
 *
 * Example 2:
 *
 *
 * Input:     1         1
 * ⁠         /           \
 * ⁠        2             2
 *
 * ⁠       [1,2],     [1,null,2]
 *
 * Output: false
 *
 *
 * Example 3:
 *
 *
 * Input:     1         1
 * ⁠         / \       / \
 * ⁠        2   1     1   2
 *
 * ⁠       [1,2,1],   [1,1,2]
 *
 * Output: false
 *
 *
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
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
 * @date 2019-04-16
 */
public class _0100SameTree {

    /**
     * 解法1: 对两个树使用相同遍历方式得到两个数组，然后比较两个数组
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        List<TreeNode> listP = new ArrayList<>();
        List<TreeNode> listQ = new ArrayList<>();
        preOrder(p, listP);
        preOrder(q, listQ);

        // size不同肯定不是相同二叉树
        if (listP.size() != listQ.size()) {
            return false;
        }

        // 遍历对比每个元素
        int i = 0;
        while (i < listP.size() && i < listQ.size()) {
            TreeNode nodeP = listP.get(i);
            TreeNode nodeQ = listQ.get(i);
            if (nodeP != null && nodeQ != null) {
                if (nodeP.val == nodeQ.val) {
                    i ++;
                    continue;
                } else {
                    return false;
                }
            } else {
                if (nodeP == null && nodeQ == null) {
                    i ++;
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 前序遍历
     */
    private void preOrder(TreeNode root, List<TreeNode> list) {
        list.add(root);
        if (root != null) {
            preOrder(root.left, list);
            preOrder(root.right, list);
        }
    }



    /**
     * 解法2：对两个树使用相同遍历方式，遍历的同时进行比较
     */
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        return preOrderAndCompare(p, q);
    }

    private boolean preOrderAndCompare(TreeNode p1, TreeNode p2) {
        // 一个或两个节点为null，即叶子节点的left/right节点
        if (p1 == null || p2 == null) {
            return p1 == p2;
        }

        // 两个节点不为null
        if (p1.val == p2.val) {
            return preOrderAndCompare(p1.left, p2.left) && preOrderAndCompare(p1.right, p2.right);
        } else {
            return false;
        }
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    @Test
    public void test() {
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(3);
        p1.left = p2; p1.right = p3;
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(3);
        q1.left = q2; q1.right = q3;
        Assert.assertTrue(isSameTree(p1, q1));


        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        a1.left = a2;
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        b1.right = b2;
        Assert.assertFalse(isSameTree(a1, b1));

        TreeNode m1 = new TreeNode(1);
        TreeNode m2 = new TreeNode(2);
        TreeNode m3 = new TreeNode(1);
        m1.left = m2; m1.right = m3;
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(2);
        n1.left = n2; n1.right = n3;
        Assert.assertFalse(isSameTree(m1, n1));

        Assert.assertTrue(isSameTree(null, null));
    }

    @Test
    public void test2() {
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(3);
        p1.left = p2; p1.right = p3;
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(3);
        q1.left = q2; q1.right = q3;
        Assert.assertTrue(isSameTree2(p1, q1));


        TreeNode a1 = new TreeNode(1);
        TreeNode a2 = new TreeNode(2);
        a1.left = a2;
        TreeNode b1 = new TreeNode(1);
        TreeNode b2 = new TreeNode(2);
        b1.right = b2;
        Assert.assertFalse(isSameTree2(a1, b1));

        TreeNode m1 = new TreeNode(1);
        TreeNode m2 = new TreeNode(2);
        TreeNode m3 = new TreeNode(1);
        m1.left = m2; m1.right = m3;
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(2);
        n1.left = n2; n1.right = n3;
        Assert.assertFalse(isSameTree2(m1, n1));

        Assert.assertTrue(isSameTree2(null, null));
    }
}
