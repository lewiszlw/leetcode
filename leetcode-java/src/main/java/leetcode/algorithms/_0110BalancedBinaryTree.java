package leetcode.algorithms;

/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 *
 * https://leetcode.com/problems/balanced-binary-tree/description/
 *
 * algorithms
 * Easy (40.55%)
 * Total Accepted:    308.8K
 * Total Submissions: 759K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 *
 * a binary tree in which the depth of the two subtrees of every node never
 * differ by more than 1.
 *
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * Return true.
 *
 * Example 2:
 *
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *
 * ⁠      1
 * ⁠     / \
 * ⁠    2   2
 * ⁠   / \
 * ⁠  3   3
 * ⁠ / \
 * ⁠4   4
 *
 *
 * Return false.
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

/**
 * Desc:
 * 递归遍历左右子树深度，对比子树深度差值是否大于1
 *
 * @author zhanglinwei02
 * @date 2019-04-17
 */
public class _0110BalancedBinaryTree {

    // 是否为BST
    private volatile boolean balance = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        maxDepth(root, 0);
        return balance;
    }

    /**
     * 递归：获取左右子树深度，计算深度差值是否大于1
     * @param root 当前节点
     * @param depth 遍历当前节点前的已遍历的深度
     */
    private int maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        // 当前节点不为空，depth加1
        depth += 1;
        // 左子树深度
        int leftDepth = root.left == null? depth: maxDepth(root.left, depth);
        // 右子树深度
        int rightDepth = root.right == null? depth: maxDepth(root.right, depth);
        // 如果存在左右子树深度差超过1，则非BST
        if (Math.abs(leftDepth - rightDepth) > 1) {
            balance = false;
        }
        return leftDepth < rightDepth? rightDepth: leftDepth;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    @Test
    public void test() {
        TreeNode p1 = new TreeNode(3);
        TreeNode p2 = new TreeNode(9);
        TreeNode p3 = new TreeNode(20);
        TreeNode p4 = new TreeNode(15);
        TreeNode p5 = new TreeNode(7);
        p1.left = p2; p1.right = p3;
        p3.left = p4; p3.right = p5;
        Assert.assertTrue(isBalanced(p1));

        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(2);
        TreeNode q4 = new TreeNode(3);
        TreeNode q5 = new TreeNode(3);
        TreeNode q6 = new TreeNode(4);
        TreeNode q7 = new TreeNode(4);
        q1.left = q2; q1.right = q3;
        q2.left = q4; q2.right = q5;
        q4.left = q6; q4.right = q7;
        Assert.assertFalse(isBalanced(q1));
    }
}
