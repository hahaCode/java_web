package com.fan.algo;

/**
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        FFALPOEISA_Solution1 solution1 = new FFALPOEISA_Solution1();
        for (int i : solution1.searchRange(new int[]{}, 6)) {
            System.out.println(i);
        }

    }
}

class FFALPOEISA_Solution1 {
    /**
     * 二分查找法, 分别查找target第一次出现的位置和最后一次出现的位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {

        int n = nums.length;
        int first = findFirst(nums, target, n);
        int last = findLast(nums, target, n);

        return new int[]{first, last};
    }

    private int findFirst(int nums[], int target, int n) {
        int first = -1;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + (high-low)/2; // 防止溢出
            if (nums[mid] >= target) {  // 想让结果尽量往左
                high = mid - 1;
            } else {
                low = mid + 1;
            }
            if (nums[mid] == target)
                first = mid;
        }

        return first;
    }


    private int findLast(int nums[], int target, int n) {
        int last = -1;
        int low = 0;
        int high = n-1;
        while (low <= high) {
            int mid = low + (high-low)/2; // 防止溢出
            if (nums[mid] <= target) {   // 想让结果尽量往右
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            if (nums[mid] == target)
                last = mid;
        }

        return last;
    }
}