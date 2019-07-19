package leetcode.algorithms;

/*
 * @lc app=leetcode id=168 lang=java
 *
 * [168] Excel Sheet Column Title
 *
 * https://leetcode.com/problems/excel-sheet-column-title/description/
 *
 * algorithms
 * Easy (28.64%)
 * Total Accepted:    168.4K
 * Total Submissions: 585.2K
 * Testcase Example:  '1'
 *
 * Given a positive integer, return its corresponding column title as appear in
 * an Excel sheet.
 *
 * For example:
 *
 *
 * ⁠   1 -> A
 * ⁠   2 -> B
 * ⁠   3 -> C
 * ⁠   ...
 * ⁠   26 -> Z
 * ⁠   27 -> AA
 * ⁠   28 -> AB
 * ⁠   ...
 *
 *
 * Example 1:
 *
 *
 * Input: 1
 * Output: "A"
 *
 *
 * Example 2:
 *
 *
 * Input: 28
 * Output: "AB"
 *
 *
 * Example 3:
 *
 *
 * Input: 701
 * Output: "ZY"
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-18
 */
public class _0168ExcelSheetColumnTitle {

    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = n;
            if (n > 26) {
                remainder = n % 26;
            }
            sb.append((char) (remainder + 64));
            if (n <= 26) {
                break;
            }
            n = n / 26;
        }
        return sb.reverse().toString();
    }


    @Test
    public void test() {
        Assert.assertTrue(convertToTitle(1).equals("A"));
        Assert.assertTrue(convertToTitle(28).equals("AB"));
        Assert.assertTrue(convertToTitle(701).equals("ZY"));
    }
}
