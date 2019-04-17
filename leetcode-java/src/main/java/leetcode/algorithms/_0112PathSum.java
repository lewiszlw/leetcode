package leetcode.algorithms;

/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
 *
 * https://leetcode.com/problems/path-sum/description/
 *
 * algorithms
 * Easy (37.26%)
 * Total Accepted:    301.6K
 * Total Submissions: 805.3K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *
 * ⁠     5
 * ⁠    / \
 * ⁠   4   8
 * ⁠  /   / \
 * ⁠ 11  13  4
 * ⁠/  \      \
 * 7    2      1
 *
 *
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
public class _0112PathSum {

    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSum(root, 0, sum);
    }

    /**
     * 递归遍历每个节点同时累积val之和，如果存在val之和满足条件，且节点为叶子节点，即为true
     * @param root 当前节点
     * @param sum 当前节点前路径上所有节点之和
     * @param target 目标值
     * @return
     */
    private boolean hasPathSum(TreeNode root, int sum, int target) {
        if (root == null) {
            return false;
        }
        // 当前节点不为空，sum值增加
        sum += root.val;
        if (sum == target && root.left == null && root.right == null) {
            // sum值满足 且 当前节点为叶子节点
            return true;
        } else {
            // 子节点是否有满足条件的
            return hasPathSum(root.left, sum, target) || hasPathSum(root.right, sum, target);
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
        TreeNode q1 = new TreeNode(5);
        TreeNode q2 = new TreeNode(4);
        TreeNode q3 = new TreeNode(8);
        TreeNode q4 = new TreeNode(11);
        TreeNode q5 = new TreeNode(13);
        TreeNode q6 = new TreeNode(4);
        TreeNode q7 = new TreeNode(7);
        TreeNode q8 = new TreeNode(2);
        TreeNode q9 = new TreeNode(1);
        q1.left = q2; q1.right = q3;
        q2.left = q4;
        q3.left = q5; q3.right = q6;
        q4.left = q7; q4.right = q8;
        q6.right = q9;
        Assert.assertTrue(hasPathSum(q1, 22));
    }
}
