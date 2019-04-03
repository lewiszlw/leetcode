package leetcode.algorithms;

/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (33.15%)
 * Total Accepted:    432.8K
 * Total Submissions: 1.3M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 *
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 *
 *
 * Example 2:
 *
 *
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 *
 *
 * Note:
 *
 * All given inputs are in lowercase letters a-z.
 *
 */

import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-03
 */
public class _0014LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (true) {
            // 所有字符串的index位置字符是否相同
            boolean flag = true;
            for (int i = 0; i < strs.length - 1; i++) {
                if (strs[i].length() <= index ||
                        strs[i + 1].length() <= index ||
                        strs[i].charAt(index) != strs[i + 1].charAt(index)) {
                    flag = false;
                    break;
                }
            }
            // 防止为经过for循环直接到这里，如{""}
            if (flag && strs[0].length() > index) {
                sb.append(strs[0].charAt(index));
                index ++;
            } else {
                break;
            }
        }
        return sb.toString();
    }


    @Test
    public void test() {
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }

    @Test
    public void test2() {
        String[] strs = {""};
        System.out.println(longestCommonPrefix(strs));
    }
}
