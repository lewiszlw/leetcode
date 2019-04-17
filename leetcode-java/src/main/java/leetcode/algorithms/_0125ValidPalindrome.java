package leetcode.algorithms;

/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 *
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (30.49%)
 * Total Accepted:    340.7K
 * Total Submissions: 1.1M
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * Given a string, determine if it is a palindrome, considering only
 * alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid
 * palindrome.
 *
 * Example 1:
 *
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 *
 *
 * Example 2:
 *
 *
 * Input: "race a car"
 * Output: false
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-18
 */
public class _0125ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return true;
        }
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char ci = s.charAt(i);
            char cj = s.charAt(j);
            if (!isAlphabet(ci) && !isNumeric(ci) ||
                    !isAlphabet(cj) && !isNumeric(cj)) {
                // 如果ci和cj种存在有不为字母和数字
                if (!isAlphabet(ci) && !isNumeric(ci)) {
                    i ++;
                }
                if (!isAlphabet(cj) && !isNumeric(cj)) {
                    j --;
                }
                continue;
            }
            if (!isSameAlphanumeric(ci, cj)) {
                return false;
            }
            i ++;
            j --;
        }
        return true;
    }

    /**
     * 是否为字母
     */
    private boolean isAlphabet(char c) {
        return c >= 65 && c <= 90 || c >= 97 && c <= 122;
    }

    /**
     * 是否为数字
     */
    private boolean isNumeric(char c) {
        return c >= 48 && c <= 57;
    }

    /**
     * 是否为相同字母或数字
     */
    private boolean isSameAlphanumeric(char c1, char c2) {
        if (isAlphabet(c1) && isAlphabet(c2) || isNumeric(c1) && isNumeric(c2)) {
            // 如果c1,c2同为数字或字母
            return Math.abs(c1 - c2) == 32 || Math.abs(c1 - c2) == 0;
        } else {
            return false;
        }
    }


    @Test
    public void test() {
        Assert.assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        Assert.assertFalse(isPalindrome("race a car"));
        Assert.assertFalse(isPalindrome("0P"));
    }
}
