package com.fan.algo.SlidingWindow;

import java.util.HashMap;
import java.util.Map;
//https://leetcode.cn/problems/permutation-in-string/
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s2.length()) {

            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;  // 窗口中的字符 c 的个数达标
                }
            }

            while (right-left >= s1.length()) { //窗口中的字串长度等于或者超过了s1 说明要开始缩小窗口了
                if (right-left == s1.length() && valid == need.size())
                    return true;

                char d = s2.charAt(left); // 即将被移出窗口的字符
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d)-1);
                }
            }
        }
        return false;
    }
}
