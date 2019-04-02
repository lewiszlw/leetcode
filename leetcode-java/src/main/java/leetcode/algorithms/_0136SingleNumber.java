package leetcode.algorithms;

import org.junit.Test;

/*
 * @lc app=leetcode id=136 lang=java
 *
 * [136] Single Number
 *
 * https://leetcode.com/problems/single-number/description/
 *
 * algorithms
 * Easy (59.36%)
 * Total Accepted:    434.3K
 * Total Submissions: 731K
 * Testcase Example:  '[2,2,1]'
 *
 * Given a non-empty array of integers, every element appears twice except for
 * one. Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 *
 * Example 1:
 *
 *
 * Input: [2,2,1]
 * Output: 1
 *
 *
 * Example 2:
 *
 *
 * Input: [4,1,2,1,2]
 * Output: 4
 *
 *
 */


/**
 * Desc:
 * 136 SingleNumber
 * 解决方法：异或。两个相同的数异或为0，0与任何数异或为该数不变，异或满足交换律、结合律。
 *
 * @author zhanglinwei02
 * @date 2019-03-27
 */
public class _0136SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {1,2,2,4,4};
        System.out.println(singleNumber(nums));
    }

}
