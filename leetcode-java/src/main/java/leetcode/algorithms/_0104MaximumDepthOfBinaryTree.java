package leetcode.algorithms;

/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
 *
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (59.63%)
 * Total Accepted:    484.6K
 * Total Submissions: 808.5K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, find its maximum depth.
 *
 * The maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given binary tree [3,9,20,null,null,15,7],
 *
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 * return its depth = 3.
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
 * 递归记录遍历深度
 *
 * @author zhanglinwei02
 * @date 2019-04-16
 */
public class _0104MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    /**
     * 递归
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
        TreeNode p5 = new TreeNode(17);
        p1.left = p2; p1.right = p3;
        p3.left = p4; p3.right = p5;
        Assert.assertEquals(maxDepth(p1), 3);
    }
}
