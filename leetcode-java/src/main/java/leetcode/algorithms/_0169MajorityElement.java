package leetcode.algorithms;

/*
 * @lc app=leetcode id=169 lang=java
 *
 * [169] Majority Element
 *
 * https://leetcode.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (51.86%)
 * Total Accepted:    368K
 * Total Submissions: 705.9K
 * Testcase Example:  '[3,2,3]'
 *
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 *
 * Example 1:
 *
 *
 * Input: [3,2,3]
 * Output: 3
 *
 * Example 2:
 *
 *
 * Input: [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-18
 */
public class _0169MajorityElement {

    /**
     * 解法1
     * 暴力遍历数组统计出现次数
     */
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == null) {
                map.put(nums[i], 1);
            } else {
                Integer count = map.get(nums[i]);
                count ++;
                map.put(nums[i], count);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return 0;
    }

    @Test
    public void test() {
        Assert.assertTrue(majorityElement(new int[]{3,2,3}) == 3);
        Assert.assertTrue(majorityElement(new int[]{2,2,1,1,1,2,2}) == 2);
    }
}
