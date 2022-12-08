package com.leetcode.archive;

import java.util.*;

//https://leetcode.com/problems/longest-consecutive-sequence/

// put them in a hashset
// if a number is the first in a sequence (i-1 is missing from the set)
// count how many subsequent are in the set
// return the largest num
// skip the others
public class LongestConsecutiveSequence {
    public static void main(String... s) {
        System.out.println(longestConsecutiveUnionFind(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public static int longestConsecutive(int[] nums) {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numbers.add(nums[i]);
        }
        int longest = 0;

        for (int number : numbers) {
            if (numbers.contains(number - 1)) {
                continue;
            }
            int current = 1;
            while (numbers.contains(number + 1)) {
                current++;
                number++;
            }
            if (current > longest) {
                longest = current;
            }
        }
        return longest;
    }

    public static int longestConsecutiveSort(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int longest = 1;
        int current = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                continue;
            }
            if (nums[i - 1] + 1 == nums[i]) {
                current++;
            } else {
                if (longest < current) {
                    longest = current;
                }
                current = 1;
            }
        }
        if (longest < current) {
            longest = current;
        }
        return longest;
    }

    public static int longestConsecutiveUnionFind(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Set<Integer>> partitions = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Set<Integer> p1 = partitions.get(nums[i] - 1);
            Set<Integer> p2 = partitions.get(nums[i]);
            Set<Integer> p3 = partitions.get(nums[i] + 1);
            if (p2 == null) {
                p2 = new HashSet<>();
                p2.add(nums[i]);
                partitions.put(nums[i], p2);
            }
            if (p1 != null) {
                p2.forEach(num -> partitions.put(num, p1));
                p1.addAll(p2);
            }
            if (p3 != null) {
                p2 = partitions.get(nums[i]);
                p2.forEach(num -> partitions.put(num, p3));
                p3.addAll(p2);
            }
        }
        return partitions.values().stream()
                .map(Set::size)
                .mapToInt(i -> i)
                .max().orElse(0);
    }
}
