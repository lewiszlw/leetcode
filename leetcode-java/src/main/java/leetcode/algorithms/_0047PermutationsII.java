package leetcode.algorithms;

//Given a collection of numbers, nums, that might contain duplicates, return all
// possible unique permutations in any order.
//
//
// Example 1:
//
//
//Input: nums = [1,1,2]
//Output:
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
//
//
// Example 2:
//
//
//Input: nums = [1,2,3]
//Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//
//
//
// Constraints:
//
//
// 1 <= nums.length <= 8
// -10 <= nums[i] <= 10
//
// Related Topics Backtracking
// 👍 2938 👎 78

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _0047PermutationsII {

    /**
     * 解法1：
     * 通过递归或者回溯找到所有全排列（见_0046Permutations.java），然后去重
     * 去重可以选择对每个排列进行hash，得到一个唯一的hash值，对hash函数要求较高
     * 去重也可以使用Set，直接简单的对比两个list是否一致
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> output = IntStream.of(nums).boxed().collect(Collectors.toList());
        // 得到不去重后的所有全排列
        backtrace(0, nums.length, output, permutations);

        // 去重
        return permutations.stream().distinct().collect(Collectors.toList());
    }

    private void backtrace(int first, int length, List<Integer> output, List<List<Integer>> permutations) {
        if (first == length) {
            permutations.add(new ArrayList<>(output));
            return;
        }
        for (int i = first; i < length; i++) {
            Collections.swap(output, first, i);
            backtrace(first + 1, length, output, permutations);
            Collections.swap(output, first, i);
        }
    }


    /**
     * 解法2：回溯 + 去重/剪枝
     * 先看 _0046PermutationsII.java 回溯解法
     * 在填数字的过程中进行去重，相同数字只填一次
     */
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        List<Integer> output = IntStream.of(nums).boxed().collect(Collectors.toList());
        // 得到不去重后的所有全排列
        backtrace2(0, nums.length, output, permutations);
        return permutations;
    }

    private void backtrace2(int first, int length, List<Integer> output, List<List<Integer>> permutations) {
        if (first == length) {
            permutations.add(new ArrayList<>(output));
            return;
        }
        // uniqueNums包含已经填过的数字
        Set<Integer> uniqueNums = new HashSet<>();
        for (int i = first; i < length; i++) {
            if (!uniqueNums.contains(output.get(i))) {
                Collections.swap(output, first, i);
                backtrace2(first + 1, length, output, permutations);
                Collections.swap(output, first, i);
                uniqueNums.add(output.get(i));
            }
        }
    }

    @Test
    public void test() {
        System.out.println(permuteUnique(new int[]{1,1,2}));
    }

    @Test
    public void test2() {
        System.out.println(permuteUnique2(new int[]{1,1,2}));
    }
}
