package com.leetcode.archive;

// https://leetcode.com/problems/contains-duplicate/

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> present = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!present.add(nums[i])) {
                return true;
            }
        }
        return false;
    }
}
