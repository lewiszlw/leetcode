package leetcode.algorithms;

//The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
//
// By listing and labeling all of the permutations in order, we get the followin
//g sequence for n = 3:
//
//
// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
//
//
// Given n and k, return the kth permutation sequence.
//
//
// Example 1:
// Input: n = 3, k = 3
//Output: "213"
// Example 2:
// Input: n = 4, k = 9
//Output: "2314"
// Example 3:
// Input: n = 3, k = 1
//Output: "123"
//
//
// Constraints:
//
//
// 1 <= n <= 9
// 1 <= k <= n!
//
// Related Topics Math Backtracking
// 👍 2309 👎 367

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _0060PermutationSequence {

    /**
     * 解法：
     * 分析从左到右每一位上的数字选择时，计算剩余数字个数的阶乘，组成若干个bucket，确定k处于哪个bucket
     * 依次分析下一位
     */
    public String getPermutation(int n, int k) {
        // n -> n! 映射
        Map<Integer, Integer> factorialMap = new HashMap<>();
        int factorial = 1;
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            factorialMap.put(i, factorial);
        }

        // 所有1-n数字升序排列
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < n; i++) {
            // 计算剩余数字个数 i - 1 的阶乘
            int fac = factorialMap.get(n - i);

            // 计算第k大数字所处的桶位(从0开始)  将其分成 n - i - 1 个桶，每个桶装 fac 个数
            // k为当前所有桶排序后第k个数(从1开始)
            int bucket = k % fac == 0 ? k / fac - 1: k / fac;
            Integer num = nums.get(bucket);
            sb.append(num);
            nums.remove(num);

            // 更新k
            k -= bucket * fac;
        }
        sb.append(nums.get(k - 1));  // 最后一位
        return sb.toString();
    }


    @Test
    public void test() {
        Assert.assertEquals("1", getPermutation(1, 1));
        Assert.assertEquals("213", getPermutation(3, 3));
        Assert.assertEquals("2314", getPermutation(4, 9));
        Assert.assertEquals("123", getPermutation(3, 1));
    }
}
