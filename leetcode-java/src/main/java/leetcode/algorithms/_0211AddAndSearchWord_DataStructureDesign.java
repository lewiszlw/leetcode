package leetcode.algorithms;

/*
 * @lc app=leetcode id=211 lang=java
 *
 * [211] Add and Search Word - Data structure design
 *
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/description/
 *
 * algorithms
 * Medium (29.54%)
 * Likes:    854
 * Dislikes: 54
 * Total Accepted:    117.5K
 * Total Submissions: 383.6K
 * Testcase Example:  '["WordDictionary","addWord","addWord","addWord","search","search","search","search"]\n[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]'
 *
 * Design a data structure that supports the following two operations:
 *
 *
 * void addWord(word)
 * bool search(word)
 *
 *
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or .. A . means it can represent any one
 * letter.
 *
 * Example:
 *
 *
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 *
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-06-26
 */
public class _0211AddAndSearchWord_DataStructureDesign {

    private TrieNode root;

    /** Initialize your data structure here. */
    public _0211AddAndSearchWord_DataStructureDesign() {
        root = new TrieNode('-', false);
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        while (word != null && word.length() > 0) {
            char c = word.charAt(0);
            if (node.children[c - 'a'] == null) {
                node.children[c - 'a'] = new TrieNode(c, false);
            }
            if (word.length() == 1) {
                node.children[c - 'a'].end = true;
            }
            node = node.children[c - 'a'];
            word = word.substring(1);
        }
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        List<TrieNode> nodes = Arrays.asList(root);
        while (word != null && word.length() > 0) {
            char c = word.charAt(0);
            List<TrieNode> matchChildren = match(c, nodes);
            if (matchChildren == null) {
                return false;
            }
            if (word.length() == 1 && containEnd(matchChildren)) {
                return true;
            }
            word = word.substring(1);
            nodes = matchChildren;
        }
        return false;
    }

    /**
     * 找出 nodes 中每个节点的子节点中匹配 c 的节点
     */
    private List<TrieNode> match(char c, List<TrieNode> nodes) {
        List<TrieNode> matchChildren = new ArrayList<>();
        for (TrieNode node : nodes) {
            if (node == null) {
                continue;
            }
            if (c == '.') {
                addChildren(matchChildren, node);
                continue;
            }
            if (node.children[c - 'a'] != null && node.children[c - 'a'].c == c) {
                matchChildren.add(node.children[c - 'a']);
            }
        }
        return matchChildren;
    }

    /**
     * 判断是否含有end节点
     */
    private boolean containEnd(List<TrieNode> nodes) {
        for (TrieNode node : nodes) {
            if (node != null && node.end) {
                return true;
            }
        }
        return false;
    }

    private void addChildren(List<TrieNode> result, TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                result.add(child);
            }
        }
    }

    /**
     * 字典树节点
     */
    class TrieNode {
        char c;
        boolean end;
        TrieNode[] children;
        TrieNode(char c, boolean end) {
            this.c = c;
            this.end = end;
            children = new TrieNode[26];
        }
    }


    @Test
    public void test() {
        _0211AddAndSearchWord_DataStructureDesign wordDictionary = new _0211AddAndSearchWord_DataStructureDesign();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        Assert.assertTrue(!wordDictionary.search("pad"));
        Assert.assertTrue(wordDictionary.search("bad"));
        Assert.assertTrue(wordDictionary.search(".ad"));
        Assert.assertTrue(wordDictionary.search("b.."));

    }
}
