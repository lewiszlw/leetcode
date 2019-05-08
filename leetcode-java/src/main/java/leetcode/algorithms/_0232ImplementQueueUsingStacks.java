package leetcode.algorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Desc:
 *
 * @author zhanglinwei02
 * @date 2019-05-07
 */
public class _0232ImplementQueueUsingStacks {

    /**
     * 两个栈实现队列
     */
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    /**
     * true: 栈顶元素为最后进入的元素(正序)
     * false: 栈顶元素为最先进入的元素(反序)
     */
    private boolean sequence = true;

    /** Initialize your data structure here. */
    public _0232ImplementQueueUsingStacks() {
        stack1 = new Stack();
        stack2 = new Stack();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if (!sequence) {
            // 非正序，先反转栈元素顺序
            reverse();
        }
        // 往非空栈压入元素，均为空则随便选一个
        if (!stack1.empty()) {
            stack1.push(x);
        } else if (!stack2.empty()) {
            stack2.push(x);
        } else {
            stack1.push(x);
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        emptyCheck();
        if (sequence) {
            // 正序，反转顺序，栈顶即为最新进入的元素
            reverse();
        }
        return stack1.empty()? stack2.pop(): stack1.pop();
    }

    /** Get the front element. */
    public int peek() {
        emptyCheck();
        if (sequence) {
            // 正序，反转顺序，栈顶即为最新进入的元素
            reverse();
        }
        return stack1.empty()? stack2.peek(): stack1.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }

    /** 反转栈元素顺序 */
    public void reverse() {
        if (stack1.empty() && !stack2.empty()) {
            // 把栈2移到栈1反转
            while (!stack2.empty()) {
                stack1.push(stack2.pop());
            }
            sequence = !sequence;
        } else if (!stack1.empty() && stack2.empty()){
            // 把栈1移到栈2反转
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            sequence = !sequence;
        } else {
            // 栈1、栈2均为空
            sequence = true;
        }
    }

    private void emptyCheck() {
        if (empty()) {
            throw new RuntimeException("queue is empty");
        }
    }


    @Test
    public void test() {
        _0232ImplementQueueUsingStacks queue = new _0232ImplementQueueUsingStacks();
        queue.push(1);
        queue.push(2);
        Assert.assertTrue(queue.peek() == 1);
        Assert.assertTrue(queue.pop() == 1);
        Assert.assertTrue(!queue.empty());
    }
}
