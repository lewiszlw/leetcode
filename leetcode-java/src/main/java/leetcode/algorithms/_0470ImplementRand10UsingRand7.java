package leetcode.algorithms;

//Given the API rand7() that generates a uniform random integer in the range [1,
// 7], write a function rand10() that generates a uniform random integer in the ra
//nge [1, 10]. You can only call the API rand7(), and you shouldn't call any other
// API. Please do not use a language's built-in random API.
//
// Each test case will have one internal argument n, the number of times that yo
//ur implemented function rand10() will be called while testing. Note that this is
// not an argument passed to rand10().
//
// Follow up:
//
//
// What is the expected value for the number of calls to rand7() function?
// Could you minimize the number of calls to rand7()?
//
//
//
// Example 1:
// Input: n = 1
//Output: [2]
// Example 2:
// Input: n = 2
//Output: [2,8]
// Example 3:
// Input: n = 3
//Output: [3,8,10]
//
//
// Constraints:
//
//
// 1 <= n <= 105
//
// Related Topics Random Rejection Sampling
// 👍 695 👎 235

import org.junit.Test;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */

public class _0470ImplementRand10UsingRand7 {

    /**
     * 解法1：拒绝采样
     * 在拒绝采样中，如果生成的随机数满足要求，那么就返回该随机数，否则会不断生成直到一个满足要求的随机数为止。
     *
     * 大的随机数生成小的随机数
     * 如 rand10 -> rand7，只需要用 rand10 生成等概率的 1 ~ 10 ，然后判断生成的随机数 num ，如果 num <= 7 ，则返回即可。
     * 生成概率是等概率的。
     *
     * 小的随机数生成大的随机数
     * 结论：(randX() - 1) * Y + randY() 可以等概率的生成的随机数范围是 [1, X*Y]
     *
     * 以 rand7 得到 rand10，即 (rand7() - 1) * 7 + rand7()，
     * (rand7() - 1) * 7  的集合是  {0，7，14，21，28，35，42}，
     * rand7()            的集合是  {1, 2, 3, 4, 5, 6, 7}，
     * 两个独立事件相加，结果集为      {1, 2, 3, 4, 5, 6, 7, 8, 9, ... , 45, 46, 47, 48}。
     */
    public int rand10() {
        int num = (rand7() - 1) * 7 + rand7();
        while (num > 10) {
            // 取结果集 {1,2,3,...,46,47,48} 中的 {1,2,3,...,8,9,10}
            num = (rand7() - 1) * 7 + rand7();
        }
        return num;
    }


    /**
     * 解法2：解法1的优化
     * 解法1只取 {1,2,3,...,46,47,48} 中的 {1,2,3,...,8,9,10}，大部分随机数浪费
     * 解法2改成取 {1,2,3,...,46,47,48} 中的 {1,2,3,...,38,39,40}，然后对10取余得到 {0,1,2,...,7,8,9}，然后加1得到 {1,2,3,...,8,9,10}
     */
    public int rand10_2() {
        int num = (rand7() - 1) * 7 + rand7();
        while (num > 40) {
            // 取结果集 {1,2,3,...,46,47,48} 中的 {1,2,3,...,38,39,40}
            num = (rand7() - 1) * 7 + rand7();
        }
        return num % 10 + 1;
    }

    private int rand7() {
        return (int) (Math.random() * 7) + 1;
    }


    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            System.out.println(rand10());
        }
    }
}
