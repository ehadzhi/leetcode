package com.leetcode.archive;

import java.util.*;

// https://leetcode.com/problems/push-dominoes/

// traverse the string and decide on each range
public class PushDominoes {
    public static void main(String[] args) {
        test("L", "L");
        test("RR.L", "RR.L");
        test(".L.R...LR..L..", "LL.RR.LLRRLL..");
        test(".L.R.", "LL.RR");
    }

    private static void test(String dominoes, String expected) {
        String actual = pushDominoes(dominoes);
        System.out.println(dominoes);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    public static String pushDominoes(String dominoes) {
        char[] result = new char[dominoes.length()];
        Arrays.fill(result, '.');
        int lastIndex = -1;
        boolean lastWasLeft = true;
        for (int i = 0; i <= dominoes.length(); i++) {
            char c;
            if (i < dominoes.length()) {
                c = dominoes.charAt(i);
            } else {
                c = 'R';
            }

            if (c == '.') {
                continue;
            }
            if (c == 'R') {
                if (lastWasLeft) {
                    if (lastIndex > 0) {
                        result[lastIndex] = 'L';
                    }
                } else {
                    for (int j = lastIndex; j < i; j++) {
                        result[j] = 'R';
                    }
                }
                lastIndex = i;
                lastWasLeft = false;
            } else {
                if (lastIndex > 0) {
                    result[lastIndex] = 'L';
                }
                if (lastWasLeft) {
                    for (int j = lastIndex + 1; j <= i; j++) {
                        result[j] = 'L';
                    }
                } else {
                    for (int j = lastIndex, k = i; j < k; j++, k--) {
                        result[j] = 'R';
                        result[k] = 'L';
                    }
                }
                lastIndex = i;
                lastWasLeft = true;
            }
        }
        return new String(result);
    }
}
