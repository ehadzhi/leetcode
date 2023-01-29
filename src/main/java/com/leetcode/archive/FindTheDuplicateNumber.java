package com.leetcode.archive;

// https://leetcode.com/problems/find-the-duplicate-number/

import sun.security.util.BitArray;

import java.util.Arrays;
import java.util.BitSet;

// Given an array of integers nums containing n + 1 integers
// where each integer is in the range [1, n] inclusive.
//
// There is only one repeated number in nums, return this repeated number.
//
// You must solve the problem without modifying the array nums and uses only constant extra space.
// 1 <= n <= 105
//nums.length == n + 1
//1 <= nums[i] <= n
//All the integers in nums appear only once except for precisely one integer which appears two or more times.
public class FindTheDuplicateNumber {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{5, 2, 3, 4, 1, 1}));
        System.out.println(findDuplicate(new int[]{1, 2, 3, 4, 2, 2}));
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
    }

    public static int findDuplicate(int[] nums) {
        int tortoise = nums[0];
        int hare = nums[0];

        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        tortoise = nums[0];

        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }


    // my solution
    // satisfies all enforced limitations, depending if new BitSet(10_000);
    // can be considered constant extra space :D
    // bit set of 10^5 ~ 10^5/8/10^3 = 12.5 kilobytes
    // 1  2  3  4  5  6  7  8  9  10
    // 1 -1  2 -2  3 -3  4 -4
    public static int findDuplicateBitSet(int[] nums) {
        BitSet present = new BitSet(10_000);
        int checksum = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            present.set(num);
            if (num % 2 == 0) {
                checksum -= num;
            } else {
                checksum += num;
            }
        }

        int repeats = 0;
        for (int i = 1; i <= nums.length; i++) {
            boolean iPresense = present.get(i);
            if (!iPresense) {
                repeats++;
                if (i % 2 == 0) {
                    checksum -= i;
                } else {
                    checksum += i;
                }
            }
        }
        int expectedChecksum = (nums.length + 1) / 2;
        if (nums.length % 2 == 0) {
            expectedChecksum = -expectedChecksum;
        }
        int res = (checksum - expectedChecksum) / repeats;
        if (res < 0) {
            res = -res;
        }

        return res;
    }

    // nlogn and does modify the list
    public int findDuplicateSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] == nums[i]) {
                return nums[i];
            }
        }
        return -1;
    }
}
