package leetcode.algorithms;

/*
 * @lc app=leetcode id=374 lang=java
 *
 * [374] Guess Number Higher or Lower
 */
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-30
 */
public class _0374GuessNumberHigherOrLower{

    private int pickNum = 5;

    public int guessNumber(int n) {
        long low = 1;
        long high = n;
        while (low <= high) {
            // 可能超出int范围
            long mid = (low + high) / 2;
            // int mid = low + (high - low) / 2; 则不会
            if (guess((int) mid) == 1) {
                low = mid + 1;
            }
            if (guess((int) mid) == 0) {
                return (int) mid;
            }
            if (guess((int) mid) == -1) {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * guess api
     */
    public int guess(int num) {
        // 注意-1、0、1含义
        return num == pickNum? 0: (num < pickNum? 1: -1);
    }

    @Test
    public void test() {
        pickNum = 6;
        Assert.assertTrue(guessNumber(10) == 6);

        pickNum = 1702766719;
        Assert.assertTrue(guessNumber(2126753390) == 1702766719);
    }
}
