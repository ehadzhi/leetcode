package com.leetcode.archive;

// https://leetcode.com/problems/continuous-subarray-sum/

import java.util.*;

// Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
//
// A good subarray is a subarray where:
//
// its length is at least two, and
// the sum of the elements of the subarray is a multiple of k.
// Note that:
//
// A subarray is a contiguous part of the array.
// An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
public class SubarraySumMultipleOfK {

    public static void main(String[] args) {
        test(new int[]{5, 0, 0, 0}, 3, true);
        test(new int[]{23, 2, 4, 6, 7}, 6, true);
        test(new int[]{23, 2, 6, 4, 7}, 6, true);
        test(new int[]{23, 2, 6, 4, 7}, 13, false);
    }

    private static void test(int[] nums, int k, boolean expected) {
        boolean actual = checkSubarraySum(nums, k);

        System.out.println(Arrays.toString(nums));
        System.out.println(k);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
    }

    public static boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixRemainderToIndex = new HashMap<>();
        prefixRemainderToIndex.put(0, -1);

        int prefixRem = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixRem = (prefixRem + nums[i]) % k;
            Integer index = prefixRemainderToIndex.get(prefixRem);
            if (index != null && i - index > 1) {
                return true;
            }
            if (index == null) {
                prefixRemainderToIndex.put(prefixRem, i);
            }
        }
        return false;
    }
}
