package leetcode.algorithms;

//Given an unsorted array of integers nums, return the length of the longest con
//secutive elements sequence.
//
// You must write an algorithm that runs in O(n) time.
//
//
// Example 1:
//
//
//Input: nums = [100,4,200,1,3,2]
//Output: 4
//Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Theref
//ore its length is 4.
//
//
// Example 2:
//
//
//Input: nums = [0,3,7,2,5,8,4,6,0,1]
//Output: 9
//
//
//
// Constraints:
//
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
//
// Related Topics Array Union Find
// 👍 5245 👎 258

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _0128LongestConsecutiveSequence {

    /**
     * 解法1：哈希表
     * 首先将所有数存放到哈希表里，然后遍历所有数x
     * 1.判断x是否为起点元素，即x-1存在，是则下一步，不是则继续遍历
     * 2.依次判断x+1, x+2, x+3, ...是否存在，计算序列长度
     *
     * 时间复杂度O(n)
     * 外层循环需要 O(n)O(n) 的时间复杂度，只有当一个数是连续序列的第一个数的情况下才会进入内层循环，
     * 然后在内层循环中匹配连续序列中的数，因此数组中的每个数只会进入内层循环一次。
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }

        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            if (map.get(x - 1) != null) {
                // x非起点
                continue;
            }

            // 不断尝试 x + 1, x + 2, x + 3, ... 是否在数组中
            int len = 1;
            while (map.get(x + 1) != null) {
                len ++;
                x ++;
            }
            longest = Math.max(longest, len);
        }
        return longest;
    }


    /**
     * 解法2：动态规划
     * 哈希表主要存放 序列端点值->序列长度 的映射
     *
     * 如nums         1 2 3 4 6 7 8 10
     *               | | | | | | | |
     * 在哈希表映射    4 x x 4 3 x 3 1 (x代表不重要)
     */
    public int longestConsecutive2(int[] nums) {
        int longest = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                // 左边为端点所处序列长度
                int left = map.getOrDefault(nums[i] - 1, 0);
                // 右边为端点所处序列长度
                int right = map.getOrDefault(nums[i] + 1, 0);

                int len = left + 1 + right;
                if (len > longest) {
                    longest = len;
                }

                // 重新更新端点所处序列长度
                map.put(nums[i] - left, len);
                map.put(nums[i] + right, len);
                map.put(nums[i], len);   // 这个值不重要，因为非端点(left,right不为0)
            }
        }
        return longest;
    }

    @Test
    public void test() {
        Assert.assertEquals(0, longestConsecutive(new int[]{}));
        Assert.assertEquals(4, longestConsecutive(new int[]{100,4,200,1,3,2}));
        Assert.assertEquals(9, longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, longestConsecutive2(new int[]{}));
        Assert.assertEquals(4, longestConsecutive2(new int[]{100,4,200,1,3,2}));
        Assert.assertEquals(9, longestConsecutive2(new int[]{0,3,7,2,5,8,4,6,0,1}));
    }
}
