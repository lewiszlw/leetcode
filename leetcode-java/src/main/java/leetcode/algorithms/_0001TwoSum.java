package leetcode.algorithms;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (42.75%)
 * Total Accepted:    1.6M
 * Total Submissions: 3.8M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers, return indices of the two numbers such that they
 * add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 *
 * Example:
 *
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1].
 *
 */

/**
 * Desc:
 * 第一种解决办法（自己）：
 * 先复制一份数组，将其中一个数组排序，两个指针一个从起始出发，一个从末尾出发，
 * 如果和大于目标值，末尾指针向前移，如果和小于目标值，起始指针向后移，直至和为目标值，
 * 然后拿值去另一个数组找到索引值
 *
 * 第二种解决办法（参考别人的）:
 * 利用HashMap结构，key为数组值，value为对应数组索引。遍历数组，查看target-nums[i]
 * 是否在map中，若不在，则put(nums[i], i)到HashMap中，若在，则返回结果
 *
 * @author zhanglinwei02
 * @date 2019-03-28
 */
public class _0001TwoSum {

    /**
     * 第一种解法
     */
    public int[] twoSum(int[] nums, int target) {
        int[] copyNums = nums.clone();

        Arrays.sort(nums);

        int i = 0, j = nums.length - 1;
        while (i < j) {
            if ((nums[i] + nums[j]) < target) {
                i++;
                continue;
            }
            if ((nums[i] + nums[j]) > target) {
                j--;
                continue;
            }
            if ((nums[i] + nums[j]) == target) {
                break;
            }
        }
        return indexOf(copyNums, nums[i], nums[j]);
    }

    public int[] indexOf(int[] nums, int target1, int target2) {
        int index1 = -1, index2 = -1;
        boolean index1Set = false, index2Set = false;
        for (int i = 0; i < nums.length; i++) {
            // 索引index1未设置过 && 数组值等于目标值1 && 数组索引不等于index2
            if (!index1Set && nums[i] == target1 && i != index2) {
                index1 = i;
                index1Set = true;
            }
            if (!index2Set && nums[i] == target2 && i != index1) {
                index2 = i;
                index2Set = true;
            }
        }
        int[] result = {index1, index2};
        return result;
    }


    /**
     * 第二种解法
     */
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    @Test
    public void test() {
//        int[] nums = {3, 2, 4};
//        int[] nums = {3, 3};
        int[] nums = {3, 2, 3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
