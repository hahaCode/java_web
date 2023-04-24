package com.fan.algo;

//public class LowestCommonAncestorOfABinaryTree {
//    TreeNode res = null;
//
//    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
//        travel(root, p, q);
//        return res;
//    }
//
//    // 判断以root为节点的树 是否包含p或者q
//    public boolean travel(TreeNode root, TreeNode p, TreeNode q) {
//        if (root == null) return false;
//
//        boolean left = travel(root.left, p, q);
//        boolean right = travel(root.right, p, q);
//
//        if ((root.val == p.val && (left || right)) || (root.val == q.val && (left || right)) || left && right) {
//            res = root;
//        }
//
//        return left || right || root.val == q.val || root.val == p.val;
//    }
//}

public class LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        } else if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        }
        return null;
    }
}
