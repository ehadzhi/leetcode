package com.leetcode.archive;

import java.util.Arrays;

//https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

// undulating binary search
public class TwoSumSortedArray {
    public static void main(String... args) {
        int[] ints = twoSum(new int[]{3, 24, 50, 79, 88, 150, 345}, 200);
        System.out.println(ints[0] + ", " + ints[1]);
    }

    public static int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int sum = numbers[left] + numbers[right];
        while (sum != target) {
            if (sum > target) {
                int perfectMatch = target - numbers[left];
                int perfectMatchIndex = Arrays.binarySearch(numbers, left + 1, right, perfectMatch);
                if (perfectMatchIndex >= 0) {
                    right = perfectMatchIndex;
                } else {
                    int firstLarger = -perfectMatchIndex - 1;
                    right = firstLarger - 1;
                }
            } else {
                int perfectMatch = target - numbers[right];
                int perfectMatchIndex = Arrays.binarySearch(numbers, left + 1, right, perfectMatch);
                if (perfectMatchIndex >= 0) {
                    left = perfectMatchIndex;
                } else {
                    int firstLarger = -perfectMatchIndex - 1;
                    left = firstLarger;
                }
            }
            sum = numbers[left] + numbers[right];
        }
        return new int[]{left + 1, right + 1};
    }

    public static int[] twoSumb(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        int leftLeastBound = left;
        int rightBiggestBound = right;
        int sum = numbers[left] + numbers[right];
        boolean leftIsSearching = true;
        while (sum != target) {
            if (sum > target) {
                if (leftIsSearching) {
                    left = ((left - leftLeastBound) / 2) + leftLeastBound;
                    if (left == leftLeastBound) {
                        leftIsSearching = false;
                        continue;
                    }
                } else {
                    rightBiggestBound = right;
                    right = (right - left) / 2 + left;
                }
            } else {
                if (leftIsSearching) {
                    leftLeastBound = left;
                    left = ((right - left) / 2) + left;
                } else {
                    right = ((rightBiggestBound - right) / 2) + right + 1;
                    if (right == rightBiggestBound) {
                        right--;
                        rightBiggestBound--;
                        leftIsSearching = true;
                        continue;
                    }
                }
            }
            sum = numbers[left] + numbers[right];
        }
        return new int[]{left + 1, right + 1};
    }
}
