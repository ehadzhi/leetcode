package com.leetcode.archive;

// https://leetcode.com/problems/zigzag-conversion/

// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//P   A   H   N
//A P L S I I G
//Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"
//
// Write the code that will take a string and make this conversion given a number of rows:

import javax.xml.bind.SchemaOutputResolver;

// 0 1 2 3 4 5 6 7 8 9 10...
// 0 1 2 3 4 5 6 7 8 9 10 - 1
// 0 2 4 6 8 10 1 3 5 7 9 - 2
// 0 4 8 12 1 3 5 7 9 11 2 6 10 - 3
// 0 6 12 1 5 7 11 13 2 4 8 3 9 - 4
public class ZigzagConversion {
    public static void main(String[] args) {
        test("ABCDEF", 1, "ABCDEF");
        test("ABCDEF", 2, "ACEBDF");
        test("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
        test("PAYPALISHIRING", 4, "PINALSIGYAHRPI");
    }

    private static void test(String s, int numRows, String expected) {
        String actual = convert(s, numRows);
        System.out.println(s);
        System.out.println(numRows);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder builder = new StringBuilder(s.length());
        for (int row = 0; row < numRows; row++) {
            int skip1 = ((numRows - 1) * 2) - row * 2, prevIndex = -1;
            boolean f = true;
            for (int index = row; index < s.length(); ) {
                if (prevIndex != index) {
                    builder.append(s.charAt(index));
                    prevIndex = index;
                }
                if (f) {
                    index += skip1;
                    f = false;
                } else {
                    index += 2 * row;
                    f = true;
                }
            }
        }
        return builder.toString();
    }
}
