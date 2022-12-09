package com.leetcode.archive;

// https://leetcode.com/problems/longest-repeating-character-replacement/

import java.util.*;

// input: upper case letter string "ABCDAADO..." and an integer k
// we can do k operations where we flip a letter to another letter to increase the answer
// return the longest possible repeating character sequence size

// my solution O(26n) look for each letter separately
// fill all the filler that you can from the beginning
// take the last filler out and put it in front to walk the fill places
// keep track of the start and end of the seq, keep track of high scores
// return the highest score

// O(n) optimal
// Sliding window. Have a start and an end index.
// Keep a frequency table to know how many of each char we've got in the window
// if max freq + k <= window size the string is valid we expand from the front
// if not valid then we slide the window forward, we do not make it smaller since if it was
// that size it means there was already a sequence that big and we're only interested if there are any larger
// when we reach the end of the string our answer is the window size
public class LongestRepeatingCharacterReplacement {
    public static void main(String... args) {
        System.out.println("7 == " + characterReplacement("QOBNTLSSS", 4));
        System.out.println(0 == characterReplacement("", 0));
        System.out.println(1 == characterReplacement("A", 0));
        System.out.println(2 == characterReplacement("AB", 1));
        System.out.println(4 == characterReplacement("ABCAE", 2));
        System.out.println(4 == characterReplacement("ABAB", 2));
        System.out.println(4 == characterReplacement("AABABBA", 1));
        System.out.println(8 == characterReplacement("AABABBABBBBB", 1));
        System.out.println(8 == characterReplacement("AABABBABBBBB", 1));

    }

    public static int characterReplacement(String s, int maxFillers) {
        int[] freq = new int[26];
        int maxFreq = 0;
        int max = 0;
        for (int start = 0, end = 0; end < s.length(); end++) {
            int currentChar = s.charAt(end) - 'A';
            freq[currentChar]++;
            maxFreq = Math.max(freq[currentChar], maxFreq);
            boolean validWindow = maxFreq + maxFillers >= end - start + 1;
            if (validWindow) {
                max = Math.max(end - start + 1, max);
                continue;
            }
            int startingChar = s.charAt(start) - 'A';
            freq[startingChar]--;
            start++;
        }
        return max;
    }

    public static int characterReplacementCrawlingFillers(String s, int maxFillers) {
        int longestPossibleRepeating = 0;
        Set<Character> done = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char charCase = s.charAt(i);
            if (done.contains(charCase)) {
                continue;
            }
            int remainingFillers = maxFillers;
            Queue<Integer> fillers = new LinkedList<>();
            int lastFiller = -1;
            for (int j = 0; j < s.length() && remainingFillers > 0; j++) {
                boolean canFill = s.charAt(j) != charCase;
                if (canFill) {
                    remainingFillers--;
                    fillers.add(j);
                    lastFiller = j;
                }
            }
            int repeating = 0;
            int repeatingStartIndex = 0;
            for (int j = 0; j < s.length(); j++) {
                char currentChar = s.charAt(j);
                boolean filled = lastFiller >= j;
                if (currentChar == charCase || filled) {
                    repeating++;
                    continue;
                }
                if (repeating > longestPossibleRepeating) {
                    longestPossibleRepeating = repeating;
                }
                Integer firstFillPosition = fillers.poll();
                if (firstFillPosition != null) {
                    repeating = repeating - (firstFillPosition - repeatingStartIndex);
                    repeatingStartIndex = firstFillPosition + 1;
                    fillers.add(j);
                    lastFiller = j;
                } else {
                    repeating = 0;
                }
            }
            if (repeating > longestPossibleRepeating) {
                longestPossibleRepeating = repeating;
            }
            done.add(charCase);
        }

        return longestPossibleRepeating;
    }
}
