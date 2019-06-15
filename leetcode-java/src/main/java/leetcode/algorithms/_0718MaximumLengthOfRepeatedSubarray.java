package leetcode.algorithms;

/*
 * @lc app=leetcode id=718 lang=java
 *
 * [718] Maximum Length of Repeated Subarray
 *
 * https://leetcode.com/problems/maximum-length-of-repeated-subarray/description/
 *
 * algorithms
 * Medium (45.14%)
 * Likes:    641
 * Dislikes: 29
 * Total Accepted:    34.3K
 * Total Submissions: 74.3K
 * Testcase Example:  '[1,2,3,2,1]\n[3,2,1,4,7]'
 *
 * Given two integer arrays A and B, return the maximum length of an subarray
 * that appears in both arrays.
 *
 * Example 1:
 *
 *
 * Input:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * Output: 3
 * Explanation:
 * The repeated subarray with maximum length is [3, 2, 1].
 *
 *
 *
 *
 * Note:
 *
 *
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
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
public class _0718MaximumLengthOfRepeatedSubarray {

    /**
     * 解法一：动态规划
     * dp[i][i]表示以A[i]、B[j]结尾的最长公共连续子数组的长度
     * 状态转移方程: dp[i][j] = dp[i - 1][j - 1] + 1（前提是A[i]==B[j]）
     */
    public int findLength(int[] A, int[] B) {
        // dp[i][j]：表示以A[i]、B[j]结尾的最长公共连续子数组的长度
        int[][] dp = new int[A.length][B.length];
        // 初始化第一行第一列
        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[0]) {
                // A[i]结尾和B[0]结尾的最长公共连续子数组长度为1
                dp[i][0] = 1;
            }
        }
        for (int i = 0; i < B.length; i++) {
            if (A[0] == B[i]) {
                // A[0]结尾和B[i]结尾的最长公共连续子数组长度为1
                dp[0][i] = 1;
            }
        }

        int maxLen = 0;
        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {
                if (A[i] == B[j]) {
                    // A[i]结尾和B[j]结尾的最大公共连续子数组长度 = A[i-1]结尾和B[j-1]结尾的最大公共连续子数组长度 + 1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLen = Math.max(dp[i][j], maxLen);
                }
            }
        }
        return maxLen;
    }


    @Test
    public void test() {
        Assert.assertTrue(findLength(new int[]{1,2,3,2,1}, new int[]{3,2,1,4,7}) == 3);
    }
}
