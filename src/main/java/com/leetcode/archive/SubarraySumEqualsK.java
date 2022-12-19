package com.leetcode.archive;

// https://leetcode.com/problems/subarray-sum-equals-k/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Given an array of integers nums and an integer k,
// return the total number of subarrays whose sum equals to k.
//
// A subarray is a contiguous non-empty sequence of elements within an array.
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        test(new int[]{1, 1, 1}, 2, 2);
        test(new int[]{1, 2, 3}, 3, 2);
    }

    private static void test(int[] nums, int k, int expected) {
        int actual = subarraySum(nums, k);
        System.out.println(Arrays.toString(nums));
        System.out.println(k);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int subarraySum(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> prefixSums = new HashMap<>();
        prefixSums.put(0, 1);
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            res += prefixSums.getOrDefault(prefixSum - k, 0);
            Integer prefixSumFreq = prefixSums.getOrDefault(prefixSum, 0);
            prefixSumFreq++;
            prefixSums.put(prefixSum, prefixSumFreq);
        }
        return res;
    }

    public static int subarraySumN2(int[] nums, int k) {
        int res = 0;
        int[][] sums = new int[2][nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (k == nums[i]) {
                res++;
            }
            sums[1][i] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int resArray = i % 2;
                sums[resArray][j] = nums[j] + sums[1 - resArray][j - 1];
                if (sums[resArray][j] == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
