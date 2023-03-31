package com.fan.algo;

import java.util.Arrays;

//https://leetcode.cn/problems/matchsticks-to-square/
//TODO TIMEOUT
public class MatchsticksToSquare {

    public static void main(String[] args) {
        MatchsticksToSquare matchsticksToSquare = new MatchsticksToSquare();
        System.out.println(matchsticksToSquare.makesquare(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));
    }

    boolean flag = false;
    boolean[] used;

    public boolean makesquare(int[] matchsticks) {
        if (matchsticks.length <= 3)
            return false;

        int sum = 0, edgeLength = 0;
        for (int i = 0; i < matchsticks.length; i++) {
            sum += matchsticks[i];
        }

        if (sum % 4 != 0) return false;

        edgeLength = sum / 4;

        Arrays.sort(matchsticks);
        used = new boolean[matchsticks.length];
        backtrace(matchsticks, edgeLength, 0, 0);
        return flag;
    }

    public void backtrace(int[] matchsticks, int target, int k, int cur) {

        if (validate(used)) {
            if (k == 4) flag = true;
            else flag = false;
            return;
        }

        for (int i = 0; i < matchsticks.length; i++) {
//            if (!used[i] && i == matchsticks[matchsticks.length-1] && cur+matchsticks[i] < target){
//                return;
//            }
            if (matchsticks[i]+cur > target) return;

            if (!used[i] && cur+matchsticks[i] < target) {  //火柴没用过  而且现在这条边还不够
                used[i] = true;
                backtrace(matchsticks, target, k, cur+matchsticks[i]);
                used[i] = false;
            }

            if (!used[i] && cur+matchsticks[i] == target) { //火柴没用过  而且现在这条边刚刚够
                used[i] = true;
                backtrace(matchsticks, target, k+1, 0);
                used[i] = false;
            }
        }
    }

    public boolean validate(boolean[] a) {
        for (int i = 0; i < a.length; i++) {
            if (!a[i]) return false;
        }
        return true;
    }
}
