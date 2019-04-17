package leetcode.algorithms;

/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * algorithms
 * Easy (46.57%)
 * Total Accepted:    472.1K
 * Total Submissions: 1M
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * Say you have an array for which the i^th element is the price of a given
 * stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy
 * one and sell one share of the stock), design an algorithm to find the
 * maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit
 * = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 *
 *
 * Example 2:
 *
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-17
 */
public class _0121BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int i = 0;
        while (i < prices.length) {
            int j = i + 1;
            // 是否跳到j位置
            boolean flag = false;
            while (j < prices.length) {
                if (prices[j] <= prices[i]) {
                    // 如果最大值的结束点在后段，则起点一定是j或者j后面位置的点
                    // 此时直接从j开始往后遍历
                    i = j;
                    flag = true;
                    break;
                }
                if (prices[j] - prices[i] > maxProfit) {
                    maxProfit = prices[j] - prices[i];
                }
                j ++;
            }
            if (!flag) {
                // 说明全程都是上升趋势，后面则不用再比较了
                break;
            }
        }
        return maxProfit;
    }

    @Test
    public void test() {
        Assert.assertTrue(maxProfit(new int[]{7,1,5,3,6,4}) == 5);
        Assert.assertTrue(maxProfit(new int[]{7,6,4,3,1}) == 0);
    }
}
