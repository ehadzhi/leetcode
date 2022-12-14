package com.leetcode.archive;

// https://leetcode.com/problems/koko-eating-bananas/

import java.util.Arrays;

// Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas.
// The guards have gone and will come back in h hours.
//
// Koko can decide her bananas-per-hour eating speed of k. Each hour,
// she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
//
// Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
//
// Return the minimum integer k such that she can eat all the bananas within h hours.
public class KokoBananaMama {
    public static void main(String[] args) {
        test(new int[]{3, 6, 7, 11}, 8, 4);
        test(new int[]{30, 11, 23, 4, 20}, 5, 30);
        test(new int[]{30, 11, 23, 4, 20}, 6, 23);
    }

    private static void test(int[] piles, int hours, int expected) {
        int actual = minEatingSpeed(piles, hours);
        System.out.println(Arrays.toString(piles));
        System.out.println(hours);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
        System.out.println();
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int l = 0, r = Integer.MAX_VALUE;
        while (l + 1 != r) {
            int speed = (r - l) / 2 + l;
            long hours = 0;
            for (int i = 0; i < piles.length; i++) {
                hours += (piles[i] + speed - 1) / speed;
            }
            boolean success = hours <= h;
            if (success) {
                r = speed;
            } else {
                l = speed;
            }
        }
        return r;
    }
}
