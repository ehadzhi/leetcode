package com.leetcode.archive;

// https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/

// You are given an array of integers nums and an integer target.
//
// Return the number of non-empty subsequences of nums such that the sum of the minimum
// and maximum element on it is less or equal to target. Since the answer may be too large,
// return it modulo 109 + 7.

import java.util.Arrays;

//         i-j-1
// 1 - 1 - -1
// 2 - 1 - 0
// 3 - 2 - 1
// 4 - 4 - 2
// 5 - 8 - 3
public class NumberSubsequencesWithGivenSum {
    public static void main(String[] args) {
        System.out.println(numSubseq(new int[]{7, 10, 7, 3, 7, 5, 4}, 12));
        System.out.println(numSubseq(new int[]{3, 3, 6, 8}, 10));
        System.out.println(numSubseq(new int[]{3, 5, 6, 7}, 9));
    }

    public static int numSubseq(int[] nums, int target) {
        int modulo = 1_000_000_007;
        int[] powDP = new int[nums.length];
        powDP[0] = 1;
        for (int i = 1; i < powDP.length; i++) powDP[i] = (powDP[i - 1] * 2) % modulo;
        Arrays.sort(nums);

        int res = 0;
        int prevMaxIndexBound = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (i >= prevMaxIndexBound) {
                return res;
            }
            int min = nums[i];
            int theoreticalMax = target - min;
            int maxIndex = Arrays.binarySearch(nums, i, prevMaxIndexBound, theoreticalMax);
            if (maxIndex < 0) {
                maxIndex = -maxIndex - 2;
            }
            while (maxIndex + 1 < nums.length && nums[maxIndex + 1] <= theoreticalMax) {
                maxIndex++;
            }
            if (maxIndex < i) {
                return res;
            }
            int numParticipantsThatCanBePresentOrNot = maxIndex - i;
            int possible = powDP[numParticipantsThatCanBePresentOrNot];
            res = (res + possible) % modulo;

            prevMaxIndexBound = maxIndex + 1;
        }
        return res;
    }
}
