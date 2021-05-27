package leetcode.algorithms;

//Given an unsorted array of integers nums, return the length of the longest con
//tinuous increasing subsequence (i.e. subarray). The subsequence must be strictly
// increasing.
//
// A continuous increasing subsequence is defined by two indices l and r (l < r)
// such that it is [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] and for each
//l <= i < r, nums[i] < nums[i + 1].
//
//
// Example 1:
//
//
//Input: nums = [1,3,5,4,7]
//Output: 3
//Explanation: The longest continuous increasing subsequence is [1,3,5] with len
//gth 3.
//Even though [1,3,5,7] is an increasing subsequence, it is not continuous as el
//ements 5 and 7 are separated by element
//4.
//
//
// Example 2:
//
//
//Input: nums = [2,2,2,2,2]
//Output: 1
//Explanation: The longest continuous increasing subsequence is [2] with length
//1. Note that it must be strictly
//increasing.
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 104
// -109 <= nums[i] <= 109
//
// Related Topics Array
// 👍 1197 👎 145

import org.junit.Assert;
import org.junit.Test;

public class _0674LongestContinuousIncreasingSubsequence {

    /**
     * 解法：双指针
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = 1;
        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[right] > nums[right - 1]) {
                right ++;
                continue;
            } else {
                max = Math.max(max, right - left);
                left = right;  // 左指针指向下一个连续递增子序列的起始位
                right ++;
            }
        }
        // 正常right=nums.length退出情况
        max = Math.max(max, right - left);
        return max;
    }


    @Test
    public void test() {
        Assert.assertEquals(3, findLengthOfLCIS(new int[]{1,3,5,4,7}));
        Assert.assertEquals(1, findLengthOfLCIS(new int[]{2,2,2,2,2}));
        Assert.assertEquals(4, findLengthOfLCIS(new int[]{1,3,5,7}));
    }
}
