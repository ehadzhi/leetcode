package com.leetcode.archive;

// https://leetcode.com/problems/construct-string-from-binary-tree/

// Given the root of a binary tree, construct a string consisting of parenthesis and integers
// from a binary tree with the preorder traversal way, and return it.
//
// Omit all the empty parenthesis pairs that do not affect the one-to-one
// mapping relationship between the string and the original binary tree.

public class ConstructStringFromBinaryTree {

    public String tree2str(TreeNode root) {
        if (root == null) {
            return "";
        }
        String left = tree2str(root.left);
        String right = tree2str(root.right);
        if (!right.isEmpty()) {
            right = "(" + right + ")";
            left = "(" + left + ")";
        } else {
            if (!left.isEmpty()) {
                left = "(" + left + ")";
            }
        }
        return root.val + left + right;
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
