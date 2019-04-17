package leetcode.algorithms;

/*
 * @lc app=leetcode id=118 lang=java
 *
 * [118] Pascal's Triangle
 *
 * https://leetcode.com/problems/pascals-triangle/description/
 *
 * algorithms
 * Easy (45.01%)
 * Total Accepted:    241.8K
 * Total Submissions: 533.3K
 * Testcase Example:  '5'
 *
 * Given a non-negative integer numRows, generate the first numRows of Pascal's
 * triangle.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it.
 *
 * Example:
 *
 *
 * Input: 5
 * Output:
 * [
 * ⁠    [1],
 * ⁠   [1,1],
 * ⁠  [1,2,1],
 * ⁠ [1,3,3,1],
 * ⁠[1,4,6,4,1]
 * ]
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-17
 */
public class _0118PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            if (i == 0) {
                // 第一行直接初始化为1
                result.add(Arrays.asList(1));
                continue;
            }
            // 上一行
            List<Integer> preList = result.get(i - 1);
            // 当前行
            List<Integer> currentList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    // 当前行首或行尾为1
                    currentList.add(1);
                    continue;
                }
                // 两肩之和
                currentList.add(preList.get(j - 1) + preList.get(j));
            }
            result.add(currentList);
        }
        return result;
    }

    @Test
    public void test() {
        List<List<Integer>> list = generate(5);
        Assert.assertTrue(list.get(0).get(0) == 1);
        Assert.assertTrue(list.get(1).get(0) == 1);
        Assert.assertTrue(list.get(1).get(1) == 1);
        Assert.assertTrue(list.get(2).get(0) == 1);
        Assert.assertTrue(list.get(2).get(1) == 2);
        Assert.assertTrue(list.get(2).get(2) == 1);
        Assert.assertTrue(list.get(3).get(0) == 1);
        Assert.assertTrue(list.get(3).get(1) == 3);
        Assert.assertTrue(list.get(3).get(2) == 3);
        Assert.assertTrue(list.get(3).get(3) == 1);
        Assert.assertTrue(list.get(4).get(0) == 1);
        Assert.assertTrue(list.get(4).get(1) == 4);
        Assert.assertTrue(list.get(4).get(2) == 6);
        Assert.assertTrue(list.get(4).get(3) == 4);
        Assert.assertTrue(list.get(4).get(4) == 1);
    }
}
