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
        test(new String[]{"423", "521", "2", "42"}, 2, "423");
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

    public static String kthLargestNumberSort(String[] nums, int k) {
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

    public static String kthLargestNumber(String[] nums, int k) {
        k = nums.length - k;
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex < rightIndex) {
            int lastSmallerIndex = pivot(nums, leftIndex, rightIndex);
            if (lastSmallerIndex < k) {
                leftIndex = lastSmallerIndex + 1;
            } else {
                rightIndex = lastSmallerIndex;
            }
        }
        return nums[leftIndex];
    }

    private static int pivot(String[] nums, int leftIndex, int rightIndex) {
        String pivot = nums[(rightIndex - leftIndex) / 2 + leftIndex];
        leftIndex--;
        rightIndex++;
        while (true) {
            do {
                leftIndex++;
            } while (compare(nums[leftIndex], pivot) < 0);
            do {
                rightIndex--;
            } while (compare(nums[rightIndex], pivot) > 0);
            if (leftIndex >= rightIndex) {
                return rightIndex;
            }
            String swap = nums[leftIndex];
            nums[leftIndex] = nums[rightIndex];
            nums[rightIndex] = swap;
        }
    }
}
