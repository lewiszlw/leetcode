package leetcode.algorithms;

/*
 * @lc app=leetcode id=119 lang=java
 *
 * [119] Pascal's Triangle II
 *
 * https://leetcode.com/problems/pascals-triangle-ii/description/
 *
 * algorithms
 * Easy (42.53%)
 * Total Accepted:    195.3K
 * Total Submissions: 455.3K
 * Testcase Example:  '3'
 *
 * Given a non-negative index k where k ≤ 33, return the k^th index row of the
 * Pascal's triangle.
 *
 * Note that the row index starts from 0.
 *
 *
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it.
 *
 * Example:
 *
 *
 * Input: 3
 * Output: [1,3,3,1]
 *
 *
 * Follow up:
 *
 * Could you optimize your algorithm to use only O(k) extra space?
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * 思路：下一行在当前行上改动，所以需要用临时变量记录当前行的某些值
 *
 * @author zhanglinwei02
 * @date 2019-04-17
 */
public class _0119PascalsTriangleII {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0) {
                list.add(1);
                continue;
            }
            // 下一个数字的左肩和右肩
            Integer leftNum = null;
            Integer rigthNum = null;
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 行首不用动
                    // 更新下一个数字两肩
                    leftNum = j >= list.size()? null: list.get(j);
                    rigthNum = j + 1 >= list.size()? null: list.get(j + 1);
                    continue;
                }
                if (j == i) {
                    // 行尾
                    list.add(1);
                    continue;
                }
                Integer sum = (leftNum == null? 0: leftNum) + (rigthNum == null? 0: rigthNum);
                // 更新下一个数字两肩
                leftNum = j > list.size()? null: list.get(j);
                rigthNum = j + 1 >= list.size()? null: list.get(j + 1);
                list.remove(j);
                list.add(j, sum);
            }
        }
        return list;
    }


    @Test
    public void test() {
        List<Integer> list = getRow(3);
        Assert.assertTrue(list.get(0) == 1);
        Assert.assertTrue(list.get(1) == 3);
        Assert.assertTrue(list.get(2) == 3);
        Assert.assertTrue(list.get(3) == 1);
    }

}
