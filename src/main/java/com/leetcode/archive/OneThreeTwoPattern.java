package com.leetcode.archive;

// https://leetcode.com/problems/132-pattern/

// Given an array of n integers nums,
// a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
// such that i < j < k and nums[i] < nums[k] < nums[j].
//
// Return true if there is a 132 pattern in nums, otherwise, return false.

// n == nums.length
// 1 <= n <= 2 * 10^5
// -10^9 <= nums[i] <= 10^9

import java.util.LinkedList;

public class OneThreeTwoPattern {

    public static void main(String[] args) {
        System.out.println(find132pattern(new int[]{1, 4, 0, -1, -2, -3, -1, -2}));
        System.out.println(find132pattern(new int[]{3, 1, 4, 2}));
    }

    public static boolean find132pattern(int[] nums) {

        LinkedList<Integer> stack = new LinkedList<>();
        boolean valid2Found = false;
        int largestValid2 = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            if (valid2Found && num < largestValid2) {
                return true;
            }
            while (!stack.isEmpty() && num > stack.getFirst()) {
                largestValid2 = stack.removeFirst();
                valid2Found = true;
            }
            stack.addFirst(num);
        }
        return false;
    }
}
