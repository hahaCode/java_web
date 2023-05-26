package com.fan.algo;

import java.util.*;
import java.util.stream.Collectors;

public class Problem3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String s1 = scanner.nextLine();
        // 给定的区间
        String[] split = s1.split(",");
        List<InterVal> intervals = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].replace("[", "").replace("]", "");
            if ((i+1)%2 == 0) {
                int start = Integer.valueOf(split[i-1]);
                int end = Integer.valueOf(split[i]);
                InterVal interVal = new InterVal(start, end);
                intervals.add(interVal);
            }
        }
        int res = intervals.size();
        // 按区间起点升序排序
        Collections.sort(intervals, new Comparator<InterVal>() {
            @Override
            public int compare(InterVal o1, InterVal o2) {
                return o1.start - o2.start;
            }
        });

//        for (int i = 0; i < intervals.size(); i++) {
//            System.out.println("["+intervals.get(i).start+ "," + intervals.get(i).end+"]");
//        }

        String s2 = scanner.nextLine();

        // 给定的连接器
        String[] split1 = s2.split(",");
        Map<Integer, Boolean> connector = new HashMap<>();
        for (int i = 0; i < split1.length; i++) {
            split1[i] = split1[i].replace("[", "").replace("]", "");
            connector.put(Integer.valueOf(split1[i]), false);  // 连接器都还没有使用
        }

        for (int i = 1; i < intervals.size(); i++) {
            if (intervals.get(i).start <= intervals.get(i-1).end) {
                // 区间本身就是重叠或者相邻的，不需要连接器
                res--;
            } else {
                // 需要连接
                int step = intervals.get(i).start - intervals.get(i-1).end;
                if (connector.containsKey(step)) {
                    res--;
                    connector.put(step, true);
                    continue;
                }
                for (Integer key : connector.keySet()) {
                    if (key > step && !connector.get(key)) {
                        res--;
                        connector.put(key, true);
                        break;
                    }
                }
            }
        }
        System.out.println(res);
    }
}

class InterVal {
    int start;
    int end;

    InterVal(){}

    InterVal(int start, int end) {
        this.start = start;
        this.end = end;
    }
}