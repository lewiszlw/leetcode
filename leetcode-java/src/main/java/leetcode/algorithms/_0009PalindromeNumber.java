package leetcode.algorithms;

/*
 * @lc app=leetcode id=9 lang=java
 *
 * [9] Palindrome Number
 *
 * https://leetcode.com/problems/palindrome-number/description/
 *
 * algorithms
 * Easy (42.47%)
 * Total Accepted:    539.8K
 * Total Submissions: 1.3M
 * Testcase Example:  '121'
 *
 * Determine whether an integer is a palindrome. An integer is a palindrome
 * when it reads the same backward as forward.
 *
 * Example 1:
 *
 *
 * Input: 121
 * Output: true
 *
 *
 * Example 2:
 *
 *
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it
 * becomes 121-. Therefore it is not a palindrome.
 *
 *
 * Example 3:
 *
 *
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a
 * palindrome.
 *
 *
 * Follow up:
 *
 * Coud you solve it without converting the integer to a string?
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Desc:
 * 先用while循环不断取余获取int的每一位，然后双指针一个起始一个末尾开始遍历，
 * 直到指针相碰，存在不相等的则不是回文数
 *
 * @author zhanglinwei02
 * @date 2019-04-01
 */
public class _009PalindromeNumber {

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }

        List<Integer> nums = new ArrayList<>();
        while (x > 0) {
            nums.add(x % 10);
            x /= 10;
        }

        int i = 0, j = nums.size() - 1;
        boolean isPalindrome = true;
        while (i < j) {
            if (Objects.equals(nums.get(i),nums.get(j))) {
                i++;
                j--;
            } else {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    @Test
    public void test() {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
    }
}
