package com.leetcode.archive;

// https://leetcode.com/problems/daily-temperatures/description/

import java.util.Arrays;
import java.util.LinkedList;

// Given an array of integers temperatures represents the daily temperatures,
// return an array answer such that answer[i] is the number of days you have
// to wait after the ith day to get a warmer temperature.
// If there is no future day for which this is possible, keep answer[i] == 0 instead.

// monotonic stack only keep the indexes on the stack
// each new temperature before pushed should pop all the lesser ones
// while popping them write the result which is difference in the index
public class DailyTemperatures {
    public static void main(String[] args) {
        test(new int[]{73, 74, 75, 71, 69, 72, 76, 73}, new int[]{1, 1, 4, 2, 1, 1, 0, 0});
        test(new int[]{30, 40, 50, 60}, new int[]{1, 1, 1, 0});
        test(new int[]{30, 60, 90}, new int[]{1, 1, 0});
    }

    private static void test(int[] input, int[] expected) {
        int[] actual = dailyTemperatures(input);
        System.out.println(Arrays.toString(input));
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
        System.out.println(Arrays.equals(expected, actual));
        System.out.println();
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty()
                    && temperatures[stack.peek()] < temperatures[i]) {
                result[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
}
