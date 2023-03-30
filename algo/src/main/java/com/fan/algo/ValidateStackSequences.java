package com.fan.algo;

import java.util.Stack;

public class ValidateStackSequences {
    public static void main(String[] args) {
        int[] pushed = new int[]{1, 2, 3, 4, 5};
        int[] popped = new int[]{4, 5, 3, 2, 1};
        System.out.println(validateStackSequences(pushed, popped));
    }

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        int[] stack = new int[pushed.length];

        int index1 = 0, index2 = 0;
        for (int i : pushed) {
            stack[index1] = i;
            while (index1 >= 0 && popped[index2] == stack[index1]) {
                index1--;  // pop
                index2++;
            }
            index1++;
        }

        return index1 == 0 ? true : false;
    }

}
