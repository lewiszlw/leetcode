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
     * 解法一：遍历，找出每个字符为中心的回文子串，取最大长度
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

    @Test
    public void test() {
        Assert.assertTrue(longestPalindrome("babad").equals("bab")
                || longestPalindrome("babad").equals("aba"));
        Assert.assertTrue(longestPalindrome("cbbd").equals("bb"));
    }
}
