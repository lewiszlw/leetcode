package leetcode.algorithms;

/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 *
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
 *
 * algorithms
 * Easy (39.99%)
 * Total Accepted:    556.8K
 * Total Submissions: 1.4M
 * Testcase Example:  '[1,1,2]'
 *
 * Given a sorted array nums, remove the duplicates in-place such that each
 * element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying
 * the input array in-place with O(1) extra memory.
 *
 * Example 1:
 *
 *
 * Given nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively.
 *
 * It doesn't matter what you leave beyond the returned length.
 *
 * Example 2:
 *
 *
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * Your function should return length = 5, with the first five elements of nums
 * being modified to 0, 1, 2, 3, and 4 respectively.
 *
 * It doesn't matter what values are set beyond the returned length.
 *
 *
 * Clarification:
 *
 * Confused why the returned value is an integer but your answer is an array?
 *
 * Note that the input array is passed in by reference, which means
 * modification to the input array will be known to the caller as well.
 *
 * Internally you can think of this:
 *
 *
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeDuplicates(nums);
 *
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len
 * elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-08
 */
public class _0026RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int len = 1;
        // 暂存值
        int tmp = nums[0];
        // 统计tmp变化次数，变化一次length加1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != tmp) {
                // 将非重复数组值挪到前面
                nums[len] = nums[i];
                len ++;
                tmp = nums[i];
            }
        }

        return len;
    }


    @Test
    public void test() {
        Assert.assertTrue(removeDuplicates(new int[]{1,1,2}) == 2);
        Assert.assertTrue(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}) == 5);

        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
