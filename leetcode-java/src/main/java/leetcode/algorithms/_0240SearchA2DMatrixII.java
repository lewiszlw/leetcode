package leetcode.algorithms;

/*
 * @lc app=leetcode id=240 lang=java
 *
 * [240] Search a 2D Matrix II
 *
 * https://leetcode.com/problems/search-a-2d-matrix-ii/description/
 *
 * algorithms
 * Medium (40.43%)
 * Likes:    1511
 * Dislikes: 43
 * Total Accepted:    182.1K
 * Total Submissions: 445.2K
 * Testcase Example:  '[[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]]\n5'
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 *
 *
 * Integers in each row are sorted in ascending from left to right.
 * Integers in each column are sorted in ascending from top to bottom.
 *
 *
 * Example:
 *
 * Consider the following matrix:
 *
 *
 * [
 * ⁠ [1,   4,  7, 11, 15],
 * ⁠ [2,   5,  8, 12, 19],
 * ⁠ [3,   6,  9, 16, 22],
 * ⁠ [10, 13, 14, 17, 24],
 * ⁠ [18, 21, 23, 26, 30]
 * ]
 *
 *
 * Given target = 5, return true.
 *
 * Given target = 20, return false.
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 * 解法：矩阵特点是右上角和左下角两条边线是递增的，利用这个单调特点来遍历矩阵
 *
 * @author zhanglinwei02
 * @date 2019-06-11
 */
public class _0240SearchA2DMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        // 边界情况
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        // 从右上角作为起点
        int x = 0, y = matrix[0].length - 1;

        while (x < matrix.length && y >= 0) {

            int num = matrix[x][y];

            if (num == target) {
                return true;
            }

            if (num > target) {
                y --;
            }

            if (num < target) {
                x ++;
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[][] matrix = new int[][] {
                {1,   4,  7, 11, 15},
                {2,   5,  8, 12, 19},
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        Assert.assertTrue(searchMatrix(matrix, 5));
        Assert.assertTrue(!searchMatrix(matrix, 20));
    }

}
