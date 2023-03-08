package com.fan.algo;

import java.util.*;

public class NC61 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            String[] split = input.split(",");
            int[] ints = new int[split.length - 1];
            int target = Integer.parseInt(split[split.length - 1]);
            for (int i = 0; i < split.length - 1; i++) {
                String s = split[i].replace("[", "").replace("]", "");
                ints[i] = Integer.parseInt(s);
            }
            for (int i : twoSum(ints, target)) {
                System.out.println(i);
            }
        }
    }

    public static int[] twoSum(int[] numbers, int target) {
        // write code here
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            int gap = target - numbers[i];
            if (map.containsKey(gap)) {
                res[0] = map.get(gap);
                res[1] = i + 1;
            } else {
                map.put(numbers[i], i + 1);
            }
        }
        return res;
    }
}
