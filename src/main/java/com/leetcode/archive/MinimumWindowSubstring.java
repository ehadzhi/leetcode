package com.leetcode.archive;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/minimum-window-substring/description/
//Given two strings s and t of lengths m and n respectively, return the minimum window
//        substring
//        of s such that every character in t (including duplicates) is included in the window.
//        If there is no such substring, return the empty string "".
//
//        The testcases will be generated such that the answer is unique.


// ez pz, crawl like a worm on the string
public class MinimumWindowSubstring {

    public static void main(String... args) {
        test("ADOBECODEBANC", "ABC", "BANC");
        test("a", "a", "a");
        test("a", "aa", "");

    }

    private static void test(String s, String t, String expected) {
        String actual = minWindow(s, t);
        System.out.println(s + ", " + t + ", expected: " + expected + ", actual: " + actual
                + "\npassing: " + expected.equals(actual));
        System.out.println("");
    }

    public static String minWindow(String s, String t) {
        Map<Character, Integer> freqTable = new HashMap<>();
        int neededMatches = 0;
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Integer freq = freqTable.getOrDefault(c, 0);
            freq++;
            freqTable.put(c, freq);
            neededMatches++;
        }

        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (freqTable.get(s.charAt(i)) != null) {
                start = i;
                break;
            }
        }
        if (start == -1) {
            return "";
        }
        Map<Character, Integer> matchingTable = new HashMap<>();
        int currentMatches = 0;
        int answerStart = -1;
        int answerEnd = -1;
        for (int end = start; end < s.length(); end++) {
            char c = s.charAt(end);
            Integer freq = matchingTable.getOrDefault(c, 0);
            freq++;
            matchingTable.put(c, freq);

            Integer toMatchFreq = freqTable.getOrDefault(c, 0);
            if (freq <= toMatchFreq) {
                currentMatches++;
                if (currentMatches == neededMatches
                        && (end - start < answerEnd - answerStart || answerStart < 0)) {
                    answerEnd = end;
                    answerStart = start;
                }

                while (currentMatches == neededMatches) {
                    c = s.charAt(start);
                    start++;
                    freq = matchingTable.getOrDefault(c, 0);
                    freq--;
                    matchingTable.put(c, freq);

                    toMatchFreq = freqTable.getOrDefault(c, 0);
                    if (freq < toMatchFreq) {
                        currentMatches--;
                    } else {
                        if (end - start < answerEnd - answerStart || answerStart < 0) {
                            answerEnd = end;
                            answerStart = start;
                        }
                    }
                }
            }
        }
        if(answerStart < 0) {
            return "";
        }
        return s.substring(answerStart, answerEnd + 1);
    }
}
