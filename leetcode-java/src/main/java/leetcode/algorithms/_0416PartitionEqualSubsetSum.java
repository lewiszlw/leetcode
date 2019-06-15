package leetcode.algorithms;

/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (40.08%)
 * Likes:    1242
 * Dislikes: 36
 * Total Accepted:    88.8K
 * Total Submissions: 218.1K
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 *
 * Note:
 *
 *
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 *
 *
 *
 *
 * Example 1:
 *
 *
 * Input: [1, 5, 11, 5]
 *
 * Output: true
 *
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *
 *
 *
 *
 * Example 2:
 *
 *
 * Input: [1, 2, 3, 5]
 *
 * Output: false
 *
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-15
 */
public class _0416PartitionEqualSubsetSum {

    /**
     * 解法一：动态规划
     * 问题转化为01背包问题，即求背包容量为数组和的一半是否存在
     * 状态转移方程：dp[j] = dp[j] || dp[j - nums[i]];
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // sum为奇数直接返回
        if (sum % 2 == 1) {
            return false;
        }

        int subsetSum = sum / 2;
        // dp数组索引i为数值和，值为boolean(是否能存在和为索引值i的情况)
        boolean[] dp = new boolean[subsetSum + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = false;
        }
        // 和为0，是true
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = subsetSum; j >= nums[i]; j--) {
                // dp[j - nums[i]]即是否存在j-nums[i]的数组和
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[subsetSum];
    }


    @Test
    public void test() {
        Assert.assertTrue(canPartition(new int[]{1, 5, 11, 5}));
        Assert.assertTrue(!canPartition(new int[]{1, 2, 3, 5}));
    }
}
