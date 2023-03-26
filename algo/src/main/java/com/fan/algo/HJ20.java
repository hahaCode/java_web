package com.fan.algo;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Pattern;
// TODO NOT Ac
public class HJ20 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextLine()) {
            boolean b = isValidPassWord(in.nextLine());
            System.out.println(b ? "OK" : "NG");
        }
    }

    public static boolean isValidPassWord(String password) {
        if (password.length() <= 8)
            return false;

        int c = 0;
        Pattern p1 = Pattern.compile("[0-9]");
        Pattern p2 = Pattern.compile("[A-Z]");
        Pattern p3 = Pattern.compile("[a-z]");
        Pattern p4 = Pattern.compile("[^a-zA-Z0-9]");
        if (p1.matcher(password).find()) c++;
        if (p2.matcher(password).find()) c++;
        if (p3.matcher(password).find()) c++;
        if (p4.matcher(password).find()) c++;

        if (c < 3) return false;

        for (int i = 0; i < password.length(); i++) {
            for (int j = i + 2; j < password.length(); j++) {
                if (password.charAt(i) == password.charAt(j) && i + 1 < password.length()
                        && j + 1 < password.length() && password.charAt(i + 1) == password.charAt(j + 1))
                    return false;
            }
        }
        return true;
    }
}
