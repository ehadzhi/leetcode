package com.leetcode.archive;

// https://leetcode.com/problems/sum-of-two-integers/

// Given two integers a and b,
// return the sum of the two integers without using the operators + and -

// -1000 <= a, b <= 1000
public class SumOfTwoIntegers {

    public static void main(String[] args) {
        System.out.println(getSum(0, 0));
        System.out.println(getSum(1, 0));
        System.out.println(getSum(0, 1));
        System.out.println(getSum(1, 1));
        System.out.println(getSum(100, 100));
    }

    public static int getSum(int a, int b) {
        int swap;
        while (b != 0) {
            swap = a;
            a = a ^ b;
            b = (swap & b) << 1;
        }
        return a;
    }

    public int getSumRecursive(int a, int b) {
        if (b == 0) {
            return a;
        }
        return getSumRecursive(a ^ b, (a & b) << 1);
    }
}
