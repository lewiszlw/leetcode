package leetcode.algorithms;

//You are given an array prices where prices[i] is the price of a given stock on
// the ith day.
//
// Find the maximum profit you can achieve. You may complete as many transaction
//s as you like (i.e., buy one and sell one share of the stock multiple times).
//
// Note: You may not engage in multiple transactions simultaneously (i.e., you m
//ust sell the stock before you buy again).
//
//
// Example 1:
//
//
//Input: prices = [7,1,5,3,6,4]
//Output: 7
//Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit =
//5-1 = 4.
//Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
//
//
//
// Example 2:
//
//
//Input: prices = [1,2,3,4,5]
//Output: 4
//Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit =
//5-1 = 4.
//Note that you cannot buy on day 1, buy on day 2 and sell them later, as you ar
//e engaging multiple transactions at the same time. You must sell before buying a
//gain.
//
//
// Example 3:
//
//
//Input: prices = [7,6,4,3,1]
//Output: 0
//Explanation: In this case, no transaction is done, i.e., max profit = 0.
//
//
//
// Constraints:
//
//
// 1 <= prices.length <= 3 * 104
// 0 <= prices[i] <= 104
//
// Related Topics Array Greedy
// 👍 4280 👎 2034

import org.junit.Assert;
import org.junit.Test;

public class _0122BestTimeToBuyAndSellStockII {

    /**
     * 解法1：双指针
     * 每次低谷入手，高峰抛出
     */
    public int maxProfit(int[] prices) {
        int sum = 0;
        int i = 0;
        while (i < prices.length) {
            int j = i + 1;
            while (j < prices.length) {
                // 确保趋势一直上升
                if (prices[j] < prices[j - 1]) {
                    break;
                }
                j ++;
            }
            // 1.j指针遍历到尾部正常退出
            if (j == prices.length) {
                sum += prices[j - 1] - prices[i];
                break;
            } else {
                // 2.j指针遍历到趋势开始降低
                sum += prices[j - 1] - prices[i];
                i = j;
            }
        }
        return sum;
    }




    /**
     * 解法2：贪心法
     * 由于不限制交易次数，只要今天股价比昨天高，就交易。
     * 该算法仅可以用于计算，但计算的过程并不是真正交易的过程，但可以用贪心算法计算题目要求的最大利润。
     */
    public int maxProfit2(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                sum += diff;
            }
        }
        return sum;
    }




    /**
     * 解法3：动态规划
     *
     * dp[i][0] 表示第 i（i 从 0 开始）天交易完后手里没有股票的最大利润，
     * dp[i][1] 表示第 i 天交易完后手里持有一支股票的最大利润。
     *
     *     dp[i][0] = max{dp[i − 1][0], dp[i − 1][1] + prices[i]}
     *     dp[i][1] = max{dp[i − 1][1], dp[i − 1][0] − prices[i]}
     */
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[n - 1][0];
    }

    @Test
    public void test() {
        Assert.assertEquals(0, maxProfit(new int[]{7}));
        Assert.assertEquals(7, maxProfit(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(4, maxProfit(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, maxProfit(new int[]{7,6,4,3,1}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, maxProfit2(new int[]{7}));
        Assert.assertEquals(7, maxProfit2(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(4, maxProfit2(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, maxProfit2(new int[]{7,6,4,3,1}));
    }

    @Test
    public void test3() {
        Assert.assertEquals(0, maxProfit3(new int[]{7}));
        Assert.assertEquals(7, maxProfit3(new int[]{7,1,5,3,6,4}));
        Assert.assertEquals(4, maxProfit3(new int[]{1,2,3,4,5}));
        Assert.assertEquals(0, maxProfit3(new int[]{7,6,4,3,1}));
    }
}
