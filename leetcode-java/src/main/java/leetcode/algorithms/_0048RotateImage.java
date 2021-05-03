package leetcode.algorithms;

/*
 * @lc app=leetcode id=48 lang=java
 *
 * [48] Rotate Image
 *
 * https://leetcode.com/problems/rotate-image/description/
 *
 * algorithms
 * Medium (47.37%)
 * Likes:    1604
 * Dislikes: 152
 * Total Accepted:    258K
 * Total Submissions: 527.7K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * You are given an n x n 2D matrix representing an image.
 *
 * Rotate the image by 90 degrees (clockwise).
 *
 * Note:
 *
 * You have to rotate the image in-place, which means you have to modify the
 * input 2D matrix directly. DO NOT allocate another 2D matrix and do the
 * rotation.
 *
 * Example 1:
 *
 *
 * Given input matrix =
 * [
 * ⁠ [1,2,3],
 * ⁠ [4,5,6],
 * ⁠ [7,8,9]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 * ⁠ [7,4,1],
 * ⁠ [8,5,2],
 * ⁠ [9,6,3]
 * ]
 *
 *
 * Example 2:
 *
 *
 * Given input matrix =
 * [
 * ⁠ [ 5, 1, 9,11],
 * ⁠ [ 2, 4, 8,10],
 * ⁠ [13, 3, 6, 7],
 * ⁠ [15,14,12,16]
 * ],
 *
 * rotate the input matrix in-place such that it becomes:
 * [
 * ⁠ [15,13, 2, 5],
 * ⁠ [14, 3, 4, 1],
 * ⁠ [12, 6, 8, 9],
 * ⁠ [16, 7,10,11]
 * ]
 *
 *
 */

import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-21
 */
public class _0048RotateImage {

    /**
     * 解法：
     * (m,n)顺时针旋转90度后的位置是(n,len-1-m)
     * m为纵坐标，n为横坐标
     */
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 1) {
            return;
        }
        for (int i = 0; i < (matrix.length + 1) / 2; i++) {
            // 沿对角线开始, (0,0),(1,1)...
            for (int j = i; j < matrix.length - i - 1; j++) {
                // 从对角线开始往左遍历到第一个交换点前
                int m = i, n = j;
                int val = matrix[m][n];
                for (int k = 0; k < 4; k++) {
                    // 依次交换，每轮四个位置
                    // (0,0) -> (0,2) -> (2,2) -> (2,0) -> (0,0)
                    int nextM = n, nextN = matrix.length - 1 - m;
                    val = exchange(matrix, nextM, nextN, val);
                    m = nextM;
                    n = nextN;
                }
            }
        }
    }

    /**
     * 将本节点的值替换到下一个节点，并返回下一个节点值
     */
    private int exchange(int[][] matrix, int nextM, int nextN, int val) {
        int tmp = matrix[nextM][nextN];
        matrix[nextM][nextN] = val;
        return tmp;
    }


    @Test
    public void test() {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate(matrix);
        printMatrix(matrix);

        int[][] matrix2 = {
                {5, 1, 9,11},
                {2, 4, 8,10},
                {13, 3, 6, 7},
                {15,14,12,16}
        };
        rotate(matrix2);
        printMatrix(matrix2);
    }

    private void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
