package com.fan.algo.BackTrace;

import java.util.ArrayList;
import java.util.List;

// https://leetcode.cn/problems/n-queens/
public class NQueens {
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        nQueens.solveNQueens(4);

        //System.out.println(nQueens.res.get(0).get(0) + " " + nQueens.res.get(0).get(1) + " " + nQueens.res.get(0).get(2) + "" + nQueens.res.get(0).get(3));
        //System.out.println(nQueens.res.get(1).get(0) + " " + nQueens.res.get(1).get(1) + " " + nQueens.res.get(1).get(2) + "" + nQueens.res.get(1).get(3));
    }

    List<List<String>> res = new ArrayList<>();
    char[][] table;

    public List<List<String>> solveNQueens(int n) {
        table = new char[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                table[i][j] = '.';
            }
        }

        backtrack(n, 0);
        return res;
    }

    public void backtrack(int n, int cur) {
        if (cur == n) {
            List<String> s = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String temp = "";
                for (int j = 0; j < n; j++) {
                    temp += table[i][j];
                }
                s.add(temp);
            }

            //System.out.println("===>" + s);
            res.add(s);
            return;
        }


        for (int j = 0; j < n; j++) {
            if (table[cur][j] == '.' && validate(cur, j, n)) {
                table[cur][j] = 'Q';
                backtrack(n, cur + 1);
                table[cur][j] = '.';
            }
        }

    }

    public boolean validate(int x, int y, int n) {

        //上
        for (int i = 0; i < x; i++) {
            if (table[i][y] == 'Q') return false;
        }

        //左上
        for (int i = x, j = y; i >= 0 && j >= 0; i--, j--) {
            if (table[i][j] == 'Q') return false;
        }

        //右上
        for (int i = x, j = y; i >= 0 && j < n; i--, j++) {
            if (table[i][j] == 'Q') return false;
        }

        return true;
    }
}
