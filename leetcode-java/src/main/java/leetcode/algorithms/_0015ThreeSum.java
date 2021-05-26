package leetcode.algorithms;

//Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k
//]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
// Notice that the solution set must not contain duplicate triplets.
//
//
// Example 1:
// Input: nums = [-1,0,1,2,-1,-4]
//Output: [[-1,-1,2],[-1,0,1]]
// Example 2:
// Input: nums = []
//Output: []
// Example 3:
// Input: nums = [0]
//Output: []
//
//
// Constraints:
//
//
// 0 <= nums.length <= 3000
// -105 <= nums[i] <= 105
//
// Related Topics Array Two Pointers
// 👍 10718 👎 1083

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _0015ThreeSum {

    /**
     * 解法1：排序+三重循环
     * 注意跳过重复元素和剪枝
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        // 升序排列
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {

            int num1 = nums[i];
            // 重复元素直接跳过
            if (i != 0 && num1 == nums[i - 1]) {
                continue;
            }
            // 三数和一定大于0
            if (num1 > 0) {
                break;
            }

            for (int j = i + 1; j < nums.length; j++) {

                int num2 = nums[j];
                // 重复元素直接跳过
                if (j != i + 1 && num2 == nums[j - 1]) {
                    continue;
                }
                // 三数和一定大于0
                if (num1 + num2 > 0) {
                    break;
                }

                for (int k = j + 1; k < nums.length; k++) {

                    int num3 = nums[k];
                    // 重复元素直接跳过
                    if (k != j + 1 && num3 == nums[k - 1]) {
                        continue;
                    }
                    // 三数和一定大于0
                    if (num1 + num2 + num3 > 0) {
                        break;
                    }
                    if (num1 + num2 + num3 == 0) {
                        List<Integer> group = new ArrayList<>();
                        group.add(num1);
                        group.add(num2);
                        group.add(num3);
                        set.add(group);
                    }
                }
            }
        }
        return new ArrayList<>(set);
    }




    /**
     * 解法2：排序+双指针
     * 针对解法1优化，在获取第二个数num2和第三个数num3时，
     * 令左指针 L=i+1，右指针 R=nums.length-1，当 L<R 时，执行循环：
     *   当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
     *   若和大于 0，说明 nums[R] 太大，R 左移
     *   若和小于 0，说明 nums[L] 太小，L 右移
     */
    public List<List<Integer>> threeSum2(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }

        // 升序排列
        Arrays.sort(nums);

        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {

            int num1 = nums[i];
            // 重复元素直接跳过
            if (i != 0 && num1 == nums[i - 1]) {
                continue;
            }
            // 三数和一定大于0
            if (num1 > 0) {
                break;
            }

            // 双指针
            int left = i + 1, right = nums.length - 1;
            while (left < right) {

                // 跳过重复元素
                if (left != i + 1 && nums[left] == nums[left - 1]) {
                    left ++;
                    continue;
                }
                if (right != nums.length - 1 && nums[right] == nums[right + 1]) {
                    right --;
                    continue;
                }

                int num2 = nums[left];
                int num3 = nums[right];

                if (num1 + num2 + num3 == 0) {
                    List<Integer> group = new ArrayList<>();
                    group.add(num1);
                    group.add(num2);
                    group.add(num3);
                    set.add(group);
                    left ++;
                    right --;
                }
                if (num1 + num2 + num3 < 0) {
                    left ++;
                }
                if (num1 + num2 + num3 > 0) {
                    right --;
                }
            }
        }
        return new ArrayList<>(set);
    }


    @Test
    public void test() {
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSum(new int[]{}));
    }

    @Test
    public void test2() {
        System.out.println(threeSum2(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(threeSum2(new int[]{}));
    }
}
