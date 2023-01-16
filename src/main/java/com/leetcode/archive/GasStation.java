package com.leetcode.archive;

// https://leetcode.com/problems/gas-station/

// There are n gas stations along a circular route,
// where the amount of gas at the ith station is gas[i].
//
// You have a car with an unlimited gas tank and it costs cost[i]
// of gas to travel from the ith station to its next (i + 1)th station.
// You begin the journey with an empty tank at one of the gas stations.
//
// Given two integer arrays gas and cost, return the starting gas station's
// index if you can travel around the circuit once in the clockwise direction,
// otherwise return -1. If there exists a solution, it is guaranteed to be unique

// n == gas.length == cost.length
// 1 <= n <= 10^5
// 0 <= gas[i], cost[i] <= 10^4

import java.util.Arrays;

// travel from i to i+1 costs cost[i]
public class GasStation {
    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}, 3);
        test(new int[]{2, 3, 4}, new int[]{3, 4, 3}, -1);

    }

    private static void test(int[] gas, int[] cost, int expected) {
        System.out.println(Arrays.toString(gas));
        System.out.println(Arrays.toString(cost));
        System.out.println(expected);
        int actual = canCompleteCircuit(gas, cost);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int res = 0;
        int tempRes = 0;
        int startIndex = 0;
        for (int i = 0; i < gas.length; i++) {
            res = res + gas[i] - cost[i];
            if (tempRes < 0) {
                tempRes = 0;
                startIndex = i;
            }
            tempRes = tempRes + gas[i] - cost[i];
        }

        if (res < 0) {
            return -1;
        }
        return startIndex;
    }
}
