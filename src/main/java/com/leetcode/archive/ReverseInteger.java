package com.leetcode.archive;

// https://leetcode.com/problems/reverse-integer/

// Given a signed 32-bit integer x, return x with its digits reversed.
// If reversing x causes the value to go outside the signed 32-bit integer range [-2^31, 2^31 - 1], then return 0.
public class ReverseInteger {
    public static void main(String[] args) {
        test(0, 0);
        test(-1, -1);
        test(123, 321);
        test(-123, -321);
    }

    private static void test(int toReverse, int expected) {
        int actual = reverse(toReverse);
        System.out.println(toReverse);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
        System.out.println();
    }

    public static int reverse(int x) {
        String s = "" + Math.abs(x);
        StringBuilder b = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            b.append(s.charAt(i));
        }
        try {
            int result = Integer.parseInt(b.toString());
            if (x < 0) {
                result *= -1;
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }
}
