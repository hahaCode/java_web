package com.fan.algo;

//https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {

    }

    //传入二叉树的跟节点, 将其拉平为链表
    public void flatten(TreeNode root) {

        if (root == null) return;

        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;
        root.right = left;

        TreeNode head = root;
        while (head.right != null) {
            head = head.right;
        }

        head.right = right;

        /*  这样是错的, left有可能是空
        while (left.right != null) {
            left = left.right;
        }

        left.right = right;
        * */
    }

}