package com.fan.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//https://leetcode.cn/problems/generate-parentheses/
public class GenerateParentheses {

    List<String> res = new ArrayList<>();
    StringBuffer trace = new StringBuffer();

    String[] choice = new String[]{"(", ")"};

    public static void main(String[] args) {
        GenerateParentheses generateParentheses = new GenerateParentheses();
        generateParentheses.generateParenthesis(3);
        for (String re : generateParentheses.res) {
            System.out.println(re);
        }
    }

    public List<String> generateParenthesis(int n) {

        backtrace(0, 0, n);
        return res;
    }

    public void backtrace(int left, int right, int n) {
        if (left == n && right == n && validate(trace.toString()) && trace.length() == n * 2) {
            res.add(trace.toString());
        }

        for (String s : choice) {
            if (s.equals("(") && left <= n && trace.length() < n * 2) {
                trace.append(s);
                backtrace(left + 1, right, n);
            }

            if (s.equals(")") && right <= n && trace.length() < n * 2) {
                trace.append(s);
                backtrace(left, right + 1, n);
            }
        }
        if (trace.length() > 0)
            trace.deleteCharAt(trace.length() - 1);
    }

    public boolean validate(String s) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (!stack.isEmpty()) {
                if (stack.peek().equals("(") && s.charAt(i) == ')') {
                    stack.pop();
                } else {
                    stack.push(s.charAt(i) + "");
                }
            } else {
                stack.push(s.charAt(i) + "");
            }
        }

        return stack.empty();
    }
}
