package leetcode.algorithms;

/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 *
 * https://leetcode.com/problems/maximum-subarray/description/
 *
 * algorithms
 * Easy (43.07%)
 * Total Accepted:    496.3K
 * Total Submissions: 1.1M
 * Testcase Example:  '[-2,1,-3,4,-1,2,1,-5,4]'
 *
 * Given an integer array nums, find the contiguous subarray (containing at
 * least one number) which has the largest sum and return its sum.
 *
 * Example:
 *
 *
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 *
 *
 * Follow up:
 *
 * If you have figured out the O(n) solution, try coding another solution using
 * the divide and conquer approach, which is more subtle.
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-09
 */
public class _0053MaximumSubarray {

    /**
     * 解法1
     * 分治法：求每个子区间（即子问题）内的最大子数组，然后依次比较，得出最大连续子数组和
     */
    public int maxSubArray(int[] nums) {
        return maxSubArray(nums, 0, nums.length - 1);
    }

    private int maxSubArray(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = (left + right) / 2;
        // 左半部分最大子数组和
        int leftMaxSum = maxSubArray(nums, left, mid);
        // 右半部分最大子数组和
        int rightMaxSum = maxSubArray(nums, mid + 1, right);

        // 中间部分最大子数组和
        // 计算mid右边最大和
        // 使用Integer.MIN_VALUE，防止最大子数组和小于0
        int rightBoundSum = 0, rightMaxBoundSum = Integer.MIN_VALUE;
        for (int i = mid; i <= right; i++) {
            rightBoundSum += nums[i];
            if (rightBoundSum > rightMaxBoundSum) {
                rightMaxBoundSum = rightBoundSum;
            }
        }
        // 计算mid左边最大和
        int leftBoundSum = 0, leftMaxBoundSum = Integer.MIN_VALUE;
        for (int i = mid - 1; i >= left; i--) {
            leftBoundSum += nums[i];
            if (leftBoundSum > leftMaxBoundSum) {
                leftMaxBoundSum = leftBoundSum;
            }
        }
        // 中间部分最大子数组和
        // 如果值为Integer.MIN_VALUE，说明未遍历，即左边或右边为空
        int midMaxSum;
        if (rightMaxBoundSum < 0 || leftMaxBoundSum < 0) {
            // 如果有一边和小于0，或两边和都小于0，取大者
            midMaxSum = Math.max(rightMaxBoundSum, leftMaxBoundSum);
        } else {
            // 都是大于0，取两者之和
            midMaxSum = rightMaxBoundSum + leftMaxBoundSum;
        }
        return leftMaxSum > rightMaxSum ? Math.max(leftMaxSum, midMaxSum) : Math.max(rightMaxSum, midMaxSum);
    }

    @Test
    public void test() {
        Assert.assertTrue(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}) == 6);
        Assert.assertTrue(maxSubArray(new int[]{-2,-1}) == -1);
        Assert.assertTrue(maxSubArray(new int[]{8,-19,5,-4,20}) == 21);
        Assert.assertTrue(maxSubArray(new int[]{1,2}) == 3);
    }

}
