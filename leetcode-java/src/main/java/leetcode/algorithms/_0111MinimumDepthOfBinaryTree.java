package leetcode.algorithms;

/*
 * @lc app=leetcode id=111 lang=java
 *
 * [111] Minimum Depth of Binary Tree
 *
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (34.99%)
 * Total Accepted:    285.5K
 * Total Submissions: 813.4K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, find its minimum depth.
 *
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
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
 * return its minimum depth = 2.
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
 *
 * @author zhanglinwei02
 * @date 2019-04-17
 */
public class _0111MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {
        return minDepth(root, 0);
    }

    /**
     * 递归：获取左右子树深度，比较深度值
     * @param root 当前节点
     * @param depth 遍历当前节点前的已遍历的深度
     */
    private int minDepth(TreeNode root, int depth) {
        if (root == null) {
            return depth;
        }
        // 当前节点不为空，depth加1
        depth += 1;
        // 子节点为空，即无子树，标记为-1
        int leftDepth = root.left == null? -1: minDepth(root.left, depth);
        int rightDepth = root.right == null? -1: minDepth(root.right, depth);
        if (leftDepth != -1 && rightDepth != -1) {
            // 左右子树均存在，则depth较小的返回
            return leftDepth < rightDepth? leftDepth: rightDepth;
        } else {
            // 左子树为空，返回右子树depth
            // 右子树为空，返回左子树depth
            // 左右子树均为空，返回当前root节点depth
            return leftDepth == -1? (rightDepth == -1? depth: rightDepth) : leftDepth;
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
        TreeNode p1 = new TreeNode(3);
        TreeNode p2 = new TreeNode(9);
        TreeNode p3 = new TreeNode(20);
        TreeNode p4 = new TreeNode(15);
        TreeNode p5 = new TreeNode(7);
        p1.left = p2; p1.right = p3;
        p3.left = p4; p3.right = p5;
        Assert.assertEquals(minDepth(p1), 2);

        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        q1.left = q2;
        Assert.assertEquals(minDepth(q1), 2);
    }
}
