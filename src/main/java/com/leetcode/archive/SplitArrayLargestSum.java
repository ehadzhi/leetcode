package com.leetcode.archive;

// https://leetcode.com/problems/split-array-largest-sum/

// Given an integer array nums and an integer k,
// split nums into k non-empty subarrays such that the largest sum of any subarray is minimized.
//
// Return the minimized largest sum of the split.
//
// A subarray is a contiguous part of the array.

import java.util.Arrays;

// 1 <= nums.length <= 1000
// 0 <= nums[i] <= 10^6
// 1 <= k <= min(50, nums.length)
public class SplitArrayLargestSum {
    public static void main(String[] args) {
        test(new int[]{0}, 1, 0);
        test(new int[]{1}, 1, 1);
        test(new int[]{0, 0}, 1, 0);
        test(new int[]{0, 1}, 1, 1);
        test(new int[]{1, 1}, 1, 2);
        test(new int[]{1, 1}, 2, 1);
        test(new int[]{7, 2, 5, 10, 8}, 2, 18);
        test(new int[]{1, 2, 3, 4, 5}, 2, 9);

    }

    private static void test(int[] nums, int k, int expected) {
        int actual = splitArray(nums, k);
        System.out.println(Arrays.toString(nums));
        System.out.println(k);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int splitArray(int[] nums, int k) {
        int maxNum = 0;
        int sumNums = 0;
        for (int i = 0; i < nums.length; i++) {
            sumNums += nums[i];
            if (maxNum < nums[i]) {
                maxNum = nums[i];
            }
        }

        int low = maxNum;
        int high = sumNums;

        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (isPossible(nums, mid, k)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    private static boolean isPossible(int[] nums, int maxSum, int k) {
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (currSum + nums[i] > maxSum) {
                currSum = nums[i];
                k--;
                if (k <= 0) {
                    return false;
                }
            } else {
                currSum += nums[i];
            }
        }
        return true;
    }
}
