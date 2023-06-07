package com.fan.algo.BinarySearch;

public class ZaiPaiXuShuZuZhongChaZhaoShuZilcof {

    public static void main(String[] args) {
        ZaiPaiXuShuZuZhongChaZhaoShuZilcof p = new ZaiPaiXuShuZuZhongChaZhaoShuZilcof();
        System.out.println(p.search(new int[]{1}, 0));
    }

    public int search(int[] nums, int target) {
        int leftBound = searchLeftBound(nums, target);
        int rightBound = searchRightBound(nums, target);

        System.out.println("left: " + leftBound + ", right: " + rightBound);

        if (leftBound != -1 && rightBound != -1)
            return rightBound - leftBound + 1;
        return 0;
    }

    public int searchLeftBound(int[] nums, int target) {

        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else { // nums[mid] < target
                left = mid + 1;
            }
        }

        if (left == nums.length) return -1;
        return nums[left] == target ? left : -1;
    }

    public int searchRightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else { // nums[mid] < target
                left = mid + 1;
            }
        }

        if (left - 1 < 0) return -1;
        return nums[left - 1] == target ? left - 1 : -1;
    }
}
