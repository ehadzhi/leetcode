package com.leetcode.archive;

import java.util.HashMap;
import java.util.Map;

// https://leetcode.com/problems/permutation-in-string/
public class PermutationInString {

    public static void main(String... args) {
        print("", "", true);
        print("a", "", false);
        print("a", "a", true);
        print("a", "b", false);
        print("jingle", "ooossdfhalsdfgleijnolsadf", true);
        print("ab", "eidbaooo", true);
        print("ab", "eidboaoo", false);
        print("abc", "ccccbbbbaaaa", false);
    }

    private static void print(String s1, String s2, boolean expected) {
        System.out.println(s1 + ", " + s2
                + ", expected: " + expected
                + ", actual: " + checkInclusion(s1, s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        int[] s1FreqTable = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            s1FreqTable[c - 'a']++;
        }
        int requiredMatches = s1.length();
        int currentMatches = 0;
        int windowSize = 0;
        int[] windowFreqTable = new int[26];
        for (int i = 0; i < requiredMatches && i < s2.length(); i++) {
            char s2Char = s2.charAt(i);
            int s2CharFreqTableIndex = s2Char - 'a';
            int freq = windowFreqTable[s2CharFreqTableIndex];
            freq++;
            windowFreqTable[s2CharFreqTableIndex] = freq;

            int s1Freq = s1FreqTable[s2CharFreqTableIndex];
            if (freq <= s1Freq) {
                currentMatches++;
            }
            windowSize++;
        }
        if (windowSize < requiredMatches) {
            return false;
        }
        if (currentMatches == requiredMatches) {
            return true;
        }

        for (int i = windowSize; i < s2.length(); i++) {
            int windowStartIndex = i - windowSize;
            char windowStartChar = s2.charAt(windowStartIndex);
            int windowStartCharFreqTableIndex = windowStartChar - 'a';
            int windowStarCharFreq = windowFreqTable[windowStartCharFreqTableIndex];
            windowStarCharFreq--;
            windowFreqTable[windowStartCharFreqTableIndex] = windowStarCharFreq;
            int windowStartCharS1Freq = s1FreqTable[windowStartCharFreqTableIndex];
            if (windowStarCharFreq < windowStartCharS1Freq) {
                currentMatches--;
            }

            char windowNewChar = s2.charAt(i);
            int windowNewCharFreqTableIndex = windowNewChar - 'a';
            int windowNewCharFreq = windowFreqTable[windowNewCharFreqTableIndex];
            windowNewCharFreq++;
            windowFreqTable[windowNewCharFreqTableIndex] = windowNewCharFreq;

            int s1Freq = s1FreqTable[windowNewCharFreqTableIndex];
            if (windowNewCharFreq <= s1Freq) {
                currentMatches++;
            }
            if (currentMatches == requiredMatches) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkInclusionHashTableLookup(String s1, String s2) {
        Map<Character, Integer> s1FreqTable = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            Integer freq = s1FreqTable.getOrDefault(c, 0);
            freq++;
            s1FreqTable.put(c, freq);
        }
        int requiredMatches = s1.length();
        int currentMatches = 0;
        int windowSize = 0;
        Map<Character, Integer> windowFreqTable = new HashMap<>();
        for (int i = 0; i < requiredMatches && i < s2.length(); i++) {
            Character s2Char = s2.charAt(i);
            Integer freq = windowFreqTable.getOrDefault(s2Char, 0);
            freq++;
            windowFreqTable.put(s2Char, freq);

            Integer s1Freq = s1FreqTable.getOrDefault(s2Char, 0);
            if (freq <= s1Freq) {
                currentMatches++;
            }
            windowSize++;
        }
        if (windowSize < requiredMatches) {
            return false;
        }
        if (currentMatches == requiredMatches) {
            return true;
        }

        for (int i = windowSize; i < s2.length(); i++) {
            int windowStartIndex = i - windowSize;
            Character windowStartChar = s2.charAt(windowStartIndex);
            Integer windowStarCharFreq = windowFreqTable.get(windowStartChar);
            windowStarCharFreq--;
            windowFreqTable.put(windowStartChar, windowStarCharFreq);
            Integer windowStartCharS1Freq = s1FreqTable.getOrDefault(windowStartChar, 0);
            if (windowStarCharFreq < windowStartCharS1Freq) {
                currentMatches--;
            }

            Character windowNewChar = s2.charAt(i);
            Integer windowNewCharFreq = windowFreqTable.getOrDefault(windowNewChar, 0);
            windowNewCharFreq++;
            windowFreqTable.put(windowNewChar, windowNewCharFreq);

            Integer s1Freq = s1FreqTable.getOrDefault(windowNewChar, 0);
            if (windowNewCharFreq <= s1Freq) {
                currentMatches++;
            }
            if (currentMatches == requiredMatches) {
                return true;
            }
        }
        return false;
    }
}
