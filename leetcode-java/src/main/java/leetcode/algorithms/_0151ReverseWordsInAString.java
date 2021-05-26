package leetcode.algorithms;

//Given an input string s, reverse the order of the words.
//
// A word is defined as a sequence of non-space characters. The words in s will
//be separated by at least one space.
//
// Return a string of the words in reverse order concatenated by a single space.
//
//
// Note that s may contain leading or trailing spaces or multiple spaces between
// two words. The returned string should only have a single space separating the w
//ords. Do not include any extra spaces.
//
//
// Example 1:
//
//
//Input: s = "the sky is blue"
//Output: "blue is sky the"
//
//
// Example 2:
//
//
//Input: s = "  hello world  "
//Output: "world hello"
//Explanation: Your reversed string should not contain leading or trailing space
//s.
//
//
// Example 3:
//
//
//Input: s = "a good   example"
//Output: "example good a"
//Explanation: You need to reduce multiple spaces between two words to a single
//space in the reversed string.
//
//
// Example 4:
//
//
//Input: s = "  Bob    Loves  Alice   "
//Output: "Alice Loves Bob"
//
//
// Example 5:
//
//
//Input: s = "Alice does not even like bob"
//Output: "bob like even not does Alice"
//
//
//
// Constraints:
//
//
// 1 <= s.length <= 104
// s contains English letters (upper-case and lower-case), digits, and spaces '
//'.
// There is at least one word in s.
//
//
//
// Follow up: Could you solve it in-place with O(1) extra space?
// Related Topics String
// 👍 1670 👎 3268

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class _0151ReverseWordsInAString {

    /**
     * 解法：
     * 利用String的split、trim和join，Collections的reverse方法
     * Java无法实现O(1)空间复杂度
     */
    public String reverseWords(String s) {
        String[] split = s.trim().split(" ");
        List<String> wordList = Arrays.asList(split);
        Collections.reverse(wordList);
        return wordList.stream().filter(word -> !"".equals(word)).collect(Collectors.joining(" "));
    }


    @Test
    public void test() {
        Assert.assertEquals("b a", reverseWords(" a     b "));
        Assert.assertEquals("blue is sky the", reverseWords("the sky is blue"));
        Assert.assertEquals("world! hello", reverseWords("  hello world!  "));
        Assert.assertEquals("example good a", reverseWords("a good   example"));
        Assert.assertEquals("example good a", reverseWords("a good   example"));
    }
}
