package leetcode.algorithms;

//Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
//in the tree.
//
// According to the definition of LCA on Wikipedia: “The lowest common ancestor
//is defined between two nodes p and q as the lowest node in T that has both p and
// q as descendants (where we allow a node to be a descendant of itself).”
//
//
// Example 1:
//
//
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//Output: 3
//Explanation: The LCA of nodes 5 and 1 is 3.
//
//
// Example 2:
//
//
//Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//Output: 5
//Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant o
//f itself according to the LCA definition.
//
//
// Example 3:
//
//
//Input: root = [1,2], p = 1, q = 2
//Output: 1
//
//
//
// Constraints:
//
//
// The number of nodes in the tree is in the range [2, 105].
// -109 <= Node.val <= 109
// All Node.val are unique.
// p != q
// p and q will exist in the tree.
//
// Related Topics Tree
// 👍 5836 👎 208

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class _0236LowestCommonAncestorOfABinaryTree {
    /**
     * 解法1：存储父节点（类似并查集）
     * 可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，
     * 再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // node -> node父节点 映射
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        // 前序遍历dfs
        preOrder(root, parentMap);

        // p到跟节点的链（包含p，p可能是最近公共祖先）
        List<TreeNode> parentsChainForP = new ArrayList<>();
        parentsChainForP.add(p);
        while (p != null) {
            TreeNode parent = parentMap.get(p);
            parentsChainForP.add(parent);
            p = parent;
        }

        while (q != null) {
            // q也可能是最近公共祖先
            if (parentsChainForP.contains(q)) {
                return q;
            }
            q = parentMap.get(q);
        }

        return null;
    }

    // 前序遍历
    private void preOrder(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root.left != null) {
            parentMap.put(root.left, root);
            preOrder(root.left, parentMap);
        }
        if (root.right != null) {
            parentMap.put(root.right, root);
            preOrder(root.right, parentMap);
        }
    }




    /**
     * 解法2：后序遍历dfs
     * 递归函数功能：
     *    1.如果 p 和 q 都存在，则返回它们的公共祖先（最近）
     *    2.如果只存在一个，则返回存在的一个
     *    3.如果 p 和 q 都不存在，则返回NULL
     *
     *             3
     *          /    \
     *         5      1
     *       /  \    /  \
     *     6    2   0    8
     *         / \
     *        7   4
     *  举例：p为7，q为0
     *  最后一轮递归时，left为6，right为0，所以返回root 3
     */
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);
        // 1.当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p,q ，返回 null
        if (left == null && right == null) {
            return null;
        }
        // 2.当 left 为空 ，right 不为空：p,q 都不在 root 的左子树中，直接返回 right。
        // 具体可分为两种情况：
        //    p,q 其中一个在 root 的右子树中，此时 right 指向 p（假设为p）；
        //    p,q 两节点都在 root 的右子树中，此时的 right 指向最近公共祖先节点；
        if (left == null) {
            return right;
        }
        // 3.当 right 为空，同理情况2
        if (right == null) {
            return left;
        }
        // 4.当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧（分别在左 / 右子树），
        // 因此 root 为最近公共祖先，返回 root
        return root;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    @Test
    public void test() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node3.left = node6; node3.right = node7;
        node5.left = node8; node5.right = node9;
        Assert.assertEquals(3, lowestCommonAncestor(node1, node2, node3).val);
        Assert.assertEquals(3, lowestCommonAncestor(node1, node4, node6).val);
        Assert.assertEquals(5, lowestCommonAncestor(node1, node4, node9).val);
        Assert.assertEquals(5, lowestCommonAncestor(node1, node2, node9).val);
    }

    @Test
    public void test2() {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);
        node1.left = node2; node1.right = node3;
        node2.left = node4; node2.right = node5;
        node3.left = node6; node3.right = node7;
        node5.left = node8; node5.right = node9;
//        Assert.assertEquals(3, lowestCommonAncestor2(node1, node2, node3).val);
        Assert.assertEquals(3, lowestCommonAncestor2(node1, node4, node6).val);
//        Assert.assertEquals(5, lowestCommonAncestor2(node1, node4, node9).val);
//        Assert.assertEquals(5, lowestCommonAncestor2(node1, node2, node9).val);
    }
}
