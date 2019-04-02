package leetcode.algorithms;

/*
 * @lc app=leetcode id=7 lang=java
 *
 * [7] Reverse Integer
 *
 * https://leetcode.com/problems/reverse-integer/description/
 *
 * algorithms
 * Easy (25.22%)
 * Total Accepted:    644.3K
 * Total Submissions: 2.6M
 * Testcase Example:  '123'
 *
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Example 1:
 *
 *
 * Input: 123
 * Output: 321
 *
 *
 * Example 2:
 *
 *
 * Input: -123
 * Output: -321
 *
 *
 * Example 3:
 *
 *
 * Input: 120
 * Output: 21
 *
 *
 * Note:
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−2^31,  2^31 − 1]. For the purpose
 * of this problem, assume that your function returns 0 when the reversed
 * integer overflows.
 *
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc: No.7 Reverse Integer
 * 先用while循环不断取余获取int的每一位，然后每一位乘10的n次方进行反转
 * 注意：反转后的值可能超过int，需要用long型
 *
 * @author zhanglinwei02
 * @date 2019-04-01
 */
public class _007ReverseInteger {

    public int reverse(int x) {
        boolean positive = x >= 0? true: false;

        List<Integer> nums = new ArrayList<>();
        int n = Math.abs(x);
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }

        long result = 0;
        for (int i = 0; i < nums.size(); i++) {
            result += nums.get(i) * pow10(nums.size() - i - 1);
        }

        long real_result = positive? result: -result;
        if (real_result > Integer.MAX_VALUE || real_result < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) real_result;
    }

    private long pow10(int n) {
        long res = 1;
        for (int i = 0; i < n; i++) {
            res *= 10;
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(reverse(153));
        System.out.println(reverse(-153));
        System.out.println(reverse(120));
        System.out.println(reverse(1534236469));
    }
}
