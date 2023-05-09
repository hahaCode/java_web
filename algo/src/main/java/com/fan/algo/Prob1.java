package com.fan.algo;

import java.util.*;
// TODO: error
public class Prob1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String s = in.nextLine();

            List<HashMap<String, Integer>> list = new ArrayList<>();
            for (int i = 0; i < s.length(); i++) {
                if (Character.isLetter(s.charAt(i))) {
                    StringBuffer buffer = new StringBuffer();
                    StringBuffer number = new StringBuffer();
                    for (int j = i; j < s.length(); j++) {
                        if (Character.isLetter(s.charAt(j))) {
                            buffer.append(s.charAt(j));
                        } else { //number
                            number.append(s.charAt(j));
                            if (j+1 < s.length() && Character.isLetter(s.charAt(j + 1))) {
                                i = j;
                                HashMap<String, Integer> map = new HashMap<>();
                                map.put(buffer.toString(), Integer.parseInt(number.toString()));
                                list.add(map);
                                break;
                            }

                            if ( j + 1 == s.length()) {
                                HashMap<String, Integer> map = new HashMap<>();
                                map.put(buffer.toString(), Integer.parseInt(number.toString()));
                                list.add(map);
                            }
                        }
                    }

                }
            }
            list.sort(new Comparator<HashMap<String, Integer>>() {
                @Override
                public int compare(HashMap<String, Integer> o1, HashMap<String, Integer> o2) {
                    if (o1.get(o1.keySet().toString().replace("[","").replace("]", "")) != o2.get(o2.keySet().toString().replace("[","").replace("]", ""))) {
                        return o1.get(o1.keySet().toString().replace("[","").replace("]", "")) - o2.get(o2.keySet().toString().replace("[","").replace("]", ""));
                    } else {
                       String s1 =  o1.keySet().toString();
                       String s2 =  o2.keySet().toString();
                        for (int i = 0, j = 0; i < s1.length() && j < s2.length() ; i++, j++) {
                            if (s1.charAt(i) != s2.charAt(j)) return s1.charAt(i) - s2.charAt(j);
                        }
                    }
                    return 0;
                }
            });

            for (HashMap<String, Integer> map : list) {
                for (String s1 : map.keySet()) {
                    for (int i = 0; i < map.get(s1); i++) {
                        System.out.print(s1);
                    }
                }
            }
            System.out.println();
        }
    }
}
