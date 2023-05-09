package com.fan.algo.BinarySearchTreeProblems;

//https://leetcode.cn/problems/convert-sorted-array-to-binary-search-tree/
public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return buildBST(nums, 0, nums.length);
    }

    public TreeNode buildBST(int[] nums, int low, int high) {  // [low, high)

        if (low >= high) return null;

        int mid = (high - low) / 2 + low;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildBST(nums, low, mid);
        root.right = buildBST(nums, mid + 1, high);
        return root;
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
