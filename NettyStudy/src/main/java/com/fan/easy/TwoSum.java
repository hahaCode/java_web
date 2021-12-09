package com.fan.easy;

import java.util.HashMap;
import java.util.Map;

/**
 *  https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        int[] twoSum = solution2.twoSum(new int[]{2,7,11,15}, 9);

        for (int i : twoSum) {
            System.out.println(i);
        }
    }
}

class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // set the num[i] as the key, index as the value
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i]) && map.get(target-nums[i]) != i) {
                return new int[] {i, map.get(target-nums[i])};
            }
        }
        throw new IllegalArgumentException("No two sum solutions!");
    }
}

class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No two sum solutions!");
    }
}
