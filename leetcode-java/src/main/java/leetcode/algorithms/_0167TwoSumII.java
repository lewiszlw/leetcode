package leetcode.algorithms;

/*
 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input array is sorted
 *
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 *
 * algorithms
 * Easy (49.68%)
 * Total Accepted:    231.4K
 * Total Submissions: 463.7K
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may
 * not use the same element twice.
 *
 *
 * Example:
 *
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
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
public class _0167TwoSumII {

    /**
     * 解法1
     * 先二分查找target一半的位置（或附近位置），然后从这里开始往两边遍历，
     * 利用HashMap存储遍历过的值，如果target减去当前遍历值在HashMap中则返回
     */
    public int[] twoSum(int[] numbers, int target) {
        int half = target / 2;
        int halfIndex = binarySearch(numbers, half);
        int i = halfIndex;
        int j = halfIndex + 1;
        // key为数组值、value为索引
        Map<Integer, Integer> map= new HashMap<>();
        // 或 只要有一个满足则继续遍历
        while (i >= 0 || j < numbers.length) {
            if (i >= 0) {
                Integer index1 = map.get(target - numbers[i]);
                if (index1 != null) {
                    return index1 > i? new int[]{i + 1, index1 + 1}: new int[]{index1 + 1, i + 1};
                }
                map.put(numbers[i], i);
            }
            if (j < numbers.length) {
                Integer index2 = map.get(target - numbers[j]);
                if (index2 != null) {
                    return index2 > j? new int[]{j + 1, index2 + 1}: new int[]{index2 + 1, j + 1};
                }
                map.put(numbers[j], j);
            }
            i --;
            j ++;
        }
        return new int[]{-1, -1};
    }

    /**
     * 找出targe位置或附近位置
     */
    private int binarySearch(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (numbers[mid] == target) {
                return mid;
            }
            if (numbers[mid] < target) {
                j = mid - 1;
            }
            if (numbers[mid] > target) {
                i = mid + 1;
            }
        }
        return i;
    }


    /**
     * 解法2
     * 头尾指针向中间遍历
     */
    public int[] twoSum2(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        Map<Integer, Integer> map= new HashMap<>(1000);
        // 注意加=号，不然可能存在没有遍历到的值
        while (i <= j) {
            Integer index1 = map.get(target - numbers[i]);
            if (index1 != null) {
                return index1 > i? new int[]{i + 1, index1 + 1}: new int[]{index1 + 1, i + 1};
            }
            map.put(numbers[i], i);
            if (i != j) {
                Integer index2 = map.get(target - numbers[j]);
                if (index2 != null) {
                    return index2 > j? new int[]{j + 1, index2 + 1}: new int[]{index2 + 1, j + 1};
                }
                map.put(numbers[j], j);
            }
            i ++;
            j --;
        }
        return new int[]{-1, -1};
    }



    @Test
    public void test() {
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 9);
        Assert.assertTrue(result[0] == 1);
        Assert.assertTrue(result[1] == 2);
    }

    @Test
    public void test2() {
        int[] result = twoSum2(new int[]{2, 7, 11, 15}, 9);
        Assert.assertTrue(result[0] == 1);
        Assert.assertTrue(result[1] == 2);

        int[] result2 = twoSum2(new int[]{5, 25, 75}, 100);
        Assert.assertTrue(result2[0] == 2);
        Assert.assertTrue(result2[1] == 3);
    }
}
