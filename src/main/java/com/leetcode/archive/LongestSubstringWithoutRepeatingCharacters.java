package com.leetcode.archive;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/longest-substring-without-repeating-characters/
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        test("", 0);
        test("a", 1);
        test(" ", 1);
        test("&", 1);
        test("bbbbb", 1);
        test("abcabcbb", 3);
        test("pwwkew", 3);

    }

    private static void test(String s, int expected) {
        int actual = lengthOfLongestSubstring(s);
        System.out.println(actual == expected);
        System.out.println(s);
        System.out.println("expected: " + expected);
        System.out.println("actual: " + actual);
    }

    public static int lengthOfLongestSubstring(String s) {
        Set<Character> chars = new HashSet<>();
        int longestSubstringSize = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!chars.contains(c)) {
                chars.add(c);
                longestSubstringSize = Math.max(chars.size(), longestSubstringSize);
            } else {
                while (s.charAt(start) != c) {
                    chars.remove(s.charAt(start));
                    start++;
                }
                start++;
            }
        }
        return longestSubstringSize;
    }
}
