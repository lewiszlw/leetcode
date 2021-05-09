package leetcode.algorithms;

//There is an integer array nums sorted in ascending order (with distinct values
//).
//
// Prior to being passed to your function, nums is rotated at an unknown pivot i
//ndex k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+
//1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example,
//[0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].
//
// Given the array nums after the rotation and an integer target, return the ind
//ex of target if it is in nums, or -1 if it is not in nums.
//
// You must write an algorithm with O(log n) runtime complexity.
//
//
// Example 1:
// Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
// Example 2:
// Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1
// Example 3:
// Input: nums = [1], target = 0
//Output: -1
//
//
// Constraints:
//
//
// 1 <= nums.length <= 5000
// -104 <= nums[i] <= 104
// All values of nums are unique.
// nums is guaranteed to be rotated at some pivot.
// -104 <= target <= 104
//
// Related Topics Array Binary Search
// 👍 7626 👎 669

import org.junit.Assert;
import org.junit.Test;

public class _0033SearchInRotatedSortedArray {

    /**
     * 解法：二分搜索变形
     * 不断从数组中间切分数组，使数组变为一个为纯升序数组、一个为两段升序的数组（即旋转数组），
     * 纯升序数组可以利用二分搜索，而两段升序的数组（即旋转数组）可以继续从中间切分。
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target == nums[mid]) {
                return mid;
            }

            if (nums[mid] >= nums[0]) {
                // mid 在两段升序的数组左段

                // 二分搜索[left, mid]
                int targetIndex = bSearch(nums, target, left, mid);
                if (targetIndex != -1) {
                    return targetIndex;
                }

                // 继续切分[mid + 1, right]
                left = mid + 1;

            } else {
                // mid 在两段升序的数组右段

                // 二分搜索[mid, right]
                int targetIndex = bSearch(nums, target, mid, right);
                if (targetIndex != -1) {
                    return targetIndex;
                }

                // 继续切分[left, mid - 1]
                right = mid - 1;
            }
        }
        return -1;
    }

    private int bSearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            }
            if (target == nums[mid]) {
                return mid;
            }
            if (target > nums[mid]) {
                left = mid + 1;
            }
        }
        return -1;
    }

    @Test
    public void test() {
        Assert.assertEquals(4, search(new int[]{4,5,6,7,0,1,2}, 0));
        Assert.assertEquals(-1, search(new int[]{4,5,6,7,0,1,2}, 3));
        Assert.assertEquals(0, search(new int[]{4,5,6,7,0,1,2}, 4));
        Assert.assertEquals(6, search(new int[]{4,5,6,7,0,1,2}, 2));
        Assert.assertEquals(-1, search(new int[]{1}, 0));
    }
}
