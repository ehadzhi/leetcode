package com.leetcode.archive;

// https://leetcode.com/problems/count-vowels-permutation/

// Given an integer n, your task is to count how many strings of length n
// can be formed under the following rules:
//
// Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
// 1 Each vowel 'a' may only be followed by an 'e'.
// 2 Each vowel 'e' may only be followed by an 'a' or an 'i'.
// 3 Each vowel 'i' may not be followed by another 'i'.
// 4 Each vowel 'o' may only be followed by an 'i' or a 'u'.
// 5 Each vowel 'u' may only be followed by an 'a'.
// Since the answer may be too large, return it modulo 10^9 + 7.


import java.util.Arrays;

// 0 1 2 3 4
// a e i o u
public class CountVowelsPermutation {

    public static void main(String[] args) {
        System.out.println(countVowelPermutation(1));
        System.out.println(countVowelPermutation(2));
        System.out.println(countVowelPermutation(3));
        System.out.println(countVowelPermutation(4));
        System.out.println(countVowelPermutation(5));
        System.out.println(countVowelPermutation(144));
    }

    public static int countVowelPermutation(int n) {
        int modulo = 1000000007;
        int vowels = 5;
        long[][] countEndingOn = new long[2][vowels];
        Arrays.fill(countEndingOn[1], 1);
        for (int i = 1; i < n; i++) {
            int prev = i % 2;
            int curr = (i + 1) % 2;

            countEndingOn[curr][0] = (countEndingOn[prev][1] +
                    countEndingOn[prev][2] + countEndingOn[prev][4]) % modulo;

            countEndingOn[curr][1] = (countEndingOn[prev][0] + countEndingOn[prev][2]) % modulo;

            countEndingOn[curr][2] = (countEndingOn[prev][3] + countEndingOn[prev][1]) % modulo;

            countEndingOn[curr][3] = countEndingOn[prev][2];

            countEndingOn[curr][4] = (countEndingOn[prev][2] + countEndingOn[prev][3]) % modulo;

            Arrays.fill(countEndingOn[prev], 0);

        }
        long sum = 0;
        int resArray = n % 2;
        for (int i = 0; i < vowels; i++) {
            sum += countEndingOn[resArray][i];
        }
        return (int) (sum % modulo);
    }
}
