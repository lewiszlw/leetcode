package leetcode.algorithms;

/*
 * @lc app=leetcode id=58 lang=java
 *
 * [58] Length of Last Word
 *
 * https://leetcode.com/problems/length-of-last-word/description/
 *
 * algorithms
 * Easy (32.19%)
 * Total Accepted:    257.8K
 * Total Submissions: 800.1K
 * Testcase Example:  '"Hello World"'
 *
 * Given a string s consists of upper/lower-case alphabets and empty space
 * characters ' ', return the length of last word in the string.
 *
 * If the last word does not exist, return 0.
 *
 * Note: A word is defined as a character sequence consists of non-space
 * characters only.
 *
 * Example:
 *
 * Input: "Hello World"
 * Output: 5
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-15
 */
public class _0058LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0 || s.equals(" ")) {
            return 0;
        }
        String[] strings = s.trim().split(" ");
        return strings[strings.length - 1].length();
    }

    @Test
    public void test() {
        Assert.assertEquals(lengthOfLastWord("Hello  World  "), 5);
    }
}
