package leetcode.algorithms;

/*
 * @lc app=leetcode id=66 lang=java
 *
 * [66] Plus One
 *
 * https://leetcode.com/problems/plus-one/description/
 *
 * algorithms
 * Easy (40.85%)
 * Total Accepted:    374.3K
 * Total Submissions: 913.6K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty array of digits representing a non-negative integer, plus
 * one to the integer.
 *
 * The digits are stored such that the most significant digit is at the head of
 * the list, and each element in the array contain a single digit.
 *
 * You may assume the integer does not contain any leading zero, except the
 * number 0 itself.
 *
 * Example 1:
 *
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 *
 *
 * Example 2:
 *
 *
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
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
public class _0066PlusOne {

    public int[] plusOne(int[] digits) {
        // 进位
        boolean carry = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            int digit = digits[i];
            if (i == digits.length - 1) {
                digit += 1;
            }
            // 如果低位有进位则需加1
            digit += carry? 1: 0;
            // 判断是否需要进位
            carry = digit > 9? true: false;
            digits[i] = digit > 9? digit - 10: digit;
        }
        if (carry) {
            // 最高位有进位
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        } else {
            return digits;
        }
    }

    @Test
    public void test() {
        Assert.assertEquals(plusOne(new int[]{1, 2, 3})[0], 1);
        Assert.assertEquals(plusOne(new int[]{1, 2, 3})[1], 2);
        Assert.assertEquals(plusOne(new int[]{1, 2, 3})[2], 4);


        Assert.assertEquals(plusOne(new int[]{9, 9, 9})[0], 1);
        Assert.assertEquals(plusOne(new int[]{9, 9, 9})[1], 0);
        Assert.assertEquals(plusOne(new int[]{9, 9, 9})[2], 0);
        Assert.assertEquals(plusOne(new int[]{9, 9, 9})[3], 0);

        Assert.assertEquals(plusOne(new int[]{0})[0], 1);
    }
}
