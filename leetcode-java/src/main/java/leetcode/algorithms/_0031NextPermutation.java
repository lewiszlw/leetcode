package leetcode.algorithms;

//Implement next permutation, which rearranges numbers into the lexicographicall
//y next greater permutation of numbers.
//
// If such an arrangement is not possible, it must rearrange it as the lowest po
//ssible order (i.e., sorted in ascending order).
//
// The replacement must be in place and use only constant extra memory.
//
//
// Example 1:
// Input: nums = [1,2,3]
//Output: [1,3,2]
// Example 2:
// Input: nums = [3,2,1]
//Output: [1,2,3]
// Example 3:
// Input: nums = [1,1,5]
//Output: [1,5,1]
// Example 4:
// Input: nums = [1]
//Output: [1]
//
//
// Constraints:
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
//
// Related Topics Array
// 👍 5477 👎 1870

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class _0031NextPermutation {

    /**
     * 解法：将排列看成一个数，包含个位十位千位...
     * 从十位开始往前遍历，记做当前位，查找查找当前位后面（即低位）是否有大于当前位的数，
     * 如果有，则从大于当前位的所有数中选取最小的那个，与当前位交换位置，然后升序排列
     * 当前位后面（即低位）的所有数
     */
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) {
            return;
        }

        // 从倒数第二个数往前遍历
        for (int i = nums.length - 2; i >= 0; i--) {

            int target = nums[i];

            // 查找当前位后面（即低位）大于当前位的所有数中最小的数
            int indexOfMinNumGreaterThanTarget = findMinNumGreaterThanTarget(nums, target, i + 1, nums.length - 1);

            if (indexOfMinNumGreaterThanTarget != -1) {
                // 交换i和indexOfMinNumGreaterThanTarget两个数
                int tmp = nums[i];
                nums[i] = nums[indexOfMinNumGreaterThanTarget];
                nums[indexOfMinNumGreaterThanTarget] = tmp;
                // 升序排列nums[i+1]到nums[nums.length - 1]之间的数
                Arrays.sort(nums, i + 1, nums.length);
                return;
            }

            // nums[i+1]到nums[nums.length - 1]中没有比target大的数
        }

        // 程序执行到这里说明整个nums是降序排列，直接对nums进行升序排序即可
        Arrays.sort(nums);
    }

    // 从nums[left]-nums[right]中找到比target大的所有数中最小的那个数
    private int findMinNumGreaterThanTarget(int[] nums, int target, int left, int right) {
        // 0 <= nums[i] <= 100, 即nums[i]不会等于Integer.MAX_VALUE
        int minNumGreaterThanTarget = Integer.MAX_VALUE;
        int index = -1;

        for (int i = left; i < right + 1; i++) {
            if (nums[i] > target && nums[i] < minNumGreaterThanTarget) {
                minNumGreaterThanTarget = nums[i];
                index = i;
            }
        }
        return index;
    }


    @Test
    public void test() {
        int[] nums1 = new int[]{1,2,3};
        nextPermutation(nums1);
        Assert.assertArrayEquals(new int[]{1,3,2}, nums1);

        int[] nums2 = new int[]{3,2,1};
        nextPermutation(nums2);
        Assert.assertArrayEquals(new int[]{1,2,3}, nums2);

        int[] nums3 = new int[]{1,1,5};
        nextPermutation(nums3);
        Assert.assertArrayEquals(new int[]{1,5,1}, nums3);

        int[] nums4 = new int[]{1,3,2};
        nextPermutation(nums4);
        Assert.assertArrayEquals(new int[]{2,1,3}, nums4);
    }
}
