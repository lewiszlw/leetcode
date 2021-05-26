package leetcode.algorithms;

//Given two non-negative integers num1 and num2 represented as strings, return t
//he product of num1 and num2, also represented as a string.
//
// Note: You must not use any built-in BigInteger library or convert the inputs
//to integer directly.
//
//
// Example 1:
// Input: num1 = "2", num2 = "3"
//Output: "6"
// Example 2:
// Input: num1 = "123", num2 = "456"
//Output: "56088"
//
//
// Constraints:
//
//
// 1 <= num1.length, num2.length <= 200
// num1 and num2 consist of digits only.
// Both num1 and num2 do not contain any leading zero, except the number 0 itsel
//f.
//
// Related Topics Math String
// 👍 2558 👎 1025

import org.junit.Assert;
import org.junit.Test;

public class _0043MultiplyStrings {

    /**
     * 解法：
     * 从右往左遍历乘数num2，将乘数的每一位与被乘数num1相乘得到对应的结果，再将每次得到的结果累加
     */
    public String multiply(String num1, String num2) {
        char[] num1Arr = num1.toCharArray();
        char[] num2Arr = num2.toCharArray();

        int[][] tmpRes = new int[num2Arr.length][num1Arr.length + num2Arr.length];

        // nums1Arr    1 2 3
        // nums2Arr  x 6 5 4
        //         ---------
        // tmpRes      4 9 2
        //           6 1 5 0
        //         7 3 8 0 0
        //         ---------
        // res     8 0 4 4 2

        // 从右往左遍历乘数的每一位与被乘数每一位相乘
        for (int i = num2Arr.length - 1; i >= 0; i--) {

            int[] tmp = new int[num1Arr.length + num2Arr.length];

            // 进位
            int flag = 0;

            int j = num1Arr.length - 1;
            for (; j >= 0; j--) {
                int x = (num2Arr[i] - '0') * (num1Arr[j] - '0');
                // 前面有进位
                x += flag;
                flag = 0;
                // 当前是否需要进位
                if (x > 9) {
                    flag = x / 10;
                }
                tmp[tmp.length - (num2Arr.length - i) - (num1Arr.length - j - 1)] = x % 10;
            }

            // 可能还有进位
            tmp[tmp.length - (num2Arr.length - i) - (num1Arr.length - j - 1)] = flag;

            tmpRes[i] = tmp;
        }

        // 对tmpRes每个数求和
        int[] res = new int[num1Arr.length + num2Arr.length];
        int flag = 0; // 进位
        int i = tmpRes[0].length - 1;
        for (; i >= 0; i--) {
            // 对第i列求和

            int sum = 0;
            for (int j = 0; j < tmpRes.length; j++) {
                sum += tmpRes[j][i];
            }
            // 前面有进位
            sum += flag;
            flag = 0;
            // 当前是否需要进位
            if (sum > 9) {
                flag = sum / 10;
            }
            res[i] = sum % 10;
        }
        // res[i] = flag; 这里不需要再处理进位，是因为num1和num2相乘结果一定不超过(nums1Arr.length + nums2Arr.length)位数

        // res数组转成string
        StringBuilder sb = new StringBuilder();
        boolean startWithZero = true;
        for (int j = 0; j < res.length; j++) {
            // 从非0开始
            if (startWithZero) {
                if (res[j] == 0) {
                    continue;
                } else {
                    startWithZero = false;
                }
            }
            sb.append((char) (res[j] + '0'));
        }
        // 结果为0情况
        if (sb.length() == 0) {
            sb.append("0");
        }
        return sb.toString();
    }

    @Test
    public void test() {
        Assert.assertEquals("80442", multiply("123", "654"));
        Assert.assertEquals("6", multiply("2", "3"));
        Assert.assertEquals("0", multiply("0", "3"));
        Assert.assertEquals("56088", multiply("123", "456"));
    }
}
