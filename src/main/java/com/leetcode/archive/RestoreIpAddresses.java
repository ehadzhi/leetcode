package com.leetcode.archive;

// https://leetcode.com/problems/restore-ip-addresses/description/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// A valid IP address consists of exactly four integers separated by single dots.
// Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.
//
// For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses,
// but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
// Given a string s containing only digits, return all possible valid IP addresses
// that can be formed by inserting dots into s.
// You are not allowed to reorder or remove any digits in s.
// You may return the valid IP addresses in any order.

// 0 1 2 3 4 5 6 7 8 9
//  1 2 3 4 5 6 7 8 9
public class RestoreIpAddresses {
    public static void main(String[] args) {
        test("0000", Arrays.asList("0.0.0.0"));
        test("25525511135", Arrays.asList("255.255.11.135", "255.255.111.35"));
        test("101023", Arrays.asList("1.0.10.23", "1.0.102.3", "10.1.0.23", "10.10.2.3", "101.0.2.3"));
    }

    private static void test(String s, List<String> expected) {
        List<String> actual = restoreIpAddresses(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual.containsAll(expected) && expected.containsAll(actual));
        System.out.println();
    }

    public static List<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4) {
            return Collections.emptyList();
        }

        List<String> res = new ArrayList<>();

        for (int i = 1; i <= Math.min(3, s.length() - 3); i++) {
            String first = s.substring(0, i);
            if (!valid(first)) {
                break;
            }
            for (int j = i + 1; j <= s.length() - 2; j++) {
                String second = s.substring(i, j);
                if (!valid(second)) {
                    break;
                }
                for (int k = Math.max(j + 1, s.length() - 3); k <= s.length() - 1; k++) {
                    String third = s.substring(j, k);
                    if (!valid(third)) {
                        break;
                    }
                    String fourth = s.substring(k);
                    if (valid(fourth)) {
                        res.add(first + "." + second + "." + third + "." + fourth);
                    }
                }
            }
        }
        return res;
    }

    public static boolean valid(String s) {
        if (s.length() > 3) {
            return false;
        }
        if (s.length() > 1 && s.charAt(0) == '0') {
            return false;
        }
        return Integer.parseInt(s) <= 255;
    }
}
