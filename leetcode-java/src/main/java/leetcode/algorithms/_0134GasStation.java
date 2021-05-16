package leetcode.algorithms;

//There are n gas stations along a circular route, where the amount of gas at th
//e ith station is gas[i].
//
// You have a car with an unlimited gas tank and it costs cost[i] of gas to trav
//el from the ith station to its next (i + 1)th station. You begin the journey wit
//h an empty tank at one of the gas stations.
//
// Given two integer arrays gas and cost, return the starting gas station's inde
//x if you can travel around the circuit once in the clockwise direction, otherwis
//e return -1. If there exists a solution, it is guaranteed to be unique
//
//
// Example 1:
//
//
//Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
//Output: 3
//Explanation:
//Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4
// = 4
//Travel to station 4. Your tank = 4 - 1 + 5 = 8
//Travel to station 0. Your tank = 8 - 2 + 1 = 7
//Travel to station 1. Your tank = 7 - 3 + 2 = 6
//Travel to station 2. Your tank = 6 - 4 + 3 = 5
//Travel to station 3. The cost is 5. Your gas is just enough to travel back to
//station 3.
//Therefore, return 3 as the starting index.
//
//
// Example 2:
//
//
//Input: gas = [2,3,4], cost = [3,4,3]
//Output: -1
//Explanation:
//You can't start at station 0 or 1, as there is not enough gas to travel to the
// next station.
//Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
//
//Travel to station 0. Your tank = 4 - 3 + 2 = 3
//Travel to station 1. Your tank = 3 - 3 + 3 = 3
//You cannot travel back to station 2, as it requires 4 unit of gas but you only
// have 3.
//Therefore, you can't travel around the circuit once no matter where you start.
//
//
//
//
// Constraints:
//
//
// gas.length == n
// cost.length == n
// 1 <= n <= 104
// 0 <= gas[i], cost[i] <= 104
//
// Related Topics Greedy
// 👍 3054 👎 438

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _0134GasStation {

    /**
     * 解法：优化遍历
     * delta[i] = gas[i] - cost[i]，每站能剩多少油量，正数代表剩余，负数代表消耗
     * 策略是尽可能在前面累计剩余油量，将delta切分成多个正数块，每个正数块第一个数则可能是起始点
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // gas[i] - cost[i], 每站能剩多少油量
        int[] delta = new int[gas.length];
        for (int i = 0; i < delta.length; i++) {
            delta[i] = gas[i] - cost[i];
        }
        // 拿到所有可能的起始点
        List<Integer> startPoints = startPoints(delta);
        for (int i = 0; i < startPoints.size(); i++) {
            int startPoint = startPoints.get(i);
            // 验证起始点
            if (eligibleStartPoint(startPoint, delta)) {
                return startPoint;
            }
        }
        return -1;
    }

    // 用负数将delta[]拆分成若干个只包含大于0元素的子数组
    // 如 [1,2,-2,4,5,-3,3]
    // 拆分后 [1,2]，[4,5], [3]
    // 返回每个子数组中第一个数的索引，即[0,3,6]，即可能的起始点
    private List<Integer> startPoints(int[] delta) {
        List<Integer> startPoints = new ArrayList<>();
        boolean nextMightBeStart = true;
        for (int i = 0; i < delta.length; i++) {
            if (delta[i] < 0) {
                nextMightBeStart = true;
                continue;
            }

            // delta[i] >= 0
            if (nextMightBeStart) {
                startPoints.add(i);
                nextMightBeStart = false;
            }
        }
        return startPoints;
    }

    // 验证起始点是否正确
    private boolean eligibleStartPoint(int startPoint, int[] delta) {
        int sum = delta[startPoint];
        int i = startPoint == delta.length - 1 ? 0 : startPoint + 1;
        while (i != startPoint) {
            sum += delta[i];
            if (sum < 0) {
                return false;
            }
            i = i == delta.length - 1 ? 0 : i + 1;
        }
        return true;
    }


    @Test
    public void test() {
        Assert.assertEquals(3, canCompleteCircuit(new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));
    }
}
