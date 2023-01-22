package com.leetcode.archive;

// https://leetcode.com/problems/count-good-nodes-in-binary-tree/

// Given a binary tree root, a node X in the tree is named good
// if in the path from root to X there are no nodes with a value greater than X.
//
// Return the number of good nodes in the binary tree.

// df ez
public class CountGoodNodesBinTree {

    public static int goodNodes(TreeNode root) {
        return goodNodes(root, root.val);
    }

    public static int goodNodes(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }
        int good = 0;
        if (root.val >= max) {
            max = root.val;
            good = 1;
        }
        return good + goodNodes(root.left, max) + goodNodes(root.right, max);
    }


    public class TreeNode {
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
}
