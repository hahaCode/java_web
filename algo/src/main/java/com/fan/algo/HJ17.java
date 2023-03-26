package com.fan.algo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HJ17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) { // 注意 while 处理多个 case
            String s = in.nextLine();
            List<String> list = new ArrayList<>();
            for (String s1 : s.split(";")) {
                if (isValid(s1))
                    list.add(s1);
            }

            int x = 0, y = 0;
            for (String s1 : list) {
                if (s1.charAt(0) == 'A') {
                    x -= Integer.parseInt(s1.substring(1, s1.length()));
                } else if (s1.charAt(0) == 'D') {
                    x += Integer.parseInt(s1.substring(1, s1.length()));
                } else if (s1.charAt(0) == 'W') {
                    y += Integer.parseInt(s1.substring(1, s1.length()));
                } else {
                    y -= Integer.parseInt(s1.substring(1, s1.length()));
                }
            }
            System.out.println(x+","+y);
        }
    }

    public static boolean isValid(String s) {
        if (s != null && !s.equals("") && (s.charAt(0) == 'A' || s.charAt(0) == 'S' || s.charAt(0) == 'W' || s.charAt(0) == 'D')) {
            String step = s.substring(1, s.length());
            try {
                Integer.parseInt(step);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
