package com.fan.algo.StackAndQueue;

import java.util.List;
import java.util.Stack;
//https://leetcode.cn/problems/implement-queue-using-stacks/
public class ImplementQueueUsingStacks {
}

class MyQueue {

    private Stack<Integer> in = new Stack<Integer>();
    private Stack<Integer> out = new Stack<Integer>();

    public MyQueue() {

    }

    public void push(int x) {
        in.push(x);
    }

    public int pop() {  //从队列的开头移除并返回元素

        if (!out.empty()){
            return out.pop();
        }

        while (!in.empty()) {
            out.push(in.pop());
        }
        return out.pop();
    }

    public int peek() { //返回队列开头的元素
        if (!out.empty()){
            return out.peek();
        }

        while (!in.empty()) {
            out.push(in.pop());
        }
        return out.peek();
    }

    public boolean empty() {
        return in.empty() && out.empty();
    }
}