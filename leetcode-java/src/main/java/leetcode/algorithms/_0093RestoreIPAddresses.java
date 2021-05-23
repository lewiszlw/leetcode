package leetcode.algorithms;

//Given a string s containing only digits, return all possible valid IP addresse
//s that can be obtained from s. You can return them in any order.
//
// A valid IP address consists of exactly four integers, each integer is between
// 0 and 255, separated by single dots and cannot have leading zeros. For example,
// "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.
//168.1.312" and "192.168@1.1" are invalid IP addresses.
//
//
// Example 1:
// Input: s = "25525511135"
//Output: ["255.255.11.135","255.255.111.35"]
// Example 2:
// Input: s = "0000"
//Output: ["0.0.0.0"]
// Example 3:
// Input: s = "1111"
//Output: ["1.1.1.1"]
// Example 4:
// Input: s = "010010"
//Output: ["0.10.0.10","0.100.1.0"]
// Example 5:
// Input: s = "101023"
//Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
//
//
// Constraints:
//
//
// 0 <= s.length <= 3000
// s consists of digits only.
//
// Related Topics String Backtracking
// 👍 1792 👎 566

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class _0093RestoreIPAddresses {

    /**
     * 解法：回溯法 + 剪枝
     * 利用三个分隔符切分字符串，当切分后的数不合格时，回溯到上一个分隔符，重新移动分隔符
     *
     * 注意切分的子串转成数字可能超过int最大值
     */
    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();

        // 三个分隔符 i, j, k 分别分隔得到 [0, i], [i + 1, j], [j + 1, k], [k + 1, s.length() - 1]
        for (int i = 0; i <= 2 && i < s.length(); i++) {

            // 第1个数[0, i]
            String num1Str = s.substring(0, i + 1);
            if (!isNumEligible(Long.parseLong(num1Str), i + 1)) {
                break;
            }

            for (int j = i + 1; j <= i + 3 && j < s.length(); j++) {

                // 第2个数[i + 1, j]
                String num2Str = s.substring(i + 1, j + 1);
                if (!isNumEligible(Long.parseLong(num2Str), j - i)) {
                    break;
                }

                for (int k = j + 1; k <= j + 3 && k < s.length() - 1; k++) {

                    // 第3个数[j + 1, k]
                    String num3Str = s.substring(j + 1, k + 1);
                    if (!isNumEligible(Long.parseLong(num3Str), k - j)) {
                        break;
                    }

                    // 第4个数[k + 1, s.length() - 1]
                    String num4Str = s.substring(k + 1);
                    if (!isNumEligible(Long.parseLong(num4Str), s.length() - k - 1)) {
                        // 第4个数超过255因为分隔符k太靠前，需要继续后移
                        continue;
                    }

                    res.add(generateIP(num1Str, num2Str, num3Str, num4Str));
                }
            }
        }

        return res;
    }

    // 判断该数是否合格，不超过255，不能有前导0，count代表有几位数
    private boolean isNumEligible(long num, int count) {
        if (num > 255) {
            return false;
        }
        if (count == 1) {
            return true;
        } else {
            int min = 1;
            for (int i = 1; i < count; i++) {
                min *= 10;
            }
            return num >= min;
        }
    }

    private String generateIP(String num1Str, String num2Str, String num3Str, String num4Str) {
        StringBuilder ip = new StringBuilder();
        ip.append(num1Str);
        ip.append(".");
        ip.append(num2Str);
        ip.append(".");
        ip.append(num3Str);
        ip.append(".");
        ip.append(num4Str);
        return ip.toString();
    }



    @Test
    public void test() {
        System.out.println(restoreIpAddresses("25525511135"));
        System.out.println(restoreIpAddresses("0000"));
        System.out.println(restoreIpAddresses("1111"));
        System.out.println(restoreIpAddresses("010010"));
        System.out.println(restoreIpAddresses("101023"));
    }
}
