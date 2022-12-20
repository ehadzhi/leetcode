package com.leetcode.archive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/find-all-anagrams-in-a-string/

// Given two strings s and p, return an array of all the start indices of p's anagrams in s.
// You may return the answer in any order.
//
// An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
// typically using all the original letters exactly once.

// slinding window...
public class FindAnagrams {
    public static void main(String[] args) {
        test("a", "a", Arrays.asList(0));
        test("baa", "aa", Arrays.asList(1));
        test("abab", "ab", Arrays.asList(0, 1, 2));
        test("cbaebabacd", "abc", Arrays.asList(0, 6));
    }

    private static void test(String s, String p, List<Integer> expected) {
        List<Integer> actual = findAnagrams(s, p);
        System.out.println(s);
        System.out.println(p);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int[] freqTable = new int[26];
        int neededMatches = 0;
        for (int i = 0; i < p.length(); i++) {
            int charIndex = p.charAt(i) - 'a';
            if (freqTable[charIndex] == 0) {
                neededMatches++;
            }
            freqTable[charIndex]--;
        }

        List<Integer> anagramStartIndexes = new ArrayList<>();
        int matches = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i - p.length() >= 0) {
                int charIndex = s.charAt(i - p.length()) - 'a';
                freqTable[charIndex]--;
                if (freqTable[charIndex] == 0) {
                    matches++;
                } else {
                    if (freqTable[charIndex] == -1) {
                        matches--;
                    }
                }
            }
            int charIndex = s.charAt(i) - 'a';
            freqTable[charIndex]++;
            if (freqTable[charIndex] == 0) {
                matches++;
                if (matches == neededMatches) {
                    anagramStartIndexes.add(i - p.length() + 1);
                }
            } else {
                if (freqTable[charIndex] == 1) {
                    matches--;
                }
            }
        }
        return anagramStartIndexes;
    }
}
