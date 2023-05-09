package com.fan.algo.BinaryTreeProblems;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//https://leetcode.cn/problems/path-sum-ii/
public class PathSumii {
    List<List<Integer>> res = new LinkedList<>();

    List<Integer> trace = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        travel(root, targetSum, 0);
        return res;
    }

    public void travel(TreeNode root, int target, int cur) {
        if (root == null) return;

        //记录路径上的值
        trace.add(root.val);

        //到达叶子节点且路径和满足要求
        if (cur + root.val == target && root.left == null && root.right == null) {
            res.add(new ArrayList<>(trace));
            trace.remove(trace.size() - 1);
            return;
        }

        travel(root.left, target, cur + root.val);
        travel(root.right, target, cur + root.val);
        trace.remove(trace.size() - 1);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}