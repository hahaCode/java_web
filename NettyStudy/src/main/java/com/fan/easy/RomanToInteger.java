package com.fan.easy;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {
        RTI_Solution1 solution1 = new RTI_Solution1();
        System.out.println(solution1.romanToInt("MCMXCIV"));
    }
}

class RTI_Solution1 {
    /**
     * 思路: 比较一个字符的右边的字符比它本身表示的大还是小, 如果大, 则为正值, 否则为负值
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if(i < s.length()-1 && map.get(s.charAt(i)) <  map.get(s.charAt(i+1))) {
                result -= map.get(s.charAt(i));
            } else {
                result += map.get(s.charAt(i));
            }
        }
        return result;
    }
}