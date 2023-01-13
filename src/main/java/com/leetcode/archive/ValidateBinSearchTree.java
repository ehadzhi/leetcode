package com.leetcode.archive;

// https://leetcode.com/problems/validate-binary-search-tree/

// Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
// A valid BST is defined as follows:
//
// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.

public class ValidateBinSearchTree {
    public static void main(String[] args) {
        System.out.println(new ValidateBinSearchTree().isValidBST(new TreeNode(0)));
    }

    int prev = 0;
    boolean first = true;

    public boolean isValidBST(TreeNode root) {
        if (root != null) {
            boolean leftIsValid = isValidBST(root.left) && (root.val > prev || first);
            prev = root.val;
            first = false;
            return leftIsValid && isValidBST(root.right);
        }
        return true;
    }

    public static class TreeNode {
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
