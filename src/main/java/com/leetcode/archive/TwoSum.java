package com.leetcode.archive;

//https://leetcode.com/problems/two-sum/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
//You may assume that each input would have exactly one solution, and you may not use the same element twice.

// can be done in one pass as well if we merge the two for cycles
public class TwoSum {
    public static void main(String... args) {

    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> lookup = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer exactAddIndex = lookup.get(target - nums[i]);
            if (exactAddIndex != null) {
                return new int[]{i, exactAddIndex};
            }
            lookup.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer exactAddIndex = lookup.get(target - nums[i]);
            if (exactAddIndex != null && exactAddIndex != i) {
                return new int[]{i, exactAddIndex};
            }
        }
        return new int[0];
    }
}
