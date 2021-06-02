package leetcode.algorithms;

//Given two strings text1 and text2, return the length of their longest common s
//ubsequence. If there is no common subsequence, return 0.
//
// A subsequence of a string is a new string generated from the original string
//with some characters (can be none) deleted without changing the relative order o
//f the remaining characters.
//
//
// For example, "ace" is a subsequence of "abcde".
//
//
// A common subsequence of two strings is a subsequence that is common to both s
//trings.
//
//
// Example 1:
//
//
//Input: text1 = "abcde", text2 = "ace"
//Output: 3
//Explanation: The longest common subsequence is "ace" and its length is 3.
//
//
// Example 2:
//
//
//Input: text1 = "abc", text2 = "abc"
//Output: 3
//Explanation: The longest common subsequence is "abc" and its length is 3.
//
//
// Example 3:
//
//
//Input: text1 = "abc", text2 = "def"
//Output: 0
//Explanation: There is no such common subsequence, so the result is 0.
//
//
//
// Constraints:
//
//
// 1 <= text1.length, text2.length <= 1000
// text1 and text2 consist of only lowercase English characters.
//
// Related Topics Dynamic Programming
// 👍 3159 👎 39

import org.junit.Assert;
import org.junit.Test;

public class _1143LongestCommonSubsequence {

    /**
     * 解法：动态规划
     * dp[i][j] 表示 text1[0,i] 和 text2[0,j]的最长公共子序列。
     * （注：text1[0,i] 表示的是 text1 的第 0 个元素到第 i 个元素，两端都包含）
     *
     *       dp[i][j] = dp[i − 1][j − 1] + 1, 当 text1[i] == text2[j];
     *       dp[i][j] = max(dp[i − 1][j], dp[i][j − 1]), 当 text1[i] != text2[j]
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()][text2.length()];
        // 初始化dp[0][0]
        if (text1.charAt(0) == text2.charAt(0)) {
            dp[0][0] = 1;
        } else {
            dp[0][0] = 0;
        }
        for (int i = 0; i < text1.length(); i++) {
            char c1 = text1.charAt(i);
            for (int j = 0; j < text2.length(); j++) {
                // dp[i][j] = dp[i − 1][j − 1] + 1, 当 text1[i] == text2[j];
                if (c1 == text2.charAt(j)) {
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    // dp[i][j] = max(dp[i − 1][j], dp[i][j − 1]), 当 text1[i] != text2[j]
                    int max = 0;
                    if (i - 1 >= 0) {
                        max = Math.max(max, dp[i - 1][j]);
                    }
                    if (j - 1 >= 0) {
                        max = Math.max(max, dp[i][j - 1]);
                    }
                    dp[i][j] = max;
                }
            }
        }
        return dp[text1.length() - 1][text2.length() - 1];
    }


    @Test
    public void test() {
        Assert.assertEquals(3, longestCommonSubsequence("abcde", "ace"));
        Assert.assertEquals(0, longestCommonSubsequence("bd", "ace"));
        Assert.assertEquals(3, longestCommonSubsequence("abc", "abc"));
        Assert.assertEquals(1, longestCommonSubsequence("psnw", "vozsh"));
    }
}
