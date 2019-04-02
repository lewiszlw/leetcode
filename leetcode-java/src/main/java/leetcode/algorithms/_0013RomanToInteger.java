package leetcode.algorithms;

/*
 * @lc app=leetcode id=13 lang=java
 *
 * [13] Roman to Integer
 *
 * https://leetcode.com/problems/roman-to-integer/description/
 *
 * algorithms
 * Easy (51.84%)
 * Total Accepted:    385.1K
 * Total Submissions: 742.4K
 * Testcase Example:  '"III"'
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D
 * and M.
 *
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 *
 * For example, two is written as II in Roman numeral, just two one's added
 * together. Twelve is written as, XII, which is simply X + II. The number
 * twenty seven is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right.
 * However, the numeral for four is not IIII. Instead, the number four is
 * written as IV. Because the one is before the five we subtract it making
 * four. The same principle applies to the number nine, which is written as IX.
 * There are six instances where subtraction is used:
 *
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 *
 *
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be
 * within the range from 1 to 3999.
 *
 * Example 1:
 *
 *
 * Input: "III"
 * Output: 3
 *
 * Example 2:
 *
 *
 * Input: "IV"
 * Output: 4
 *
 * Example 3:
 *
 *
 * Input: "IX"
 * Output: 9
 *
 * Example 4:
 *
 *
 * Input: "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 *
 *
 * Example 5:
 *
 *
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-02
 */
public class _0013RomanToInteger {

    public int romanToInt(String s) {
        int result = 0;

        char[] chars = s.toCharArray();

        int i = chars.length - 1;
        while (i >= 0) {
            RomanNum romanNum = RomanNum.getInstance(chars[i]);
            RomanNum preRomanNum = i > 0? RomanNum.getInstance(chars[i-1]): null;
            if (preRomanNum != null && preRomanNum == romanNum.before()) {
                result += romanNum.value - preRomanNum.value;
                i -= 2;
            } else {
                result += romanNum.value;
                i--;
            }
        }

        return result;
    }

    enum RomanNum {
        I('I', 1),
        V('V', 5),
        X('X', 10),
        L('L', 50),
        C('C', 100),
        D('D', 500),
        M('M', 1000);

        char c;
        int value;

        RomanNum(char c, int value) {
            this.c = c;
            this.value = value;
        }

        public static RomanNum getInstance(char c) {
            for (RomanNum romanNum : RomanNum.values()) {
                if (c == romanNum.c) {
                    return romanNum;
                }
            }
            return null;
        }

        public RomanNum before() {
            switch (this) {
                case I:
                    return null;
                case V:
                case X:
                    return I;
                case L:
                case C:
                    return X;
                case D:
                case M:
                    return C;
                default:
                    return null;
            }
        }
    }


    @Test
    public void test() {
        Assert.assertTrue("assert1", romanToInt("III") == 3);
        Assert.assertTrue("assert2", romanToInt("IV") == 4);
        Assert.assertTrue("assert3", romanToInt("IX") == 9);
        Assert.assertTrue("assert4", romanToInt("LVIII") == 58);
        Assert.assertTrue("assert5", romanToInt("MCMXCIV") == 1994);
    }

}
