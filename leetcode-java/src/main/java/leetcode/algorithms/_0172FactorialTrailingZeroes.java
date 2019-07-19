package leetcode.algorithms;

/*
 * @lc app=leetcode id=172 lang=java
 *
 * [172] Factorial Trailing Zeroes
 *
 * https://leetcode.com/problems/factorial-trailing-zeroes/description/
 *
 * algorithms
 * Easy (37.29%)
 * Total Accepted:    152.5K
 * Total Submissions: 408.8K
 * Testcase Example:  '3'
 *
 * Given an integer n, return the number of trailing zeroes in n!.
 *
 * Example 1:
 *
 *
 * Input: 3
 * Output: 0
 * Explanation: 3! = 6, no trailing zero.
 *
 * Example 2:
 *
 *
 * Input: 5
 * Output: 1
 * Explanation: 5! = 120, one trailing zero.
 *
 * Note: Your solution should be in logarithmic time complexity.
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-22
 */
public class _0172FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        if (n < 0) {
            return -1;
        }
        int fac = factorial(n);
        int trailingZeroes = 0;
        while (fac > 0) {
            int remain = fac % 10;
            if (remain == 0) {
                trailingZeroes ++;
            }
            fac /= 10;
        }
        return trailingZeroes;
    }

    private int factorial(int n) {
        if (n == 0) {
            // 0的阶乘是1
            return 1;
        }
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    @Test
    public void test() {
//        Assert.assertTrue(trailingZeroes(3) == 0);
//        Assert.assertTrue(trailingZeroes(5) == 1);
//        Assert.assertTrue(trailingZeroes(7) == 1);
        Assert.assertTrue(trailingZeroes(13) == 2);
    }
}
