package leetcode.algorithms;

//Given an integer array nums, return the length of the longest strictly increas
//ing subsequence.
//
// A subsequence is a sequence that can be derived from an array by deleting som
//e or no elements without changing the order of the remaining elements. For examp
//le, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
//
//
// Example 1:
//
//
//Input: nums = [10,9,2,5,3,7,101,18]
//Output: 4
//Explanation: The longest increasing subsequence is [2,3,7,101], therefore the
//length is 4.
//
//
// Example 2:
//
//
//Input: nums = [0,1,0,3,2,3]
//Output: 4
//
//
// Example 3:
//
//
//Input: nums = [7,7,7,7,7,7,7]
//Output: 1
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 2500
// -104 <= nums[i] <= 104
//
//
//
// Follow up:
//
//
// Could you come up with the O(n2) solution?
// Could you improve it to O(n log(n)) time complexity?
//
// Related Topics Binary Search Dynamic Programming
// 👍 7088 👎 160

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class _0300LongestIncreasingSubsequence {

    /**
     * 解法1：动态规划
     * f(i) 是索引 i 所在的数字结尾的最长上升子序列的长度，注意 nums[i] 必须被选取。
     *  f(i) = { Max(f(j) + 1), 0 <= j < i, nums[i] > nums[j]    // 遍历0<=j<i的f(j)值，在满足nums[i] > nums[j]的所有情况中选择最大的f(j)， 加1即为f(i)的值
     *         { 1,             0 <= j < i, nums[i] <= nums[j]   // 遍历0<=j<i的f(j)值，若没有满足nums[i] > nums[j]的情况，则f(i)为1
     *
     * p.s 子序列不要求连续，保证相对顺序即可
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        return IntStream.of(dp).max().getAsInt();
    }


    @Test
    public void test() {
        Assert.assertEquals(4, lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
        Assert.assertEquals(4, lengthOfLIS(new int[]{0,1,0,3,2,3}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{7,7,7,7,7,7,7}));
        Assert.assertEquals(1, lengthOfLIS(new int[]{7}));
    }
}
