package com.leetcode.archive;

import java.util.*;
// https://leetcode.com/problems/unique-length-3-palindromic-subsequences/

// Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
//
// Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
//
// A palindrome is a string that reads the same forwards and backwards.
//
// A subsequence of a string is a new string generated from the original string with
// some characters (can be none) deleted without changing the relative order of the remaining characters.
//
// For example, "ace" is a subsequence of "abcde".

// find first and last index of each char
// the sum count of unique elements between each first and last occurrence is the answer
public class UniqueLengthPalindromicSubSeq {
    public static void main(String[] args) {
        test("aabca", 3);
        test("adc", 0);
        test("bbcbaba", 4);
    }

    private static void test(String s, int expected) {
        int actual = countPalindromicSubsequence(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int countPalindromicSubsequence(String s) {
        int[] firstIndexes = new int[26];
        Arrays.fill(firstIndexes, -1);
        int[] lastIndexes = new int[26];
        Arrays.fill(lastIndexes, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int asciiIndex = c - 'a';
            if (firstIndexes[asciiIndex] == -1) {
                firstIndexes[asciiIndex] = i;
            }
            lastIndexes[asciiIndex] = i;
        }
        int answer = 0;

        int[] lookup = new int[26];
        Arrays.fill(lookup, -1);
        for (int i = 0; i < 26; i++) {
            if (firstIndexes[i] != -1
                    && lastIndexes[i] != firstIndexes[i]) {
                for (int j = firstIndexes[i] + 1; j < lastIndexes[i]; j++) {
                    if (lookup[s.charAt(j) - 'a'] != i) {
                        lookup[s.charAt(j) - 'a'] = i;
                        answer++;
                    }
                }
            }
        }
        return answer;
    }
}
