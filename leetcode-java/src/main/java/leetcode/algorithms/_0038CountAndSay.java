package leetcode.algorithms;

/*
 * @lc app=leetcode id=38 lang=java
 *
 * [38] Count and Say
 *
 * https://leetcode.com/problems/count-and-say/description/
 *
 * algorithms
 * Easy (39.84%)
 * Total Accepted:    270.3K
 * Total Submissions: 676.2K
 * Testcase Example:  '1'
 *
 * The count-and-say sequence is the sequence of integers with the first five
 * terms as following:
 *
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 *
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 *
 * Given an integer n where 1 ≤ n ≤ 30, generate the n^th term of the
 * count-and-say sequence.
 *
 * Note: Each term of the sequence of integers will be represented as a
 * string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: 1
 * Output: "1"
 *
 *
 * Example 2:
 *
 *
 * Input: 4
 * Output: "1211"
 *
 */

import org.junit.Assert;
import org.junit.Test;

/**
 * Desc:
 * 题目读了半天，看了中文题目才懂
 *  1.     1
 *  2.     11
 *  3.     21
 *  4.     1211
 *  5.     111221
 *  解释题目：
 *  当前值是读上一个sequence的读法
 *  1 读作1，即1
 *  2 读作1个1，即11
 *  3 读作2个1，即21
 *  4 读作1个2，1个1，即1211
 *  5 读作1个1，1个2，2个1，即111221
 *
 *  递归思想：n的值是n-1的读法
 *
 * @author zhanglinwei02
 * @date 2019-04-10
 */
public class _0038CountAndSay {

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        String preSeq = countAndSay(n-1);
        StringBuilder seq = new StringBuilder();
        char tmp = preSeq.charAt(0);
        // 记录同一字符tmp出现字数
        int count = 0;
        for (int i = 0; i < preSeq.length(); i++) {
            if (preSeq.charAt(i) == tmp) {
                count ++;
            } else {
                // count and say
                seq.append(count + String.valueOf(tmp));
                // 重置count和tmp, 此时已经计数1
                count = 1;
                tmp = preSeq.charAt(i);
            }
        }
        // 收尾
        seq.append(count != 0? count + String.valueOf(tmp): "");
        return seq.toString();
    }


    @Test
    public void test() {
        Assert.assertTrue("1".equals(countAndSay(1)));
        Assert.assertTrue("11".equals(countAndSay(2)));
        Assert.assertTrue("21".equals(countAndSay(3)));
        Assert.assertTrue("1211".equals(countAndSay(4)));
        Assert.assertTrue("111221".equals(countAndSay(5)));
    }
}
