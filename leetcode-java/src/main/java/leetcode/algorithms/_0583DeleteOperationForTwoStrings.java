package leetcode.algorithms;

/*
 * @lc app=leetcode id=583 lang=java
 *
 * [583] Delete Operation for Two Strings
 *
 * https://leetcode.com/problems/delete-operation-for-two-strings/description/
 *
 * algorithms
 * Medium (44.38%)
 * Likes:    694
 * Dislikes: 19
 * Total Accepted:    31.5K
 * Total Submissions: 69.9K
 * Testcase Example:  '"sea"\n"eat"'
 *
 *
 * Given two words word1 and word2, find the minimum number of steps required
 * to make word1 and word2 the same, where in each step you can delete one
 * character in either string.
 *
 *
 * Example 1:
 *
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to
 * make "eat" to "ea".
 *
 *
 *
 * Note:
 *
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
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
public class _0583DeleteOperationForTwoStrings {

    /**
     * 解法一：动态规划。转换为求最长公共子序列问题
     * dp[i][j]：表示以A[i]、B[j]结尾的最长公共子串(非连续)的长度
     * 状态转移方程：
     * 如果word1.charAt(i) == word2.charAt(j): dp[i][j] = dp[i - 1][j - 1] + 1;
     * 如果word1.charAt(i) != word2.charAt(j): dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
     */
    public int minDistance(String word1, String word2) {
        // 边界
        if (word1 == null || word1.length() == 0) {
            return word2 == null? 0: word2.length();
        }
        if (word2 == null || word2.length() == 0) {
            return word1 == null? 0: word1.length();
        }

        // 初始化dp
        int[][] dp = new int[word1.length()][word2.length()];
        int maxLen = 0;
        boolean flag1 = false;
        for (int i = 0; i < word1.length(); i++) {
            if (flag1) {
                // 当前面已经有相等的情况，后面全部为1
                dp[i][0] = 1;
            } else if (word1.charAt(i) == word2.charAt(0)) {
                dp[i][0] = 1;
                flag1 = true;
            }
            maxLen = Math.max(maxLen, dp[i][0]);
        }
        boolean flag2 = false;
        for (int i = 0; i < word2.length(); i++) {
            if (flag2) {
                dp[0][i] = 1;
            } else if (word1.charAt(0) == word2.charAt(i)) {
                dp[0][i] = 1;
                flag2 = true;
            }
            maxLen = Math.max(maxLen, dp[0][i]);
        }

        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    // 不等，则dp[i][j-1]、dp[i-1][j]取大者
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return word1.length() + word2.length() - 2 * maxLen;
    }


    @Test
    public void test() {
        Assert.assertTrue(minDistance("sea", "eat") == 2);
        Assert.assertTrue(minDistance("", "a") == 1);
        Assert.assertTrue(minDistance("a", "a") == 0);
        Assert.assertTrue(minDistance("ab", "bc") == 2);
        Assert.assertTrue(minDistance("park", "spake") == 3);
    }
}
