package leetcode.algorithms;

import org.junit.Test;

/**
 * Desc:
 * 136 SingleNumber
 * 解决方法：异或。两个相同的数异或为0，0与任何数异或为该数不变，异或满足交换律、结合律。
 *
 * @author zhanglinwei02
 * @date 2019-03-27
 */
public class _136SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result = result ^ nums[i];
        }
        return result;
    }

    @Test
    public void test() {
        int[] nums = {1,2,2,4,4};
        System.out.println(singleNumber(nums));
    }

}
