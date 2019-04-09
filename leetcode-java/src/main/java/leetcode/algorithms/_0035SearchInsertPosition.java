package leetcode.algorithms;

/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 *
 * https://leetcode.com/problems/search-insert-position/description/
 *
 * algorithms
 * Easy (40.57%)
 * Total Accepted:    380.5K
 * Total Submissions: 936.6K
 * Testcase Example:  '[1,3,5,6]\n5'
 *
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 *
 *
 * Example 2:
 *
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 *
 *
 * Example 3:
 *
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 *
 *
 * Example 4:
 *
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 * 利用二分查找思想，无论最后是否查到，左指针都会在insertPosition附近，然后进行判断即可
 *
 * @author zhanglinwei02
 * @date 2019-04-09
 */
public class _0035SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        if (nums[left] < target) {
            return left + 1;
        }
        // 防止未经过while循环情况
        if (nums[left] == target) {
            return left;
        }
        if (nums[left] > target) {
            return left;
        }
        return -1;
    }


    @Test
    public void test() {
        Assert.assertTrue(searchInsert(new int[]{1,3,5,6}, 5) == 2);
        Assert.assertTrue(searchInsert(new int[]{1,3,5,6}, 2) == 1);
        Assert.assertTrue(searchInsert(new int[]{1,3,5,6}, 7) == 4);
        Assert.assertTrue(searchInsert(new int[]{1,3,5,6}, 0) == 0);
        Assert.assertTrue(searchInsert(new int[]{1}, 0) == 0);
        Assert.assertTrue(searchInsert(new int[]{1}, 1) == 0);
        Assert.assertTrue(searchInsert(new int[]{1}, 2) == 1);
    }

}
