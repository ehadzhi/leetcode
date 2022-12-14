package com.leetcode.archive;

import java.util.Arrays;

// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

// Suppose an array of length n sorted in ascending order is rotated between 1 and n times.
// For example, the array nums = [0,1,2,4,5,6,7] might become:
// [4,5,6,7,0,1,2] if it was rotated 4 times.
// Given the sorted rotated array nums of unique elements, return the minimum element of this array.
// You must write an algorithm that runs in O(log n) time.
public class MinimumInRotatedSortedArray {
    public static void main(String[] args) {
        test(new int[]{1}, 1);
        test(new int[]{2, 1}, 1);
        test(new int[]{11, 13, 15, 17}, 11);
        test(new int[]{3, 4, 5, 1, 2}, 1);
        test(new int[]{4, 5, 6, 7, 0, 1, 2}, 0);

    }

    private static void test(int[] nums, int expected) {
        int actual = findMin(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        if (nums[left] <= nums[right]) {
            return nums[left];
        }
        while (left + 1 != right) {
            int mid = (right - left) / 2 + left;
            if (nums[left] > nums[mid]) {
                right = mid;
                continue;
            }
            if (nums[mid] > nums[right]) {
                left = mid;
            }
        }
        return nums[right];
    }
}
