package com.leetcode.archive;

// https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/

// 1 ][ - 1
// 2 ]][[ - 1
// 3 ]]][[[ - 2
// 4 ]] ]][[ [[ - 2
// 5 ]] ]] ][ [[ [[ = 3
public class MinimumNumberOfSwaps {
    public static void main(String[] args) {
        test("", 0);
        test("[]", 0);
        test("[][]", 0);
        test("[[]]", 0);
        test("][", 1);
        test("]][[", 1);
        test("]]][[[", 2);
        test("]]]][[[[", 2);


    }

    private static void test(String s, int expected) {
        int actual = minSwaps(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int minSwaps(String s) {
        int open = 0;
        int closingWithoutAnOpening = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                open++;
            } else {
                if (open == 0) {
                    closingWithoutAnOpening++;
                } else {
                    open--;
                }
            }
        }
        return (closingWithoutAnOpening + 1) / 2;
    }
}
