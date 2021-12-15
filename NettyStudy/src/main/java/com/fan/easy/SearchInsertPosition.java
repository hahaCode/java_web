package com.fan.easy;

public class SearchInsertPosition {
    public static void main(String[] args) {
        SIP_Solution1 solution1 = new SIP_Solution1();
        System.out.println(solution1.searchInsert(new int[]{1}, 0));
    }
}

class SIP_Solution1 {
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;

        while (low <= high) {
            int mid =  low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}