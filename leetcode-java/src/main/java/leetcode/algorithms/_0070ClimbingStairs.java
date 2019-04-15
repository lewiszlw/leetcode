package leetcode.algorithms;

/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 *
 * https://leetcode.com/problems/climbing-stairs/description/
 *
 * algorithms
 * Easy (43.72%)
 * Total Accepted:    382.5K
 * Total Submissions: 871.9K
 * Testcase Example:  '2'
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 *
 * Note: Given n will be a positive integer.
 *
 * Example 1:
 *
 *
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 *
 *
 * Example 2:
 *
 *
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
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
public class _0070ClimbingStairs {

    /**
     * 解法1：递归
     * n阶爬楼梯方式 = 最后一步爬1步的前n-1阶爬楼梯方式 + 最后一步爬2阶的前n-2阶爬楼梯方式
     * 即，爬到n阶分为两种情况：
     *   1.最后一步为1阶，有climbStairs(n-1)种方式
     *   2.最后一步为2阶，有climbStairs(n-2)种方式
     * n >= 44会超时
     */
    public int climbStairs1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }


    /**
     * 解法2：动态规划
     * f(n) = f(n-1) + f(n-2)
     * 使用数组记录从n=1开始的阶数及其对应的方式数，这样高阶楼梯计算直接查表即可
     */
    public int climbStairs2(int n) {
        // 索引为阶数，值为爬楼梯方式数
        int[] ways = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1) {
                ways[i] = 1;
            } else if (i == 2) {
                ways[i] = 2;
            } else {
                // 高阶直接查表即可
                ways[i] = ways[i - 1] + ways[i - 2];
            }
        }
        return ways[n];
    }

    @Test
    public void test1() {
        Assert.assertEquals(climbStairs1(2), 2);
        Assert.assertEquals(climbStairs1(3), 3);
        Assert.assertEquals(climbStairs1(4), 5);
    }

    @Test
    public void test2() {
        Assert.assertEquals(climbStairs2(2), 2);
        Assert.assertEquals(climbStairs2(3), 3);
        Assert.assertEquals(climbStairs2(4), 5);
    }
}
