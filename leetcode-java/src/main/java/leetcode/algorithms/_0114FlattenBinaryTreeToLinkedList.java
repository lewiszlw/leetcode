package leetcode.algorithms;

/*
 * @lc app=leetcode id=114 lang=java
 *
 * [114] Flatten Binary Tree to Linked List
 *
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 *
 * algorithms
 * Medium (41.49%)
 * Likes:    1481
 * Dislikes: 191
 * Total Accepted:    240.8K
 * Total Submissions: 565K
 * Testcase Example:  '[1,2,5,3,4,null,6]'
 *
 * Given a binary tree, flatten it to a linked list in-place.
 *
 * For example, given the following tree:
 *
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   5
 * ⁠/ \   \
 * 3   4   6
 *
 *
 * The flattened tree should look like:
 *
 *
 * 1
 * ⁠\
 * ⁠ 2
 * ⁠  \
 * ⁠   3
 * ⁠    \
 * ⁠     4
 * ⁠      \
 * ⁠       5
 * ⁠        \
 * ⁠         6
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

/**
 * Desc:
 * 注：Binary Tree 指 Binary Search Tree (Binary Sorted Tree)
 *
 * @author zhanglinwei02
 * @date 2019-06-16
 */
public class _0114FlattenBinaryTreeToLinkedList {

    /**
     * 解法一：递归前序遍历
     * 前序遍历，将root的左子树插入到root和root的右子树之间，并将左子树删除，递归处理每个节点
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode right = root.right;
        TreeNode left = root.left;
        // 删除左子树
        root.left = null;
        if (left != null) {
            // 将左子树接到root.right位置
            root.right = left;
            // 将右子树接到左子树最right的位置
            while (root.right != null) {
                root = root.right;
            }
            root.right = right;
        }
        flatten(left);
        flatten(right);
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    @Test
    public void test() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        t1.left = t2; t1.right = t5;
        t2.left = t3; t2.right = t4;
        t5.right = t6;
        flatten(t1);
        Assert.assertTrue(t1.val == 1);
        Assert.assertTrue(t1.right.val == 2);
        Assert.assertTrue(t1.right.right.val == 3);
        Assert.assertTrue(t1.right.right.right.val == 4);
        Assert.assertTrue(t1.right.right.right.right.val == 5);
        Assert.assertTrue(t1.right.right.right.right.right.val == 6);
    }
}
