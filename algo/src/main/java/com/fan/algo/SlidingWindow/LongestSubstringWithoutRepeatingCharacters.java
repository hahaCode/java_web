package com.fan.algo.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.cn/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;

        int res = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0)+1); // 加入窗口中
            right++;

            while (window.get(c) > 1) {  // 不含有重复字符，所以窗口中的某一个字符数量大于1 就该缩小窗口了
                char d = s.charAt(left);
                left++;
                window.put(d, window.get(d)-1);

                if (c == d) {
                    break;
                }
            }
            if (left < right) {
                res = Math.max(res, right-left);
            }
        }
        return res;
    }
}
