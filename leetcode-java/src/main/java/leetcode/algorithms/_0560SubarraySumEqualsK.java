package leetcode.algorithms;

//Given an array of integers nums and an integer k, return the total number of c
//ontinuous subarrays whose sum equals to k.
//
//
// Example 1:
// Input: nums = [1,1,1], k = 2
//Output: 2
// Example 2:
// Input: nums = [1,2,3], k = 3
//Output: 2
//
//
// Constraints:
//
//
// 1 <= nums.length <= 2 * 104
// -1000 <= nums[i] <= 1000
// -107 <= k <= 107
//
// Related Topics Array Hash Table
// 👍 7439 👎 254

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _0560SubarraySumEqualsK {

    /**
     * 解法1：前缀和
     * 注：双指针形成窗口 方法也可以，跟前缀和时间复杂度一样
     */
    public int subarraySum(int[] nums, int k) {
        // 以i结尾的前缀和
        int[] preSum = new int[nums.length];
        preSum[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        // 遍历前缀和数组，preSum[j] - preSum[i] 是 [i + 1, j] 的区间和
        int count = 0;
        for (int i = -1; i < preSum.length; i++) {   // 注意i从-1开始
            for (int j = i + 1; j < preSum.length; j++) {
                if (i == -1) {
                    if (preSum[j] == k) {
                        count ++;
                    }
                } else {
                    if (preSum[j] - preSum[i] == k) {
                        count ++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * 解法2：前缀和+哈希表优化
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        // 前缀和key -> 前缀和key出现次数
        Map<Integer, Integer> map = new HashMap<>();
        // nums第一个元素也可能被用到和为k的连续子数组中，如[1,1,1] k=2
        map.put(0, 1);

        int pre = 0;  // 以i结尾的前缀和
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (map.containsKey(pre - k)) {
                // 前面有符合的前缀和，差值为k
                count += map.get(pre - k);
            }
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }

        return count;
    }



    @Test
    public void test() {
        Assert.assertEquals(1, subarraySum(new int[]{1}, 1));
        Assert.assertEquals(2, subarraySum(new int[]{1,1,1}, 2));
        Assert.assertEquals(3, subarraySum(new int[]{1,1,1}, 1));
    }

    @Test
    public void test2() {
        Assert.assertEquals(1, subarraySum2(new int[]{1}, 1));
        Assert.assertEquals(2, subarraySum2(new int[]{1,1,1}, 2));
        Assert.assertEquals(3, subarraySum2(new int[]{1,1,1}, 1));
    }
}
