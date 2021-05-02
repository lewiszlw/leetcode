package leetcode.algorithms;

/*
 * @lc app=leetcode id=5 lang=java
 *
 * [5] Longest Palindromic Substring
 *
 * https://leetcode.com/problems/longest-palindromic-substring/description/
 *
 * algorithms
 * Medium (26.85%)
 * Likes:    3413
 * Dislikes: 327
 * Total Accepted:    528.6K
 * Total Submissions: 2M
 * Testcase Example:  '"babad"'
 *
 * Given a string s, find the longest palindromic substring in s. You may
 * assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 *
 *
 * Example 2:
 *
 *
 * Input: "cbbd"
 * Output: "bb"
 *
 * 1 <= s.length <= 1000
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-12
 */
public class _0005LongestPalindromicSubstring {

    /**
     * 解法一：中心扩展
     * 遍历，找出每个字符为中心的回文子串，取最大长度
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int max = 0, beginIndex = 0, endIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            // 假如回文串是奇数
            int[] oddPalindrome = isPalindrome(s, i, i);
            if (oddPalindrome[0] > max) {
                max = oddPalindrome[0];
                beginIndex = oddPalindrome[1];
                endIndex = oddPalindrome[2];
            }
            // 假如回文串是偶数个
            int[] evenPalindrome = isPalindrome(s, i, i + 1);
            if (evenPalindrome[0] > max) {
                max = evenPalindrome[0];
                beginIndex = evenPalindrome[1];
                endIndex = evenPalindrome[2];
            }
        }
        return s.substring(beginIndex, endIndex + 1);
    }

    /**
     * 判断是否回文
     * @return {len, beginIndex, endIndex}
     */
    private int[] isPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left --;
            right ++;
        }
        // 如果left=1，right=2，不是回文，返回{0, 2, 1}，可以兼容此情况
        return new int[] {(right - 1) - (left + 1) + 1, left + 1, right - 1};
    }



    /**
     * 解法二：动态规划
     * 对于一个子串而言，如果它是回文串，并且长度大于 2，那么将它首尾的两个字母去除之后，它仍然是个回文串。
     * 用 P(i,j) 表示字符串 S 的第 i 到 j 个字母组成的串是否为回文串（true/false）, 动态规划方程
     *              P(i,j) = P(i+1,j−1) && (Si == Sj)
     */
    public String longestPalindrome2(String s) {
        // dp[i][j] 代表 子串 s[i]-s[j] (包含s[i]和s[j]) 是否为回文串，即true/false
        boolean[][] dp = new boolean[s.length()][s.length()];

        // 单个字符为回文串
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }

        int maxLen = 0;
        // begin, end 初始为0，这样如果 s 只有一个字符时也成立
        int begin = 0;
        int end = 0;

        // L 为回文串长度，从2开始
        for (int L = 2; L <= s.length(); L++) {
            // i 为字符串左位置
            for (int i = 0; i < s.length() - L + 1; i++) {
                // j 为字符串右位置
                int j = i + L - 1;

                if (L == 2) {
                    if (s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = true;
                        int len = j - i + 1;
                        if (len > maxLen) {
                            maxLen = len;
                            begin = i;
                            end = j;
                        }
                    } else {
                        dp[i][j] = false;
                    }
                    continue;
                }

                // L > 2
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                    if (dp[i][j]) {
                        int len = j - i + 1;
                        if (len > maxLen) {
                            maxLen = len;
                            begin = i;
                            end = j;
                        }
                    }
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return s.substring(begin, end + 1);
    }



    @Test
    public void test() {
        Assert.assertTrue(longestPalindrome("babad").equals("bab")
                || longestPalindrome("babad").equals("aba"));
        Assert.assertTrue(longestPalindrome("cbbd").equals("bb"));
    }

    @Test
    public void test2() {
        Assert.assertTrue(longestPalindrome2("c").equals("c"));
        Assert.assertTrue(longestPalindrome2("cbbd").equals("bb"));
        Assert.assertTrue(longestPalindrome2("babad").equals("bab"));
    }
}
