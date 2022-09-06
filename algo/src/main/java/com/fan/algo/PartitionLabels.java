package com.fan.algo;

/**
 * https://leetcode.com/problems/partition-labels/
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
    public static void main(String[] args) {
        PL_Solution1 solution1 = new PL_Solution1();
        System.out.println(solution1.partitionLabels("eccbbbbdec"));
    }
}

class PL_Solution1 {
    public List<Integer> partitionLabels(String s) {

        List<Integer> result = new ArrayList<>();

        //将字符串s中每个字母出现的最后位置保存到map里
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charMap.put(s.charAt(i), i);
        }

        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, charMap.get(s.charAt(i))); // 找到目前所有遍历过的字母的最大的最后位置
            if (i == end) { // 可以截取了
                result.add(end - start + 1); // 当前截取部分的字符串长度
                start = i + 1; //下一部分开始的位置
            }
        }
        return result;
    }
}
