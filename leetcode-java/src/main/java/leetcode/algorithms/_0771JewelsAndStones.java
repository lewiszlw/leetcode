package leetcode.algorithms;

/*
 * @lc app=leetcode id=771 lang=java
 *
 * [771] Jewels and Stones
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-09
 */
public class _0771JewelsAndStones {

    /**
     * 解法1
     * 两次遍历
     */
    public int numJewelsInStones(String J, String S) {
        // 利用hash O(1)复杂度加快遍历
        Map<Character, String> jMap = new HashMap<>(2 << 10);
        final String value = "a";
        for (int i = 0; i < J.length(); i++) {
            jMap.put(J.charAt(i), value);
        }
        int num = 0;
        for (int i = 0; i < S.length(); i++) {
            if (jMap.containsKey(S.charAt(i))) {
                num ++;
            }
        }
        return num;
    }

    /**
     * 解法2
     * 排序+双指针
     */
    public int numJewelsInStones2(String J, String S) {
        char[] charsJ = J.toCharArray();
        char[] charsS = S.toCharArray();
        Arrays.sort(charsJ);
        Arrays.sort(charsS);

        int j = 0, s = 0, num = 0;
        while (j < charsJ.length && s < charsS.length) {
            if (charsJ[j] == charsS[s]) {
                s ++;
                num ++;
            } else if (charsJ[j] < charsS[s]) {
                // 如a < b，此时S中肯定没有a字符
                j ++;
            } else if (charsJ[j] > charsS[s]) {
                // 如b > a，此时S中a字符不在J中
                s ++;
            }
        }
        return num;
    }

    /**
     * 解法3
     * 利用正则匹配去掉J中的字符
     */
    public int numJewelsInStones3(String J, String S) {
        // ^ 匹配输入字符串的开始位置，除非在方括号表达式中使用，此时它表示不接受该字符集合
        return S.replaceAll("[^" + J + "]", "").length();
    }


    @Test
    public void test() {
        Assert.assertTrue(numJewelsInStones("aA", "aAAbbbb") == 3);
        Assert.assertTrue(numJewelsInStones("z", "ZZ") == 0);
    }

    @Test
    public void test2() {
        Assert.assertTrue(numJewelsInStones2("aA", "aAAbbbb") == 3);
        Assert.assertTrue(numJewelsInStones2("z", "ZZ") == 0);
        Assert.assertTrue(numJewelsInStones2("", "") == 0);
    }

}
