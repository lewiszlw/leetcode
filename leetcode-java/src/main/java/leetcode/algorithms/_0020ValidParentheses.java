package leetcode.algorithms;

/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 *
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * algorithms
 * Easy (36.11%)
 * Total Accepted:    547.3K
 * Total Submissions: 1.5M
 * Testcase Example:  '"()"'
 *
 * Given a string containing just the characters '(', ')', '{', '}', '[' and
 * ']', determine if the input string is valid.
 *
 * An input string is valid if:
 *
 *
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 *
 * Note that an empty string is also considered valid.
 *
 * Example 1:
 *
 *
 * Input: "()"
 * Output: true
 *
 *
 * Example 2:
 *
 *
 * Input: "()[]{}"
 * Output: true
 *
 *
 * Example 3:
 *
 *
 * Input: "(]"
 * Output: false
 *
 *
 * Example 4:
 *
 *
 * Input: "([)]"
 * Output: false
 *
 *
 * Example 5:
 *
 *
 * Input: "{[]}"
 * Output: true
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * 创建list（类似于栈的作用）用于存放字符括号，遍历字符串s，如果list末尾元素（栈顶）和当前遍历位置字符是成对括号，
 * 则移除list末尾元素（弹栈），否则将当前遍历位置字符放到list中（压栈），最后查看list是否为空
 *
 * @author zhanglinwei02
 * @date 2019-04-08
 */
public class _0020ValidParentheses {

    public boolean isValid(String s) {
        List<Character> list = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (list.size() > 0 && pair(list.get(list.size() - 1), s.charAt(i))) {
                // 如果list末尾元素和当前字符成对，则移除list末尾元素
                list.remove(list.size() - 1);
            } else {
                list.add(s.charAt(i));
            }
        }
        return list.size() == 0;
    }

    /**
     * 是否成对括号
     */
    private boolean pair(char left, char right) {
        return left == '(' && right == ')' ||
                left == '[' && right == ']' ||
                left == '{' && right == '}';
    }


    @Test
    public void test() {
        Assert.assertTrue(isValid("()[]{}"));
        Assert.assertTrue(isValid("{[]}"));
        Assert.assertFalse(isValid("([)]"));
    }

}
