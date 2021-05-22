package leetcode.algorithms;

//You are given an integer array nums and an integer target.
//
// You want to build an expression out of nums by adding one of the symbols '+'
//and '-' before each integer in nums and then concatenate all the integers.
//
//
// For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1
//and concatenate them to build the expression "+2-1".
//
//
// Return the number of different expressions that you can build, which evaluate
//s to target.
//
//
// Example 1:
//
//
//Input: nums = [1,1,1,1,1], target = 3
//Output: 5
//Explanation: There are 5 ways to assign symbols to make the sum of nums be tar
//get 3.
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
//
// Example 2:
//
//
//Input: nums = [1], target = 1
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 1000
//
// Related Topics Dynamic Programming Depth-first Search
// 👍 4187 👎 169

import org.junit.Assert;
import org.junit.Test;

public class _0494TargetSum {

    /**
     * 解法1：构造满二叉树
     * 二叉树左节点为减运算，右节点为加运算
     *
     * [1,3,2]
     *                              0
     *                  /                       \
     *               -1(-1)                    1(+1)
     *             /         \              /        \
     *         -4(-3)      2(+3)        -2(-3)       4(+3)
     *        /     \     /     \       /   \        /   \
     *     -6(-2) -2(+2) 0(-2) 4(+2) -4(-2) 0(+2) 2(-2) 6(+2)
     */
    public int findTargetSumWays(int[] nums, int target) {
        // 构造满二叉树，根节点为0
        int[] tree = new int[calculateBTreeNodeCount(nums.length + 1)];

        // 树的每层起始索引和结束节点索引
        int startIndex = 0;
        int endIndex = 0;

        // 从第二层开始构建树
        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            int newStartIndex = -1;
            int newEndIndex = -1;

            for (int j = startIndex; j <= endIndex; j++) {

                // 构建当前节点的左右子节点
                int leftChild = 2 * j + 1;
                int rightChild = 2 * j + 2;
                tree[leftChild] = tree[j] - num;
                tree[rightChild] = tree[j] + num;

                // 更新树的下一层起始和结束节点索引
                if (j == startIndex) {
                    newStartIndex = leftChild;
                }
                if (j == endIndex) {
                    newEndIndex = rightChild;
                }
            }

            startIndex = newStartIndex;
            endIndex = newEndIndex;
        }

        // 遍历所有叶子节点
        int ways = 0;
        for (int i = startIndex; i <= endIndex; i++) {
            if (tree[i] == target) {
                ways ++;
            }
        }
        return ways;
    }

    // 计算n层满二叉树的节点总数
    public int calculateBTreeNodeCount(int level) {
        // 计算2的n次幂
        int totalCount = 1; // 总节点数
        int levelCount = 1; // 每层节点数
        // 从第二层开始
        for (int i = 1; i < level; i++) {
            levelCount *= 2;
            totalCount += levelCount;
        }
        return totalCount;
    }


    /**
     * 解法2：动态规划
     *
     * dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数。
     * 考虑第 i 个数 nums[i]，它可以被添加 + 或 -，因此状态转移方程如下：
     *   dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
     *
     * 也可以写成递推的形式：
     *   dp[i][j + nums[i]] += dp[i - 1][j]
     *   dp[i][j - nums[i]] += dp[i - 1][j]
     *
     * 由于数组中所有数的和不超过 1000，那么 j 的最小值可以达到 -1000。
     * 由于数组下标不能为负数，所以需要给 dp[i][j] 的第二维预先增加 1000，即：
     *   dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
     *   dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
     *
     */
    public int findTargetSumWays2(int[] nums, int target) {

        int[][] dp = new int[nums.length][2001];
        dp[0][nums[0] + 1000] = 1;
        dp[0][-nums[0] + 1000] += 1; // 防止 nums[0] = 0 情况

        // 从第二个数开始构建dp
        for (int i = 1; i < nums.length; i++) {
            for (int sum = -1000; sum <= 1000; sum++) {
                if (dp[i - 1][sum + 1000] > 0) {
                    // dp[i][sum + 1000] = dp[i - 1][sum - nums[i] + 1000] + dp[i - 1][sum + nums[i] + 1000];
                    // 上述算法存在 dp[i - 1][sum + nums[i] + 1000] 当前还未算出的情况
                    dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                    dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                }
            }
        }
        return dp[nums.length - 1][target + 1000];
    }



    @Test
    public void test() {
        Assert.assertEquals(5, findTargetSumWays(new int[]{1,1,1,1,1}, 3));
        Assert.assertEquals(1, findTargetSumWays(new int[]{1}, 1));
    }

    @Test
    public void test2() {
        Assert.assertEquals(5, findTargetSumWays2(new int[]{1,1,1,1,1}, 3));
        Assert.assertEquals(1, findTargetSumWays2(new int[]{1}, 1));
    }
}
