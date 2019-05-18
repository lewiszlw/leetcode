package leetcode.algorithms;

/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
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
 * @date 2019-05-18
 */
public class _0543DiameterOfBinaryTree {

    /**
     * 解法一
     * 递归遍历子节点深度，计算每个节点的diameter，
     * 最大的则为该树的diameter
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Diameter diameter = new Diameter(0);
        diameterOfBinaryTree(root, 1, diameter);
        return diameter.val;
    }

    /**
     *  递归遍历子节点深度
     * @param root 当前节点，非空
     * @param depth 当前节点到根节点的深度
     * @param diameter 使用对象的引用传递，Integer不行
     */
    private int diameterOfBinaryTree(TreeNode root, int depth, Diameter diameter) {
        int leftDepth = root.left == null? depth: diameterOfBinaryTree(root.left, depth + 1, diameter);
        int rightDepth = root.right == null? depth: diameterOfBinaryTree(root.right, depth + 1, diameter);
        // 计算当前节点下的diameter
        Integer currentDiameter = (leftDepth - depth) + (rightDepth - depth);
        if (currentDiameter > diameter.val) {
            diameter.val = currentDiameter;
        }
        return leftDepth > rightDepth? leftDepth: rightDepth;
    }

    class Diameter {
        int val;
        Diameter(int val) { this.val = val; }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    @Test
    public void test() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2; n1.right = n3;
        n2.left = n4; n2.right = n5;
        Assert.assertTrue(diameterOfBinaryTree(n1) == 3);
    }
}
