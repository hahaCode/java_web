package com.fan.algo.BackTrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//https://leetcode.cn/problems/subsets/
public class Subsets {
    public static void main(String[] args) {

    }

    List<List<Integer>> subset = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {

        backtrack(nums, 0);
        return subset;
    }

    public void backtrack(int[] nums, int level) {

        subset.add(new ArrayList<>(track));

        for (int i = level; i < nums.length; i++) {
            track.add(nums[i]);
            backtrack(nums, i + 1);
            track.removeLast();
        }
    }
}
