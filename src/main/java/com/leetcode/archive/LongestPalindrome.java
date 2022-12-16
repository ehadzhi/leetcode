package com.leetcode.archive;
// https://leetcode.com/problems/longest-palindromic-substring/

// Given a string s, return the longest palindromic substring in s.
public class LongestPalindrome {
    public static void main(String[] args) {
        test("d", "d");
        test("babad", "aba");
        test("cbbd", "bb");
        test("ccd", "cc");
    }

    private static void test(String s, String expected) {
        String actual = longestPalindrome(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    public static String longestPalindrome(String s) {
        int b = 0, e = 0;
        int origins = 2 * s.length() - 1;
        int start = s.length() - 1;
        for (int i = 0; i < origins; i++) {
            if (i % 2 != 0) {
                start += i;
            } else {
                start -= i;
            }
            int halfStart = start / 2;
            if (Math.min(s.length() - halfStart, halfStart) * 2 + 1 < e - b) {
                return s.substring(b, e);
            }
            for (int j = (start + 1) / 2 - 1, k = halfStart + 1;
                 j >= -1 && k <= s.length(); j--, k++) {
                if (j < 0 || k >= s.length() || s.charAt(j) != s.charAt(k)) {
                    int palLength = k - j - 1;
                    if (e - b < palLength) {
                        b = j + 1;
                        e = k;
                    }
                    break;
                }
            }
        }

        return s.substring(b, e);
    }
}
