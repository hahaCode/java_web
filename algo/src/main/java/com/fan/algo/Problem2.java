package com.fan.algo;

import java.util.Objects;
import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        String[] position = s.split(" ");

        int res = 0;

        for (int i = 0; i < position.length; i++) {
            if (Objects.equals(position[i], "0")) {
                int count = 0;
                for (int left = i-1; left >= 0; left--) {
                    if (Objects.equals(position[left], "1")) count++;
                    else break;
                }

                for (int right = i+1; right < position.length; right++) {
                    if (Objects.equals(position[right], "1")) count++;
                    else break;
                }
                res  = Math.max(res, count);
            }
        }

        System.out.println(res);
    }
}
