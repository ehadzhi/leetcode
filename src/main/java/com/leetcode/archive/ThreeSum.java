package com.leetcode.archive;
//https://leetcode.com/problems/3sum/description/

import java.util.*;
import java.util.stream.Collectors;

// Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
// such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
//
//        Notice that the solution set must not contain duplicate triplets.
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        Map<Integer, Integer> reverseLookup = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            reverseLookup.put(nums[i], i);
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                Integer matchIndex = reverseLookup.get(-sum);
                if (matchIndex != null && matchIndex > j) {
                    List<Integer> triplet = Arrays.asList(
                            nums[i], nums[j], nums[matchIndex]);
                    Collections.sort(triplet);
                    result.add(triplet);
                }
            }
        }
        return new ArrayList<>(result);
    }
}