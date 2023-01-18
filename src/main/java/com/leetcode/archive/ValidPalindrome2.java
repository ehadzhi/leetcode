package com.leetcode.archive;

// https://leetcode.com/problems/valid-palindrome-ii/

// Given a string s, return true if the s can be palindrome
// after deleting at most one character from it.
public class ValidPalindrome2 {

    public static void main(String[] args) {
        System.out.println(validPalindrome(
                "aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    public static boolean validPalindrome(String s) {
        return validPalindromeIndex(s, 0, s.length() - 1, true);
    }

    public static boolean validPalindromeIndex(String s, int lo, int hi, boolean errorsPermitted) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                if(errorsPermitted) {
                    return validPalindromeIndex(s, lo + 1, hi, false)
                            || validPalindromeIndex(s, lo, hi - 1, false);
                }
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

}
