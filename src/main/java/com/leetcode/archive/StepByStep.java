package com.leetcode.archive;

import java.util.*;

// https://leetcode.com/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
// given a root of a bin tree, each node has a unique value
// given a start and a finish value find the shortest path
// print directions U, L, R

// find path to star
// find path to finish
// compare and remove similarities, this gives us lca
// transform to needed result
public class StepByStep {
    public String getDirections(TreeNode root, int startValue, int destValue) {
        List<Character> pathToStart = find(root, startValue);
        List<Character> pathToDestination = find(root, destValue);

        int lcaIndex = 0;
        int toStartSize = pathToStart.size();
        int toDestSize = pathToDestination.size();
        for (int i = 0; i < toStartSize && i < toDestSize; i++) {
            if (pathToStart.get(toStartSize - i - 1) == pathToDestination.get(toDestSize - i - 1)) {
                lcaIndex++;
            } else {
                break;
            }
        }
        StringBuffer b = new StringBuffer(toStartSize - lcaIndex + toDestSize - lcaIndex);
        for (int i = lcaIndex; i < toStartSize; i++) {
            b.append('U');
        }
        for (int i = lcaIndex; i < toDestSize; i++) {
            b.append(pathToDestination.get(toDestSize - i - 1));
        }
        return b.toString();
    }

    public List<Character> find(TreeNode node, int value) {
        if (node == null) {
            return null;
        }
        if (node.val == value) {
            return new ArrayList<>();
        }
        List<Character> left = find(node.left, value);
        if (left != null) {
            left.add('L');
            return left;
        }
        List<Character> right = find(node.right, value);
        if (right != null) {
            right.add('R');
            return right;
        }
        return null;
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
