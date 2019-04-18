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

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-04-18
 */
public class _0155MinStack {

    /**
     * 最小元素
     */
    private int minEle;

    /**
     * 存放元素
     */
    private List<Integer> list;

    /** initialize your data structure here. */
    public _0155MinStack() {
        list = new ArrayList<>();
        minEle = Integer.MAX_VALUE;
    }

    public void push(int x) {
        list.add(x);
        if (x < minEle){
            // 更新最小元素
            minEle = x;
        }
    }

    public void pop() {
        if (list.size() == 0) {
            throw new RuntimeException("No element in stack");
        }
        Integer removeInt = list.remove(list.size() - 1);
        if (removeInt == minEle) {
            // 如果移除了最小元素，重新寻找最小元素
            minEle = findMin();
        }
    }

    public int top() {
        if (list.size() == 0) {
            throw new RuntimeException("No element in stack");
        }
        return list.get(list.size() - 1);
    }

    public int getMin() {
        if (list.size() == 0) {
            throw new RuntimeException("No element in stack");
        }
        return minEle;
    }

    private int findMin() {
        int min = Integer.MAX_VALUE;
        for (Integer x: list) {
            if (x < min) {
                min = x;
            }
        }
        return min;
    }


    @Test
    public void test() {
        _0155MinStack stack = new _0155MinStack();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        Assert.assertTrue(stack.getMin() == -3);
        stack.pop();
        Assert.assertTrue(stack.top() == 0);
        Assert.assertTrue(stack.getMin() == -2);
    }
}
