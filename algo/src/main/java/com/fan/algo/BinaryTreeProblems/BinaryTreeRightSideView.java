package com.fan.algo.BinaryTreeProblems;

import com.fan.algo.BinaryTreeProblems.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// https://leetcode.cn/problems/binary-tree-right-side-view/
public class BinaryTreeRightSideView {

    // 层序遍历ß
    public List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int nodeNum = q.size();

            for (int i = 0; i < nodeNum; i++) {
                TreeNode cur = q.poll(); // 取出当前节点

                if (i == nodeNum-1) { // 每层的最后一个节点就是要加入res的节点
                    res.add(cur.val);
                }

                if (cur.left != null)
                    q.offer(cur.left);
                if (cur.right != null)
                    q.offer(cur.right);
            }
        }

        return res;
    }
    /*

        // 输入一棵二叉树的根节点，层序遍历这棵二叉树
void levelTraverse(TreeNode root) {
    if (root == null) return;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);

    // 从上到下遍历二叉树的每一层
    while (!q.isEmpty()) {
        int sz = q.size();
        // 从左到右遍历每一层的每个节点
        for (int i = 0; i < sz; i++) {
            TreeNode cur = q.poll();
            // 将下一层节点放入队列
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
    }
}

    */
}
