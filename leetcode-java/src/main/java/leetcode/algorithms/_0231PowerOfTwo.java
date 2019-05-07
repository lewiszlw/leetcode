package leetcode.algorithms;

/*
 * @lc app=leetcode id=231 lang=java
 *
 * [231] Power of Two
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-07
 */
public class _0231PowerOfTwo {

    public boolean isPowerOfTwo(int n) {
        if (n < 1) {
            return false;
        }
        while (n >= 1) {
            if (n == 1) {
                // 整除2最后为1
                return true;
            }
            if (n % 2 == 0) {
                // 如果可以整除2，则循环整除2
                n /= 2;
            } else {
                // 无法被2整除
                return false;
            }
        }
        return false;
    }

    @Test
    public void test() {
        Assert.assertTrue(isPowerOfTwo(1));
        Assert.assertTrue(isPowerOfTwo(16));
        Assert.assertTrue(!isPowerOfTwo(218));
    }

}
