package com.fan.algo.StackAndQueue;

import java.util.Stack;

//https://leetcode.cn/problems/min-stack/
public class MinStack {

    private Stack<Integer> stack = new Stack<>();
    private int min = Integer.MAX_VALUE;
    public MinStack() {

    }

    public void push(int val) {
        if (val < min) min = val;
        stack.push(val);
    }

    public void pop() {
        if (min == stack.peek()) {
            min = Integer.MAX_VALUE;
        }
        stack.pop();
        setMin();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    private void setMin(){
        for (int i = 0; i < stack.size(); i++) {
            if (stack.get(i) < min)
                min = stack.get(i);
        }
    }
}
