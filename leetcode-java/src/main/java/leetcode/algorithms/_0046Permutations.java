package leetcode.algorithms;

//Given an array nums of distinct integers, return all the possible permutations
//. You can return the answer in any order.
//
//
// Example 1:
// Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// Example 2:
// Input: nums = [0,1]
//Output: [[0,1],[1,0]]
// Example 3:
// Input: nums = [1]
//Output: [[1]]
//
//
// Constraints:
//
//
// 1 <= nums.length <= 6
// -10 <= nums[i] <= 10
// All the integers of nums are unique.
//
// Related Topics Backtracking
// 👍 5893 👎 134

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _0046Permutations {
    /**
     * 解法1：递归
     * [1,2,3]
     * 首先取1，existingNums则为[2,3]，递归继续取2，existingNums则为[3]，递归继续取3，existingNums则为[]，得到一个全排列[1,2,3]
     * 首先取2，existingNums则为[1,3]，递归继续取1，existingNums则为[3]，递归继续取3，existingNums则为[]，得到一个全排列[2,1,3]
     * 首先取3，existingNums则为[1,2]，递归继续取1，existingNums则为[2]，递归继续取2，existingNums则为[]，得到一个全排列[3,1,2]
     * 以此类推
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        // 将数组转化成list，方便remove元素
        List<Integer> existingNums = IntStream.of(nums).boxed().collect(Collectors.toList());
        generatePermutations(permutations, existingNums, new ArrayList<>());
        return permutations;
    }

    private void generatePermutations(List<List<Integer>> permutations,
                                      List<Integer> existingNums,
                                      List<Integer> permutation) {
        // 当existingNums为空时，说明一个全排列完成
        if (existingNums.size() == 0) {
            permutations.add(permutation);
            return;
        }

        for (int i = 0; i < existingNums.size(); i++) {
            // 复制上一轮pick后的部分排列结果，并pick新的数字进入该排列
            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(existingNums.get(i));

            // 移除pick的元素，得到新的newExistingNums
            List<Integer> newExistingNums = new ArrayList<>(existingNums);
            newExistingNums.remove(i);

            // 继续下一轮pick
            generatePermutations(permutations, newExistingNums, newPermutation);
        }
    }



    /**
     * 解法2：回溯法
     * 看作有 n 个排列成一行的空格，我们需要从左往右依此填入题目给定的 n 个数，每个数只能使用一次
     * nums 划分成左右两个部分，左边的表示已经填过的数，右边表示待填的数，我们在回溯的时候只要动态维护这个数组即可
     *
     * [1,2,3]
     * first=0&i=first,[1,2,3], first=1&i=first,[1,2,3], first=2&i=first,[1,2,3] => [1,2,3]
     * first=0&i=first,[1,2,3], first=1,i=first+1,[1,3,2], first=2&i=first,[1,3,2] => [1,3,2]
     * first=0&i=first+1,[2,1,3], first=1,i=first,[2,1,3], first=2&i=first,[2,1,3] => [2,1,3]
     * first=0&i=first+1,[2,1,3], first=1,i=first+1,[2,3,1], first=2&i=first,[2,3,1] => [2,3,1]
     * ...
     * 以此类推
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        // nums转成list方便交换两个元素
        List<Integer> output = IntStream.of(nums).boxed().collect(Collectors.toList());
        backtrace(0, nums.length, output, permutations);
        return permutations;
    }

    /**
     * output列表中，first左侧为已经选好的数，first及右侧为空格（即未选择数字）
     */
    private void backtrace(int first, int length, List<Integer> output, List<List<Integer>> permutations) {
        if (first == length) {
            // 需要拷贝output
            permutations.add(new ArrayList<>(output));
        }
        for (int i = first; i < length; i++) {
            // 在first位置填上一个数nums[i]
            Collections.swap(output, first, i);
            // first位置已填好数，继续填first+1位置的数
            backtrace(first + 1, length, output, permutations);
            // 回溯，保证for循环进入下一轮，output不会变化，下一轮在first位置填数num[i+1]
            Collections.swap(output, first, i);
        }
    }

    @Test
    public void test1() {
        // it should be [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println(permute(new int[]{1,2,3}));
    }

    @Test
    public void test2() {
        // it should be [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        System.out.println(permute2(new int[]{1,2,3}));
    }
}
