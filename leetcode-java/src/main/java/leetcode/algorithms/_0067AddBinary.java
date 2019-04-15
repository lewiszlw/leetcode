package leetcode.algorithms;

/*
 * @lc app=leetcode id=67 lang=java
 *
 * [67] Add Binary
 *
 * https://leetcode.com/problems/add-binary/description/
 *
 * algorithms
 * Easy (38.32%)
 * Total Accepted:    290.7K
 * Total Submissions: 753.4K
 * Testcase Example:  '"11"\n"1"'
 *
 * Given two binary strings, return their sum (also a binary string).
 *
 * The input strings are both non-empty and contains only characters 1 or 0.
 *
 * Example 1:
 *
 *
 * Input: a = "11", b = "1"
 * Output: "100"
 *
 * Example 2:
 *
 *
 * Input: a = "1010", b = "1011"
 * Output: "10101"
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-15
 */
public class _0067AddBinary {

    public String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        StringBuilder sb = new StringBuilder();
        // 已经相加过的位数
        int count = 0;
        // 进位
        boolean carry = false;
        while (count < lenA && count < lenB) {
            int tmp = binaryVal(a.charAt(lenA - 1 - count)) + binaryVal(b.charAt(lenB - 1 - count));
            // 如果低位有进位则加1
            tmp += carry? 1: 0;
            // 判断本位是否进位
            carry = tmp > 1? true: false;
            sb.append(tmp > 1? tmp - 2: tmp);
            count ++;
        }

        // 剩余位 A/B
        if (count < lenA) {
            while (count < lenA) {
                int tmp = binaryVal(a.charAt(lenA - 1 - count));
                tmp += carry? 1: 0;
                carry = tmp > 1? true: false;
                sb.append(tmp > 1? tmp - 2: tmp);
                count ++;
            }
        }
        if (count < lenB) {
            while (count < lenB) {
                int tmp = binaryVal(b.charAt(lenB - 1 - count));
                tmp += carry? 1: 0;
                carry = tmp > 1? true: false;
                sb.append(tmp > 1? tmp - 2: tmp);
                count ++;
            }
        }

        // 最高位是否还有进位
        if (carry) {
            sb.append(1);
        }

        // 反转
        return sb.reverse().toString();
    }

    private int binaryVal(char c) {
        if (c == '0') {
            return 0;
        }
        if (c == '1') {
            return 1;
        }
        throw new IllegalArgumentException();
    }


    @Test
    public void test() {
        Assert.assertEquals(addBinary("111", "111"), "1110");
        Assert.assertEquals(addBinary("11", "1"), "100");
        Assert.assertEquals(addBinary("1010", "1011"), "10101");
    }

}
