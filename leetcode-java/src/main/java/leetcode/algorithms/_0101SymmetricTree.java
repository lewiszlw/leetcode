package leetcode.algorithms;

/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 *
 * https://leetcode.com/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (42.97%)
 * Total Accepted:    382.7K
 * Total Submissions: 887.4K
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 *
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠/ \ / \
 * 3  4 4  3
 *
 *
 *
 * But the following [1,2,2,null,3,null,3]  is not:
 *
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠  \   \
 * ⁠  3    3
 *
 *
 *
 *
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
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
 * 解决思路：递归树的每层节点，对比对称位置节点是否相同
 * 使用中序遍历+回文判断，有一种情况无法判断，见测试用例
 *
 * @author zhanglinwei02
 * @date 2019-04-16
 */
public class _0101SymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 递归判断对应两边对应子节点是否相同
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null
            || left != null && right != null && left.val == right.val) {
            // left和right相同
            if (left == null) {
                // 如果都为null
                return true;
            }
            // 否则继续判断子节点
            return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
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
        TreeNode p3 = new TreeNode(2);
        TreeNode p4 = new TreeNode(3);
        TreeNode p5 = new TreeNode(4);
        TreeNode p6 = new TreeNode(4);
        TreeNode p7 = new TreeNode(3);
        p1.left = p2; p1.right = p3;
        p2.left = p4; p2.right = p5;
        p3.left = p6; p3.right = p7;
        Assert.assertTrue(isSymmetric(p1));

        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(2);
        TreeNode q4 = new TreeNode(3);
        TreeNode q5 = new TreeNode(3);
        q1.left = q2; q1.right = q3;
        q2.right = q4;
        q3.right = q5;
        Assert.assertFalse(isSymmetric(q1));

        TreeNode m1 = new TreeNode(1);
        TreeNode m2 = new TreeNode(2);
        TreeNode m3 = new TreeNode(3);
        TreeNode m4 = new TreeNode(3);
        TreeNode m5 = new TreeNode(2);
        m1.left = m2; m1.right = m3;
        m2.left = m4;
        m3.left = m5;
        Assert.assertFalse(isSymmetric(m1));

        /**
         * 中序遍历+回文判断，无法判断下面情况
         */
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(2);
        TreeNode n7 = new TreeNode(2);
        n1.left = n2; n1.right = n3;
        n2.right = n4;
        n3.right = n5;
        n4.left = n6;
        n5.left = n7;
        Assert.assertFalse(isSymmetric(n1));
    }
}
