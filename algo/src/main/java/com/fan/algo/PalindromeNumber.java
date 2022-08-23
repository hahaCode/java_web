package com.fan.algo;

/**
 * https://leetcode.com/problems/palindrome-number/
 */

public class PalindromeNumber {
    public static void main(String[] args) {
        PN_Solution1 solution1 = new PN_Solution1();
        System.out.println(solution1.isPalindrome(-101));
    }
}

class PN_Solution1 {
    public boolean isPalindrome(int x) {
        int index = 0;
        int[] nums = new int[12];
        if ( x<0) {
            return false;
        } else if (x <= 9 && x >= 0) { //个位是回文数
            return true;
        } else {
            while (x != 0) {
                nums[index++] = x % 10;
                x = x / 10;
            }

            int i, j;
            for (i = 0, j = index-1; j > i; i++, j--) {

                if (nums[i] != nums[j])
                    return false;
            }
        }
        return true;
    }
}

class PN_Solution2 {
    public boolean isPalindrome(int x) {
        if (x == 0)
            return true;
        if (x < 0)
            return false;
        if (x % 10 == 0) //最后一位是0
            return false;

        //如果这个数的位数是偶数  后半部分和前半部分的值应该一样
        int sum = 0;
        while (x > sum) {
            sum = sum * 10 + x % 10;
            x = x/10;
        }
        //如果这个数的位数是奇数 12321  --> x=12 sum = 123
        return (sum == x) || (x == sum/10);
    }
}