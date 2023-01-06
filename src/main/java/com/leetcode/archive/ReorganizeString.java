package com.leetcode.archive;

// https://leetcode.com/problems/reorganize-string/description/

// Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
//
// Return any possible rearrangement of s or return "" if not possible.

// if we have a character more than the even places then it is impossible
// build a freq table
// find the max
// fill it if possible on even
// fill the rest of the even and uneven with the rest, no need to be sorted
public class ReorganizeString {
    public static void main(String[] args) {

        test("aab", "aba");
        test("aaab", "");
        test("abbabbaaab", "ababababab");
        test("zqugrfbsznyiwbokwkpvpmeyvaosdkedbgjogzdpwawwl", "wkwlwmwnwoaoaobpbpbpdqdrdseseufvgvgygyizjzkzk");
    }

    private static void test(String s, String expected) {
        String actual = reorganizeString(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    public static String reorganizeString(String s) {
        int[] freqTable = new int[26];
        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            freqTable[index]++;
            if (freqTable[index] > max) {
                max = freqTable[index];
                maxIndex = index;
            }
        }
        if (max > (s.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[s.length()];
        int index = 0;
        while (freqTable[maxIndex] > 0) {
            res[index] = (char) ('a' + maxIndex);
            freqTable[maxIndex]--;
            index += 2;
        }

        for (int i = 0; i < 26; i++) {
            while (freqTable[i] > 0) {
                if (index >= s.length()) {
                    index = 1;
                }
                res[index] = (char) ('a' + i);
                freqTable[i]--;
                index += 2;
            }
        }
        return new String(res);
    }
}
