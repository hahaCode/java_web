package com.fan.algo.SlidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://leetcode.cn/problems/find-all-anagrams-in-a-string/submissions/
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        FindAllAnagramsInAString findAllAnagramsInAString = new FindAllAnagramsInAString();
        findAllAnagramsInAString.findAnagrams("cbaebabacd", "abc");
    }

    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> window = new HashMap<>();

        Map<Character, Integer> need = new HashMap<>();  // 存p中的每一个字符的个数

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        List<Integer> res = new ArrayList<>();

        while (right < s.length()) {
            char c = s.charAt(right);

            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {  //窗口中的字符 c 的个数和 need中字符数量一样， 表示有一种字符数量达标了
                    valid++;
                }
            }
            right++;

            while (valid == need.size() && right - left >= p.length()) { //窗口中所有字符数量都达标
                if (right - left == p.length())
                    res.add(left);  // 把窗口中的字串的起始位置加入到结果集合中
                char d = s.charAt(left); // 即将被移除窗口的字符
                left++;

                if (window.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return res;
    }
}
