package leetcode.algorithms;

//Given the root of a binary tree, return the zigzag level order traversal of it
//s nodes' values. (i.e., from left to right, then right to left for the next leve
//l and alternate between).
//
//
// Example 1:
//
//
//Input: root = [3,9,20,null,null,15,7]
//Output: [[3],[20,9],[15,7]]
//
//
// Example 2:
//
//
//Input: root = [1]
//Output: [[1]]
//
//
// Example 3:
//
//
//Input: root = []
//Output: []
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [0, 2000].
// -100 <= Node.val <= 100
//
// Related Topics Stack Tree Breadth-first Search
// 👍 3538 👎 130

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class _0103BinaryTreeZigzagLevelOrderTraversal {

    /**
     * 解法：层序遍历
     * 记录每层遍历顺序是从左往右还是从右往左
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // 存储每层的节点，均从左往右
        List<TreeNode> level = new ArrayList<>();
        if (root != null) {
            level.add(root);
        }
        // 遍历顺序 从左往右还是从右往左
        boolean leftToRight = true;
        while (level.size() != 0) {

            // 遍历当前层节点
            List<Integer> oneRes = new ArrayList<>();
            if (leftToRight) {
                for (int i = 0; i < level.size(); i++) {
                    oneRes.add(level.get(i).val);
                }
            } else {
                for (int i = level.size() - 1; i >= 0; i--) {
                    oneRes.add(level.get(i).val);
                }
            }
            res.add(oneRes);

            // 获取一下层节点
            List<TreeNode> nextLevel = new ArrayList<>();
            for (int i = 0; i < level.size(); i++) {
                TreeNode node = level.get(i);
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }
            level = nextLevel;

            // 更新读取顺序
            leftToRight = !leftToRight;
        }
        return res;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    @Test
    public void test() {
        TreeNode n5 = new TreeNode(7);
        TreeNode n4 = new TreeNode(15);
        TreeNode n3 = new TreeNode(20, n4, n5);
        TreeNode n2 = new TreeNode(9);
        TreeNode n1 = new TreeNode(3, n2, n3);
        System.out.println(zigzagLevelOrder(n1));
    }
}
