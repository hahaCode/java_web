package com.fan.algo;

import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = n-1; j > i ; j--) {
                if (nums[i] == nums[j]) {
                    res = Math.max(res, j-i);
                }
            }
        }

        System.out.println(res);
    }
}
