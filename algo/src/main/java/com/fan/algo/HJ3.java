package com.fan.algo;

import java.util.*;

public class HJ3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别

        int[] nums = new int[505];
        int n = in.nextInt();
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            nums[num] = 1;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 1)
                System.out.println(i);
        }
    }
}
