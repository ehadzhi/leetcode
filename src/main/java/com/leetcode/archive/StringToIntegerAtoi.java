package com.leetcode.archive;

// https://leetcode.com/problems/string-to-integer-atoi/

// Implement the myAtoi(string s) function, which converts a string to
// a 32-bit signed integer (similar to C/C++'s atoi function).
//
// The algorithm for myAtoi(string s) is as follows:
//
// Read in and ignore any leading whitespace.
// Check if the next character (if not already at the end of the string) is '-' or '+'.
// Read this character in if it is either.
// This determines if the final result is negative or positive respectively.
// Assume the result is positive if neither is present.
// Read in next the characters until the next non-digit character or the end of the input is reached.
// The rest of the string is ignored.
// Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32).
// If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
// If the integer is out of the 32-bit signed integer range [-231, 231 - 1],
// then clamp the integer so that it remains in the range. Specifically,
// integers less than -2^31 should be clamped to -2^31, and integers greater than 2^31 - 1 should be clamped to 2^31 - 1.
// Return the integer as the final result.
// Note:
//
// Only the space character ' ' is considered a whitespace character.
// Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
public class StringToIntegerAtoi {

    public static void main(String[] args) {
        test("", 0);
        test(" ", 0);
        test("-", 0);
        test("1", 1);
        test("+1", 1);
        test("42", 42);
        test("-42", -42);
        test("  -42", -42);
        test("4193 with words", 4193);
        test("99999999999999", 2147483647);
        test("1000000000000000000000000000000000000000000000000000000" +
                "0000000000000000000000000000000000000000000000000522545459", 2147483647);
        test("-99999999999999", -2147483648);
        test("words and 987", 0);
    }

    private static void test(String s, int expected) {
        int actual = myAtoi(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
        System.out.println();
    }

    public static int myAtoi(String s) {
        int i = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                break;
            }
        }
        int sign = 1;

        if(i < s.length()) {
            char signChar = s.charAt(i);
            if (signChar == '-') {
                sign = -1;
            } else if (signChar == '+') {
            } else if (signChar < '0' || signChar > '9') {
                return 0;
            } else {
                i--;
            }
        }

        i++;
        int res = 0;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                try {
                    res = Math.addExact(Math.multiplyExact(res, 10), sign * (c - '0'));
                } catch (Exception e) {
                    if (res > 0) {
                        return Integer.MAX_VALUE;
                    }
                    return Integer.MIN_VALUE;
                }
            } else {
                return res;
            }
        }
        return res;
    }
}
