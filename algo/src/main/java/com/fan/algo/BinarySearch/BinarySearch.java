package com.fan.algo.BinarySearch;

/**
 * https://leetcode.com/problems/binary-search/
 */
public class BinarySearch {
    public static void main(String[] args) {
        BS_Solution solution = new BS_Solution();
        int res = solution.search(new int[]{-1,0,3,5,9,12}, 2);
        System.out.println(res);
    }
}

class BS_Solution {
    public int search(int[] nums, int target) {
        int result = -1;

        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        while (left <= right) {
            if (target == nums[mid]) {
                result = mid;
                break;
            } else if (target > nums[mid]) {
                left = mid + 1;
                mid = (left + right) / 2;
            } else {
                right = mid - 1;
                mid = (left + right) / 2;
            }
        }

        return result;
    }
}