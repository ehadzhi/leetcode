package com.leetcode.archive;

// https://leetcode.com/problems/splitting-a-string-into-descending-consecutive-values/description/

// You are given a string s that consists of only digits.
//
// Check if we can split s into two or more non-empty substrings such that the numerical
// values of the substrings are in descending order and the difference between numerical
// values of every two adjacent substrings is equal to 1.
//
// For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89].
// The values are in descending order and adjacent values differ by 1, so this way is valid.
// Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"].
// However all the ways are invalid because they have numerical values [0,1], [0,1],
// and [0,0,1] respectively, all of which are not in descending order.
// Return true if it is possible to split s as described above, or false otherwise.
//
// A substring is a contiguous sequence of characters in a string.
public class StringSplittingConsecutiveDescending {

    public static void main(String[] args) {
        test("10", true);
        test("4771447713", true);
        test("1098765432", true);
        test("0090089", true);
        test("001", false);
        test("1234", false);
        test("050043", true);
        test("9080701", false);
    }

    private static void test(String s, boolean expected) {
        boolean actual = splitString(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static boolean splitString(String s) {
        for (int i = 1; i < s.length(); i++) {
            String start = s.substring(0, i);
            long first = Long.parseUnsignedLong(start);
            if (first == 0) {
                continue;
            }
            boolean res = split(s, i, Long.toUnsignedString(first - 1));
            if (res) {
                return true;
            }
        }
        return false;
    }

    private static boolean split(String s, int index, String toFind) {
        if (index == s.length()) {
            return true;
        }

        if (toFind.length() > s.length() - index) {
            return false;
        }

        if (toFind.equals("0") && s.charAt(index) == '0') {
            while (index < s.length() && s.charAt(index) == '0') {
                index++;
            }
            return index == s.length();
        }

        // ignore leading zeros
        while (index < s.length() && s.charAt(index) == '0') {
            index++;
        }

        if (s.startsWith(toFind, index)) {
            return split(s, index + toFind.length(),
                    Long.toUnsignedString(Long.parseUnsignedLong(toFind) - 1));
        }
        return false;
    }
}
