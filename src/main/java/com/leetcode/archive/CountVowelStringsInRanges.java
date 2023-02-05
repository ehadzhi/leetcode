package com.leetcode.archive;

// https://leetcode.com/problems/count-vowel-strings-in-ranges/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// You are given a 0-indexed array of strings words and a 2D array of integers queries.
//
// Each query queries[i] = [li, ri] asks us to find the number of
// strings present in the range li to ri (both inclusive) of words that start and end with a vowel.
//
// Return an array ans of size queries.length,
// where ans[i] is the answer to the ith query.
//
// Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.
public class CountVowelStringsInRanges {

    public static void main(String[] args) {

    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] prefixSumVowelStrings = new int[words.length];
        int vowelStringsSoFar = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (startsAndEndsWithAVowel(word)) {
                vowelStringsSoFar++;
            }
            prefixSumVowelStrings[i] = vowelStringsSoFar;
        }

        int[] answers = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int start = query[0];
            int end = query[1];
            int vowelStrings0toEnd = prefixSumVowelStrings[end];
            int vowelStrings0toStartExclusive = 0;
            if (start > 0) {
                vowelStrings0toStartExclusive = prefixSumVowelStrings[start - 1];
            }
            int vowelStringStartToEnd =
                    vowelStrings0toEnd - vowelStrings0toStartExclusive;
            answers[i] = vowelStringStartToEnd;
        }
        return answers;
    }

    private boolean startsAndEndsWithAVowel(String word) {
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);
        return vowels.contains(firstChar) && vowels.contains(lastChar);
    }

    private static Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));


}
