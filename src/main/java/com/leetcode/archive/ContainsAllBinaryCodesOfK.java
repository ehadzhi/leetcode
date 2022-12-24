package com.leetcode.archive;

import java.util.HashSet;
import java.util.Set;

// https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/

// Given a binary string s and an integer k,
// return true if every binary code of length k is a substring of s.
// Otherwise, return false.
public class ContainsAllBinaryCodesOfK {
    public static void main(String[] args) {
        test("00110110", 2, true);
        test("0110", 1, true);
        test("0110", 2, false);
    }

    private static void test(String s, int k, boolean expected) {
        boolean actual = hasAllCodes(s, k);
        System.out.println(s);
        System.out.println(k);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static boolean hasAllCodes(String s, int k) {
        int num = 0;
        int twoToTheKMinusOne = 1;
        for (int i = 0; i < k && i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                num = 2 * num + 1;
            } else {
                num = 2 * num;
            }
            twoToTheKMinusOne *= 2;
        }
        twoToTheKMinusOne /= 2;

        Set<Integer> present = new HashSet<>();
        present.add(num);
        for (int i = k; i < s.length(); i++) {
            if (s.charAt(i - k) == '1') {
                num = num - twoToTheKMinusOne;
            }
            if (s.charAt(i) == '1') {
                num = 2 * num + 1;
            } else {
                num = 2 * num;
            }
            present.add(num);
        }
        return present.size() == twoToTheKMinusOne * 2;
    }
}
