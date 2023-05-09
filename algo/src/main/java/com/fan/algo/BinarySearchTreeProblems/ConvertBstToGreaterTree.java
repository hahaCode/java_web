package com.fan.algo.BinarySearchTreeProblems;

// https://leetcode.cn/problems/convert-bst-to-greater-tree/
public class ConvertBstToGreaterTree {

    // 传入二叉搜索树的根结点, 生成累加树，并返回根结点
    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    int sum = 0;
    // 降序遍历,然后统计目前已经遍历的节点的值和
    public void traverse(TreeNode root) {
        if (root == null) return;

        traverse(root.right);
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }
}
