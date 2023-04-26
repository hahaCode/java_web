package com.fan.algo;

// https://leetcode.cn/problems/delete-node-in-a-bst/
public class DeleteNodeInABst {
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) return null;

        if (root.val == key) {
            //找到节点进行删除

            // 被删除的节点为叶子节点，直接删除； 或者有一边子树为空, 则另外一边顶替被删除节点的位置
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // 被删除的节点既有左子树也有右子树, 就要找左子树最大值或者右子树最小值来顶替被删除节点的位置, 这里用左子树最大值来顶替

//            TreeNode newRoot = getBSTMax(root.left);
//            root.left = deleteNode(root.left, newRoot.val); // 别忘了删除这个节点
//
//            newRoot.left = root.left;
//            newRoot.right = root.right;
//            root = newRoot;

            // 被删除的节点既有左子树也有右子树, 就要找左子树最大值或者右子树最小值来顶替被删除节点的位置, 这里用右子树最小值来顶替
            TreeNode newRoot = getBSTMin(root.right);
            root.right = deleteNode(root.right, newRoot.val); // 别忘了删除这个节点

            newRoot.left = root.left;
            newRoot.right = root.right;
            root = newRoot;

        } else if (root.val > key) {  // key在左子树
            root.left = deleteNode(root.left, key);
        } else { // root.val < key
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    public TreeNode getBSTMax(TreeNode root) {

        if (root == null) return null;

        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    public TreeNode getBSTMin(TreeNode root) {

        if (root == null) return null;

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }
}
