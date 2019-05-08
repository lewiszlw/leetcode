package leetcode.algorithms;

/*
 * @lc app=leetcode id=217 lang=java
 *
 * [217] Contains Duplicate
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-08
 */
public class _0217ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, String> map = new HashMap<>();
        String entryValue = "a";
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return true;
            }
            map.put(nums[i], entryValue);
        }
        return false;
    }


    @Test
    public void test() {
        Assert.assertTrue(containsDuplicate(new int[]{1,2,3,1}));
        Assert.assertTrue(!containsDuplicate(new int[]{1,2,3,4}));
        Assert.assertTrue(containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2}));
    }
}
