package leetcode.algorithms;

/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 *
 * https://leetcode.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (30.90%)
 * Total Accepted:    350.4K
 * Total Submissions: 1.1M
 * Testcase Example:  '4'
 *
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a
 * non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 *
 * Example 1:
 *
 *
 * Input: 4
 * Output: 2
 *
 *
 * Example 2:
 *
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 * 思路：循环计算x的一半的一半的一半的...的平方，如果小于x则跳出循环，然后从此值开始找满足平方根所在区间
 * 注意：
 *  1.边界情况：0和1
 *  2.遍历过程中两个int相乘可能会超过int最大值
 *
 * @author zhanglinwei02
 * @date 2019-04-15
 */
public class _0069Sqrtx {

    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        double root = x / 2;
        while (true) {
            if (root * root <= x) {
                break;
            }
            root /= 2;
        }
        // i 可能等于 x
        for (long i = (int) root; i <= x; i++) {
            // (i + 1) * (i + 1) 可能超过int最大值
            if (i * i <= x && (i + 1) * (i + 1) > x) {
                return (int) i;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(mySqrt(1), 1);
        Assert.assertEquals(mySqrt(4), 2);
        Assert.assertEquals(mySqrt(8), 2);
        Assert.assertEquals(mySqrt(2147395600), 46340);
    }
}
