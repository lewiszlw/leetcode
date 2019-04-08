package leetcode.algorithms;

/*
 * @lc app=leetcode id=28 lang=java
 *
 * [28] Implement strStr()
 *
 * https://leetcode.com/problems/implement-strstr/description/
 *
 * algorithms
 * Easy (31.49%)
 * Total Accepted:    403.6K
 * Total Submissions: 1.3M
 * Testcase Example:  '"hello"\n"ll"'
 *
 * Implement strStr().
 *
 * Return the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 *
 * Example 1:
 *
 *
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 *
 *
 * Example 2:
 *
 *
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 *
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great
 * question to ask during an interview.
 *
 * For the purpose of this problem, we will return 0 when needle is an empty
 * string. This is consistent to C's strstr() and Java's indexOf().
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-08
 */
public class _0028ImplementstrStr {

    /**
     * 解法一：暴力匹配
     */
    public int strStr(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.length() - i < needle.length()) {
                // 长度不足
                return -1;
            }
            boolean match = true;
            for (int j = 0; j < needle.length(); j++) {
                if (needle.charAt(j) != haystack.charAt(i + j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }


    @Test
    public void test1() {
        Assert.assertTrue(strStr("hello", "ll") == 2);
        Assert.assertTrue(strStr("aaaaa", "bba") == -1);
    }
}
