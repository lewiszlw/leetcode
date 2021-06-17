package leetcode.algorithms;

//字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
//
//
//
// 示例 1:
//
// 输入:
//first = "pale"
//second = "ple"
//输出: True
//
//
//
// 示例 2:
//
// 输入:
//first = "pales"
//second = "pal"
//输出: False
//
// Related Topics 字符串 动态规划
// 👍 70 👎 0

import java.util.Objects;

public class Lcci0105OneAway {

    public boolean oneEditAway(String first, String second) {
        if (Objects.equals(first, second)) {
            return true;
        }
        return false;
    }
}
