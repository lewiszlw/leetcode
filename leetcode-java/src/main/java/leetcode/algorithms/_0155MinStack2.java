package leetcode.algorithms;

/*
 * @lc app=leetcode id=155 lang=java
 *
 * [155] Min Stack
 *
 * https://leetcode.com/problems/min-stack/description/
 *
 * algorithms
 * Easy (36.10%)
 * Total Accepted:    286.9K
 * Total Submissions: 787.3K
 * Testcase Example:  '["MinStack","push","push","push","getMin","pop","top","getMin"]\n[[],[-2],[0],[-3],[],[],[],[]]'
 *
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 *
 *
 * push(x) -- Push element x onto stack.
 *
 *
 * pop() -- Removes the element on top of the stack.
 *
 *
 * top() -- Get the top element.
 *
 *
 * getMin() -- Retrieve the minimum element in the stack.
 *
 *
 *
 *
 * Example:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * 解法2：辅助栈
 *
 *  stack      min
 *    0        0
 *    2        1
 *    3        1
 *    1        1
 *    5        5
 * 1.值 v 入栈时，除了入栈stack外，同时入栈min，v与min栈顶元素top比较，如果v大于top，则入栈top到min，否则入栈v到min
 * 2.出栈时，除了stack栈顶元素出栈，同时min栈顶元素出栈
 *
 * P.S.为了简便直接用Java Stack类，主要理解辅助栈。
 */
public class _0155MinStack2 {

    Stack<Integer> stack;

    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public _0155MinStack2() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            int top = minStack.peek();
            int min = Math.min(top, val);
            minStack.push(min);
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }


    @Test
    public void test() {
        _0155MinStack2 stack = new _0155MinStack2();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        Assert.assertTrue(stack.getMin() == -3);
        stack.pop();
        Assert.assertTrue(stack.top() == 0);
        Assert.assertTrue(stack.getMin() == -2);
    }
}
