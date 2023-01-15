package com.leetcode.archive;

// https://leetcode.com/problems/maximum-number-of-removable-characters/

// You are given two strings s and p where p is a subsequence of s.
// You are also given a distinct 0-indexed integer array removable containing a
// subset of indices of s (s is also 0-indexed).
//
// You want to choose an integer k (0 <= k <= removable.length) such that,
// after removing k characters from s using the first k indices in removable,
// p is still a subsequence of s. More formally, you will mark the character at
// s[removable[i]] for each 0 <= i < k, then remove all marked characters and check if p is still a subsequence.
//
// Return the maximum k you can choose such that p is still a subsequence of s after the removals.
//
// A subsequence of a string is a new string generated from the original string with
// some characters (can be none) deleted without changing the relative order of the remaining characters.

import java.util.Arrays;

public class MaximumNumberOfRemovableCharacters {
    public static void main(String[] args) {
        test("abcacb", "ab", new int[]{3, 1, 0}, 2);
        test("abcbddddd", "abcd", new int[]{3, 1, 0}, 1);
        test("abcab", "abc", new int[]{0, 1, 2, 3, 4}, 0);
    }

    private static void test(String s, String p, int[] removable, int expected) {
        int actual = maximumRemovals(s, p, removable);
        System.out.println(s);
        System.out.println(p);
        System.out.println(Arrays.toString(removable));
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int maximumRemovals(String s, String p, int[] removable) {
        char[] chars = s.toCharArray();
        int low = 0;
        int high = removable.length;
        // 0 0
        // 1 0 1
        // 1 2 2
        // 2 3 3
        // 2 0 1
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;

            for (int i = 0; i < mid; i++) {
                chars[removable[i]] -= 'a';
            }
            boolean possible = false;
            for (int i = 0, j = 0; i < chars.length && j < p.length();
                 i++) {
                if (chars[i] >= 'a') {
                    if (p.charAt(j) == chars[i]) {
                        j++;
                        if (j == p.length()) {
                            possible = true;
                            break;
                        }
                    }
                }
            }
            for (int i = 0; i < mid; i++) {
                chars[removable[i]] += 'a';
            }
            if (possible) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }
}
