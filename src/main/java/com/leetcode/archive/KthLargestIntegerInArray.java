package com.leetcode.archive;

// https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/

import java.util.*;

// You are given an array of strings nums and an integer k.
// Each string in nums represents an integer without leading zeros.
//
// Return the string that represents the kth largest integer in nums.
//
// Note: Duplicate numbers should be counted distinctly.
// For example, if nums is ["1","2","2"], "2" is the first largest integer,
// "2" is the second-largest integer, and "1" is the third-largest integer.
public class KthLargestIntegerInArray {

    public static void main(String[] args) {
        test(new String[]{"429", "28", "43", "390", "328"}, 3, "328");
        test(new String[]{"1"}, 1, "1");
        test(new String[]{"1", "2"}, 1, "2");
        test(new String[]{"1", "2"}, 2, "1");
        test(new String[]{"1", "2", "3"}, 1, "3");
        test(new String[]{"3", "6", "7", "10"}, 4, "3");
        test(new String[]{"2", "21", "12", "1"}, 3, "2");
        test(new String[]{"0", "0"}, 2, "0");
    }

    private static void test(String[] nums, int k, String expected) {
        System.out.println(Arrays.toString(nums));
        String actual = kthLargestNumber(nums, k);
        System.out.println(k);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    public static String kthLargestNumber(String[] nums, int k) {
        k = nums.length - k;
        Arrays.sort(nums, KthLargestIntegerInArray::compare);
        return nums[k];
    }

    private static int compare(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return s1.compareTo(s2);
        }
        return s1.length() - s2.length();
    }

    static Map<String, Map<String, Integer>> cache = new HashMap<>();

    private static int cacheCompare(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return s1.length() - s2.length();
        }
        Map<String, Integer> stringIntegerMap = cache.get(s1);
        if (stringIntegerMap != null) {
            Integer integer = stringIntegerMap.get(s2);
            if (integer != null) {
                return integer;
            }
        }
        if (s1 == s2) {
            return 0;
        }
        int res = s1.compareTo(s2);
        if (s1.length() > 4) {
            if (stringIntegerMap == null) {
                stringIntegerMap = new HashMap<>();
                cache.put(s1, stringIntegerMap);
            }
            stringIntegerMap.put(s2, res);
        }
        return res;
    }

    public static String kthLargestNumberQuickSelect(String[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            nums[i].intern();
        }
        k = nums.length - k;
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex < rightIndex) {
            int pivotIndex = pivot(nums, leftIndex, rightIndex);
            if (pivotIndex == k) {
                return nums[k];
            }
            if (k < pivotIndex) {
                rightIndex = pivotIndex - 1;
            } else {
                leftIndex = pivotIndex + 1;
            }
        }
        return nums[k];
    }

    private static int pivot(String[] nums, int leftIndex, int rightIndex) {
        int rand = (int) ((Math.random() * (rightIndex - leftIndex)) + leftIndex);
        String pivot = nums[rand];
        nums[rand] = nums[rightIndex];
        nums[rightIndex] = pivot;
        int pivotIndex = rightIndex;
        rightIndex--;
        while (leftIndex < rightIndex) {
            String left = nums[leftIndex];
            String right = nums[rightIndex];
            if (compare(left, pivot) > 0 && compare(right, pivot) <= 0) {
                nums[leftIndex] = right;
                nums[rightIndex] = left;
                continue;
            }
            if (compare(left, pivot) <= 0) {
                leftIndex++;
                continue;
            }
            if (compare(right, pivot) > 0) {
                rightIndex--;
            }
        }
        if (compare(nums[leftIndex], pivot) > 0) {
            nums[pivotIndex] = nums[leftIndex];
            nums[leftIndex] = pivot;
            pivotIndex = leftIndex;
        }
        return pivotIndex;
    }
}
