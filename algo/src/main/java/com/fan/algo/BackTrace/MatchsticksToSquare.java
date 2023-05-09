package com.fan.algo.BackTrace;

import java.util.Arrays;

//https://leetcode.cn/problems/matchsticks-to-square/
public class MatchsticksToSquare {

    public static void main(String[] args) {
        MatchsticksToSquare matchsticksToSquare = new MatchsticksToSquare();
        System.out.println(matchsticksToSquare.makesquare(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}));
    }

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

        //降序排序
        Arrays.sort(matchsticks);

        used = new boolean[matchsticks.length];
        return backtrace(matchsticks, edgeLength, 1, 0, matchsticks.length-1);
    }

    /**
     * @param matchsticks 火柴数组
     * @param target      每条边的长度
     * @param k           现在到第几个桶里
     * @param cur         当前这个桶里已经有的值
     */
    public boolean backtrace(int[] matchsticks, int target, int k, int cur, int start) {

        if (k == 4) {   //前三条边都满足, 第四条边肯定也满足
            return true;
        }

        //进入下一个边
        if (cur == target) {
            return backtrace(matchsticks, target, k + 1, 0, matchsticks.length-1);
        }

        //从长的火柴开始尝试
        for (int i = start; i >= 0; i--) {
            if (used[i] || matchsticks[i] + cur > target) continue;

            used[i] = true;
            if (backtrace(matchsticks, target, k, cur + matchsticks[i], i-1)) return true;
            used[i] = false;

            while (i > 0 && matchsticks[i] == matchsticks[i - 1]) {
                i--;
            }
        }

        return false;
    }
}
