package leetcode.algorithms;

/*
 * @lc app=leetcode id=3 lang=java
 *
 * [3] Longest Substring Without Repeating Characters
 *
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 *
 * algorithms
 * Medium (28.10%)
 * Likes:    5290
 * Dislikes: 281
 * Total Accepted:    883.7K
 * Total Submissions: 3.1M
 * Testcase Example:  '"abcabcbb"'
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 *
 *
 * Example 1:
 *
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 *
 *
 * Example 2:
 *
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 *
 *
 * Example 3:
 *
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * ⁠            Note that the answer must be a substring, "pwke" is a
 * subsequence and not a substring.
 *
 *
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-22
 */
public class _0003LongestSubstringWithoutRepeatingCharacters {

    /**
     * 解法一：遍历s，发现重复时，下一次遍历从冲突字符处下一个开始
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLen = 0;

        int i = 0;
        while (i < s.length()) {

            // 当前子串
            List<Character> substring = new ArrayList<>();

            boolean repeat = false;

            int j = i;
            while (j < s.length()) {

                char curr = s.charAt(j);
                int index = substring.indexOf(curr);

                // 当前字符存在重复
                if (index != -1) {
                    maxLen = Math.max(maxLen, substring.size());
                    // 下一次从冲突字符处下一个开始遍历
                    i = i + index + 1;
                    repeat = true;
                    break;
                }

                // 不重复
                substring.add(curr);
                j ++;
            }

            // 未重复，正常遍历结束
            if (!repeat) {
                maxLen = Math.max(maxLen, substring.size());
                break;
            }
        }
        return maxLen;
    }

    /**
     * 解法2：双指针
     * 两个指针形成一个滑动窗口，截取最长的不重复子串
     */
    public int lengthOfLongestSubstring2(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            char curr = s.charAt(j);
            if (set.contains(curr)) {
                // 移除首字符
                set.remove(s.charAt(i));
                i ++;
            } else {
                set.add(curr);
                max = Math.max(max, set.size());
                j ++;
            }
        }
        return max;
    }

    /**
     * 解法3：双指针优化
     * 两个指针形成一个滑动窗口，截取最长的不重复子串，当遇到下一个字符与子串某一个字符A重复，左指针跳到字符A下一个元素
     */
    public int lengthOfLongestSubstring3(String s) {
        int i = 0, j = 0, max = 0;
        // char -> char在字符串上的下标
        Map<Character, Integer> map = new HashMap<>();
        while (j < s.length()) {
            char curr = s.charAt(j);
            if (map.containsKey(curr)) {
                // 这里重复元素可能是跟左指针左侧元素重复，因为并没有从map中移除滑动窗口外的元素
                // 左指针移到重复字符下一个元素
                i = Math.max(i, map.get(curr) + 1);
            }
            // 新的重复元素覆盖之前重复元素
            map.put(curr, j);
            max = Math.max(max, j - i + 1);
            // 右指针往右移一位
            j ++;
        }
        return max;
    }



    @Test
    public void test() {
        Assert.assertTrue(lengthOfLongestSubstring("abcabcbb") == 3);
        Assert.assertTrue(lengthOfLongestSubstring("bbbbb") == 1);
        Assert.assertTrue(lengthOfLongestSubstring("pwwkew") == 3);
        Assert.assertTrue(lengthOfLongestSubstring(" ") == 1);
    }

    @Test
    public void test2() {
        Assert.assertTrue(lengthOfLongestSubstring2("abcabcbb") == 3);
        Assert.assertTrue(lengthOfLongestSubstring2("bbbbb") == 1);
        Assert.assertTrue(lengthOfLongestSubstring2("pwwkew") == 3);
        Assert.assertTrue(lengthOfLongestSubstring2(" ") == 1);
    }

    @Test
    public void test3() {
        Assert.assertTrue(lengthOfLongestSubstring3("abcabcbb") == 3);
        Assert.assertTrue(lengthOfLongestSubstring3("bbbbb") == 1);
        Assert.assertTrue(lengthOfLongestSubstring3("pwwkew") == 3);
        Assert.assertTrue(lengthOfLongestSubstring3(" ") == 1);
        Assert.assertTrue(lengthOfLongestSubstring3("abba") == 2);
    }
}
