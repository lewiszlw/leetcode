package leetcode.algorithms;

//Given a string s and a dictionary of strings wordDict, return true if s can be
// segmented into a space-separated sequence of one or more dictionary words.
//
// Note that the same word in the dictionary may be reused multiple times in the
// segmentation.
//
//
// Example 1:
//
//
//Input: s = "leetcode", wordDict = ["leet","code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
//
//
// Example 2:
//
//
//Input: s = "applepenapple", wordDict = ["apple","pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pe
//n apple".
//Note that you are allowed to reuse a dictionary word.
//
//
// Example 3:
//
//
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: false
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 300
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 20
// s and wordDict[i] consist of only lowercase English letters.
// All the strings of wordDict are unique.
//
// Related Topics Dynamic Programming
// 👍 6746 👎 32

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _0139WordBreak {

    /**
     * 解法：动态规划
     *
     * dp[i] 代表 [0, i] 子串能否分解成单词
     *     dp[i] = check(j + 1, i),           j = -1
     *             dp[j] && check(j + 1, i),  0 <= j < i
     *
     *  check(j + 1, i) 结果是 [j + 1, i]子串是否是一个单词
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // 转成hash表
        Set<String> wordSet = new HashSet<>(wordDict);

        boolean[] dp = new boolean[s.length()];
        if (wordSet.contains(s.substring(0, 1))) {
            dp[0] = true;
        } else {
            dp[0] = false;
        }

        for (int i = 1; i < s.length(); i++) {
            for (int j = -1; j < i; j++) {
                if (j == -1) {
                    // 第1种情况, j == -1
                    if (check(j + 1, i, wordSet, s)) {
                        dp[i] = true;
                        break;
                    }
                } else {
                    // 第2种情况, 0 <= j < i
                    if (dp[j] && check(j + 1, i, wordSet, s)) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[dp.length - 1];
    }

    private boolean check(int start, int end, Set<String> wordSet, String s) {
        if (wordSet.contains(s.substring(start, end + 1))) {
            return true;
        } else {
            return false;
        }
    }


    @Test
    public void test() {
        Assert.assertTrue(wordBreak("i", Arrays.asList("i")));
        Assert.assertTrue(wordBreak("leetcode", Arrays.asList("leet", "code")));
        Assert.assertTrue(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        Assert.assertTrue(!wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
