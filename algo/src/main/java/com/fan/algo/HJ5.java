package com.fan.algo;

import java.util.Scanner;

//https://www.nowcoder.com/practice/8f3df50d2b9043208c5eed283d1d4da6?tpId=37&tqId=21228&rp=1&ru=/exam/oj/ta&qru=/exam/oj/ta&sourceUrl=%2Fexam%2Foj%2Fta%3FtpId%3D37&difficulty=undefined&judgeStatus=undefined&tags=&title=
public class HJ5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    if (s.startsWith("0x")) {
                        int index = 2;
                        int r = 0;
                        for (int i = index; i < s.length(); i++) {
                            if (s.charAt(i) == 'A' || s.charAt(i) == 'a') {
                                r += 10 * Math.pow(16, s.length() - i - 1);
                            } else if (s.charAt(i) == 'B' || s.charAt(i) == 'b') {
                                r += 11 * Math.pow(16, s.length() - i - 1);
                            } else if (s.charAt(i) == 'C' || s.charAt(i) == 'c') {
                                r += 12 * Math.pow(16, s.length() - i - 1);
                            } else if (s.charAt(i) == 'D' || s.charAt(i) == 'd') {
                                r += 13 * Math.pow(16, s.length() - i - 1);
                            } else if (s.charAt(i) == 'E' || s.charAt(i) == 'e') {
                    r += 14 * Math.pow(16, s.length() - i - 1);
                } else if (s.charAt(i) == 'F' || s.charAt(i) == 'f') {
                    r += 15 * Math.pow(16, s.length() - i - 1);
                } else {
                    r += Integer.parseInt(s.charAt(i) + "") * Math.pow(16, s.length() - i - 1);
                }
            }
            System.out.println(r);
        }
    }
}
