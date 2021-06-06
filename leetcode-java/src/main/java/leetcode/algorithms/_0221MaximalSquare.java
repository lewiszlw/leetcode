package leetcode.algorithms;

//Given an m x n binary matrix filled with 0's and 1's, find the largest square
//containing only 1's and return its area.
//
//
// Example 1:
//
//
//Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1",
//"1"],["1","0","0","1","0"]]
//Output: 4
//
//
// Example 2:
//
//
//Input: matrix = [["0","1"],["1","0"]]
//Output: 1
//
//
// Example 3:
//
//
//Input: matrix = [["0"]]
//Output: 0
//
//
//
// Constraints:
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 300
// matrix[i][j] is '0' or '1'.
//
// Related Topics Dynamic Programming
// 👍 4681 👎 108

import org.junit.Assert;
import org.junit.Test;

public class _0221MaximalSquare {

    /**
     * 解法：动态规划
     * dp(i, j) 表示以 (i, j) 为右下角，且只包含 1 的正方形的边长最大值
     *
     *    dp(i, j) = min( dp(i − 1,j), dp(i − 1, j − 1), dp(i, j − 1) ) + 1
     *
     * 举例：
     * 原始矩阵如下，
     *    0 1 1 1 0
     *    1 1 1 1 0
     *    0 1 1 1 1
     *    0 1 1 1 1
     *    0 0 1 1 1
     * dp 如下，
     *    0 1 1 1 0
     *    1 1 2 2 0
     *    0 1 2 3 1
     *    0 1 2 3 2
     *    0 0 1 2 3
     * 注意看 dp[2][3], dp[3][4], dp[4][2] 计算。
     *
     */
    public int maximalSquare(char[][] matrix) {
        // 最大正方形边长
        int max = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        // 处在第一行或第一列，正方形边长最大为1
                        dp[i][j] = 1;
                    } else {
                        // 取左方、上方、左上方最小值
                        int min = Math.min(Math.min(dp[i - 1][j], dp[i - 1][j - 1]), dp[i][j - 1]);
                        dp[i][j] = min + 1;
                    }
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max * max;
    }



    @Test
    public void test() {
        Assert.assertEquals(4, maximalSquare(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}}));
        Assert.assertEquals(1, maximalSquare(new char[][]{
                {'0','1'},
                {'1','0'}}));
        Assert.assertEquals(0, maximalSquare(new char[][]{
                {'0'}}));
    }
}
