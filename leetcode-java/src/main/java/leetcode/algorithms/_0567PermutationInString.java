package leetcode.algorithms;

//Given two strings s1 and s2, return true if s2 contains the permutation of s1.
//
//
// In other words, one of s1's permutations is the substring of s2.
//
//
// Example 1:
//
//
//Input: s1 = "ab", s2 = "eidbaooo"
//Output: true
//Explanation: s2 contains one permutation of s1 ("ba").
//
//
// Example 2:
//
//
//Input: s1 = "ab", s2 = "eidboaoo"
//Output: false
//
//
//
// Constraints:
//
//
// 1 <= s1.length, s2.length <= 104
// s1 and s2 consist of lowercase English letters.
//
// Related Topics Two Pointers Sliding Window
// 👍 2479 👎 79

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class _0567PermutationInString {

    /**
     * 解法1：
     * 从s2中拿到所有与s1等长度的子串，然后依次判断子串是否是s1的一种排列
     */
    public boolean checkInclusion(String s1, String s2) {
        // 从s2中获取所有s1长度的子串
        List<String> possibleSubstrings = getPossibleSubstrings(s2, s1.length());

        // char -> char出现频次 s1 map
        Map<Character, Integer> s1Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }

        // 遍历所有可能的子串
        for (int i = 0; i < possibleSubstrings.size(); i++) {
            String substring = possibleSubstrings.get(i);

            // char -> char出现频次 substring map
            Map<Character, Integer> substringMap = new HashMap<>();
            for (int j = 0; j < substring.length(); j++) {
                char c = substring.charAt(j);
                substringMap.put(c, substringMap.getOrDefault(c, 0) + 1);
            }

            // 比较s1 map 和 substring map
            if (s1Map.equals(substringMap)) {
                return true;
            }
        }
        return false;
    }

    private List<String> getPossibleSubstrings(String s, int subLen) {
        List<String> possibleSubstrings = new ArrayList<>();
        for (int i = 0; i + subLen <= s.length(); i++) {
            possibleSubstrings.add(s.substring(i, i + subLen));
        }
        return possibleSubstrings;
    }




    /**
     * 解法2：滑动窗口
     * 滑动窗口长度等同于s1长度，优化substringMap生成，当滑动窗口移动时，只需要从substringMap添加或移除元素即可
     */
    public boolean checkInclusion2(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        // char -> char出现频次 s1 map
        Map<Character, Integer> s1Map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            s1Map.put(c, s1Map.getOrDefault(c, 0) + 1);
        }

        // 定义双指针
        int left = 0, right = s1.length() - 1;

        // char -> char出现频次 滑动窗口substring map
        Map<Character, Integer> substringMap = new HashMap<>();
        for (int i = left; i <= right; i++) {
            char c = s2.charAt(i); // 需注意s2可能短于s1
            substringMap.put(c, substringMap.getOrDefault(c, 0) + 1);
        }

        while (right < s2.length()) {
            if (s1Map.equals(substringMap)) {
                return true;
            }

            // 从substringMap中移除left处的char
            char charAtLeft = s2.charAt(left);
            Integer originFreq = substringMap.get(charAtLeft);
            if (originFreq == 1) {
                substringMap.remove(charAtLeft);
            } else {
                substringMap.put(charAtLeft, originFreq - 1);
            }

            // 左指针右移
            left ++;

            // 右指针右移
            right ++;

            // substringMap中添加right处的char
            if (right < s2.length()) {
                char charAtRight = s2.charAt(right);
                substringMap.put(charAtRight, substringMap.getOrDefault(charAtRight, 0) + 1);
            }
        }
        return false;
    }




    /**
     * 解法3：滑动窗口优化
     * 维持一个diff变量来代表s1和s2子串之间字符频次不同的个数，而不需要全部比较两个子串，同时利用数组存储
     *
     * 窗口滑动，记一进一出两个字符为 x 和 y.
     * 若 x=y 则对diff无影响，可以直接跳过。
     * 若 x!=y 对于x和y，如修改前相同则diff加一，如修改后相同则diff减一
     */
    public boolean checkInclusion3(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        // cnt下标为char-'a'，值为char在子串和s1上的频次差值
        int[] cnt = new int[26]; // s1 s2只包含小写字母
        for (int i = 0; i < s1.length(); ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }

        // 统计差值
        int diff = 0;
        for (int c : cnt) {
            // 差值为0说明频次相同，差值非0则说明频次不同
            if (c != 0) {
                ++diff;
            }
        }

        // 当a-z每个的频次差值都为0，即diff为0，则说明s1和子串是同一排列
        if (diff == 0) {
            return true;
        }

        // 窗口移动
        for (int i = s1.length(); i < s2.length(); ++i) {
            // x代表新增元素，y代表删除的元素
            int x = s2.charAt(i) - 'a', y = s2.charAt(i - s1.length()) - 'a';
            if (x == y) {
                continue;
            }

            // 修改x前后判断频次差值，以决定diff加一还是减一
            if (cnt[x] == 0) {
                ++diff;
            }
            ++cnt[x];
            if (cnt[x] == 0) {
                --diff;
            }

            // 修改y前后判断频次差值，以决定diff加一还是减一
            if (cnt[y] == 0) {
                ++diff;
            }
            --cnt[y];
            if (cnt[y] == 0) {
                --diff;
            }

            if (diff == 0) {
                return true;
            }
        }
        return false;
    }



    @Test
    public void test() {
        Assert.assertTrue(checkInclusion("ab", "eidbaooo"));
        Assert.assertTrue(!checkInclusion("ab", "eidboaoo"));
    }

    @Test
    public void test2() {
        Assert.assertTrue(checkInclusion2("ab", "eidbaooo"));
        Assert.assertTrue(!checkInclusion2("ab", "eidboaoo"));
        Assert.assertTrue(!checkInclusion2("ab", "a"));
    }

    @Test
    public void test3() {
        Assert.assertTrue(checkInclusion3("ab", "eidbaooo"));
        Assert.assertTrue(!checkInclusion3("ab", "eidboaoo"));
        Assert.assertTrue(!checkInclusion3("ab", "a"));
    }
}
