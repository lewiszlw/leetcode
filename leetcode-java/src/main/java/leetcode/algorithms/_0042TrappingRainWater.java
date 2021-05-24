package leetcode.algorithms;

//Given n non-negative integers representing an elevation map where the width of
// each bar is 1, compute how much water it can trap after raining.
//
//
// Example 1:
//
//
//Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
//Explanation: The above elevation map (black section) is represented by array [
//0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are
// being trapped.
//
//
// Example 2:
//
//
//Input: height = [4,2,0,3,2,5]
//Output: 9
//
//
//
// Constraints:
//
//
// n == height.length
// 0 <= n <= 3 * 104
// 0 <= height[i] <= 105
//
// Related Topics Array Two Pointers Dynamic Programming Stack
// 👍 11412 👎 167

import org.junit.Assert;
import org.junit.Test;

public class _0042TrappingRainWater {

    /**
     * 解法1：
     * 水平面高度设置为1，遍历看这些坑能接住多少量的水；
     * 水平面高度设置为2，遍历看这些坑又能额外接住多少量的水；
     * 依次遍历直至水平面高度超过柱子最大高度。
     */
    public int trap(int[] height) {
        int totalWater = 0;

        int level = 1; // 水平面
        while (true) {
            int start = -1;
            int currentWater = 0;
            for (int i = 0; i < height.length; i++) {
                // 找到第一个大于等于level的高度作为start
                if (start == -1) {
                    if (height[i] >= level) {
                        start = i;
                    }
                    continue;
                }

                if (height[i] < level) {
                    // 额外增加的水量，+1即可
                    currentWater += 1;
                } else {
                    // 遍历到了此处坑的右边，即坑(start, i)之间积蓄了currentWater水量
                    totalWater += currentWater;

                    // 更新start和currentWater
                    start = i;
                    currentWater = 0;
                }
            }
            if (start == -1) {
                // 水平面超过最大柱子高度，不用继续遍历，跳出循环
                break;
            }
            level++;
        }

        return totalWater;
    }



    /**
     * 解法2：按列求
     * 遍历每根柱子，查找左边最高的柱子和右边最高的柱子，就能知道这个柱子上能存放多少水了
     */
    public int trap2(int[] height) {
        int totalWater = 0;
        // 遍历每根柱子
        for (int i = 0; i < height.length; i++) {
            // 当前柱子高度
            int elevation = height[i];
            // 左边最高的柱子高度
            int leftMaxElevation = -1;
            for (int j = 0; j < i; j++) {
                if (height[j] > leftMaxElevation) {
                    leftMaxElevation = height[j];
                }
            }
            // 右边最高的柱子高度
            int rightMaxElevation = -1;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > rightMaxElevation) {
                    rightMaxElevation = height[j];
                }
            }

            if (elevation > Math.max(leftMaxElevation, rightMaxElevation)) {
                // 1.当前柱子高于左右最高柱子高度
                continue;
            } else if (elevation > leftMaxElevation || elevation > rightMaxElevation){
                // 2.当前柱子处于左右最高柱子高度之间
                continue;
            } else {
                // 3.当前柱子低于等于左右最高柱子高度
                totalWater += Math.min(leftMaxElevation, rightMaxElevation) - elevation;
            }
        }
        return totalWater;
    }



    /**
     * 解法3：动态规划
     * 优化解法2中查找左边最高柱子高度和右边最高柱子高度
     * maxLeft[i]代表第i列左边（不包括第i列）最高柱子高度，则
     *    maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1])
     * maxRight[i]代表第i列右边（不包括第i列）最高柱子高度，则
     *    maxRight[i] = Math.max(maxRight[i + 1], height[i + 1])
     */
    public int trap3(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        // 构建动态规划记录表
        int[] maxLeft = new int[height.length];
        maxLeft[0] = 0;
        int tmpLeftMax = height[0]; // 第i列左边最高高度
        for (int i = 1; i < height.length; i++) {
            maxLeft[i] = tmpLeftMax;
            if (height[i] > tmpLeftMax) {
                tmpLeftMax = height[i];
            }
        }
        int[] maxRight = new int[height.length];
        maxRight[height.length - 1] = height[height.length - 1];
        int tmpRightMax = height[height.length - 1]; // 第i列右边最高高度
        for (int i = height.length - 2; i >= 0; i--) {
            maxRight[i] = tmpRightMax;
            if (height[i] > tmpRightMax) {
                tmpRightMax = height[i];
            }
        }

        int totalWater = 0;
        for (int i = 0; i < height.length; i++) {
            // 当前柱子高度
            int elevation = height[i];
            // 左边最高的柱子高度
            int leftMaxElevation = maxLeft[i];
            // 右边最高的柱子高度
            int rightMaxElevation = maxRight[i];

            if (elevation > Math.max(leftMaxElevation, rightMaxElevation)) {
                // 1.当前柱子高于左右最高柱子高度
                continue;
            } else if (elevation > leftMaxElevation || elevation > rightMaxElevation){
                // 2.当前柱子处于左右最高柱子高度之间
                continue;
            } else {
                // 3.当前柱子低于等于左右最高柱子高度
                totalWater += Math.min(leftMaxElevation, rightMaxElevation) - elevation;
            }
        }
        return totalWater;
    }





    @Test
    public void test() {
        Assert.assertEquals(6, trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(9, trap(new int[]{4,2,0,3,2,5}));
    }

    @Test
    public void test2() {
        Assert.assertEquals(6, trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(9, trap2(new int[]{4,2,0,3,2,5}));
    }

    @Test
    public void test3() {
        Assert.assertEquals(6, trap3(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assert.assertEquals(9, trap3(new int[]{4,2,0,3,2,5}));
    }
}
