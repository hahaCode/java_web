package com.fan.algo;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class NC68 {
    //static int res = 0 ;
    //static List<List<Integer>> res = new LinkedList<>();

    //static LinkedList<Integer> track = new LinkedList<>();
    public static int res = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            res = 0;
            System.out.println(jumpFloor(n));

//            for (int i = 0; i < res.size(); i++) {
//                System.out.println(res.get(i));
//            }
        }
    }

    //    public static int jumpFloor(int target) {
//        if (target <= 1)
//            return 1;
//        return jumpFloor(target-1) + jumpFloor(target-2);
//    }
    public static int jumpFloor(int target) {
        backtrack(target, 0);
        return res;
    }

    public static void backtrack(int target, int sum) {
        if (target == sum) {
            //res.add(new LinkedList<>(track));
            res++;
            return;
        }
        for (int i = 1; i <= 2; i++) {
            sum += i;


            if (target >= sum) {
                //track.addLast(i);
                backtrack(target, sum);

                sum -= i;
                //track.removeLast();
            } else return;
        }
    }
}
