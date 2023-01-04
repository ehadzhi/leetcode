package com.leetcode.archive;

// https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task.
// In each round, you can complete either 2 or 3 tasks of the same difficulty level.
//
// Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
public class MinimumRoundsToCompleteAllTasks {
    public static void main(String[] args) {
        test(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4}, 4);
        test(new int[]{2, 3, 3}, -1);
    }

    private static void test(int[] tasks, int expected) {
        int actual = minimumRounds(tasks);
        System.out.println(Arrays.toString(tasks));
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
        System.out.println();
    }

    public static int minimumRounds(int[] tasks) {
        Map<Integer, Integer> freqTable = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            int num = tasks[i];
            Integer freq = freqTable.getOrDefault(num, 0);
            freq++;
            freqTable.put(num, freq);
        }

        int result = 0;
        for (int freq : freqTable.values()) {
            if (freq == 1) {
                return -1;
            }
            int remainder = freq % 3;
            if (remainder == 0) {
                result += freq / 3;
            } else {
                result += freq / 3 + 1;
            }
        }
        return result;
    }
}
