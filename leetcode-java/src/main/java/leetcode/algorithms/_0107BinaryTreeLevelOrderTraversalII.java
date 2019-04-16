package leetcode.algorithms;

/*
 * @lc app=leetcode id=107 lang=java
 *
 * [107] Binary Tree Level Order Traversal II
 *
 * https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
 *
 * algorithms
 * Easy (45.97%)
 * Total Accepted:    218.2K
 * Total Submissions: 471.9K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the bottom-up level order traversal of its
 * nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 *
 *
 *
 * return its bottom-up level order traversal as:
 *
 * [
 * ⁠ [15,7],
 * ⁠ [9,20],
 * ⁠ [3]
 * ]
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
import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-16
 */
public class _0107BinaryTreeLevelOrderTraversalII {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        levelOrderBottom(Arrays.asList(root), list);
        // 反转list
        List<List<Integer>> result = new ArrayList<>(list.size());
        for (int i = list.size() - 1; i >= 0; i --) {
            result.add(list.get(i));
        }
        return result;
    }

    private void levelOrderBottom(List<TreeNode> nodeList, List<List<Integer>> list) {
        if (nodeList.size() == 0) {
            return;
        }
        // 遍历当前层级的节点值组成列表
        List<Integer> valList = new ArrayList<>();
        // 组装下一层级节点的列表
        List<TreeNode> nextNodeList = new ArrayList<>();
        nodeList.stream().forEach(treeNode -> {
            valList.add(treeNode.val);
            if (treeNode.left != null) {
                nextNodeList.add(treeNode.left);
            }
            if (treeNode.right != null) {
                nextNodeList.add(treeNode.right);
            }
        });
        list.add(valList);
        // 继续遍历下一层级
        levelOrderBottom(nextNodeList, list);
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
        List<List<Integer>> result = levelOrderBottom(p1);
        Assert.assertTrue(result.get(0).get(0) == 15);
        Assert.assertTrue(result.get(0).get(1) == 7);
        Assert.assertTrue(result.get(1).get(0) == 9);
        Assert.assertTrue(result.get(1).get(1) == 20);
        Assert.assertTrue(result.get(2).get(0) == 3);
    }
}
