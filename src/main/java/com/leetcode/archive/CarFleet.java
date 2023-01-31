package com.leetcode.archive;

// https://leetcode.com/problems/car-fleet/

// There are n cars going to the same destination along a one-lane road.
// The destination is target miles away.
//
// You are given two integer array position and speed, both of length n,
// where position[i] is the position of the ith car and speed[i] is the speed of
// the ith car (in miles per hour).
//
// A car can never pass another car ahead of it, but it can catch up to it and drive
// bumper to bumper at the same speed. The faster car will slow down to match the
// slower car's speed. The distance between these two cars is ignored (i.e., they are assumed
// to have the same position).
//
// A car fleet is some non-empty set of cars driving at the same position and same speed.
// Note that a single car is also a car fleet.
//
// If a car catches up to a car fleet right at the destination point,
// it will still be considered as one car fleet.
//
// Return the number of car fleets that will arrive at the destination.

import java.util.Arrays;

// n == position.length == speed.length
// 1 <= n <= 10^5
// 0 < target <= 10^6
// 0 <= position[i] < target
// All the values of position are unique.
// 0 < speed[i] <= 10^6
public class CarFleet {

    public static void main(String[] args) {
        test(10, new int[]{6, 8}, new int[]{3, 2}, 2);
        test(10, new int[]{3}, new int[]{3}, 1);
        test(100, new int[]{0, 2, 4}, new int[]{4, 2, 1}, 1);
        test(12, new int[]{10, 8, 0, 5, 3}, new int[]{2, 4, 1, 1, 3}, 3);
        test(10, new int[]{8, 3, 7, 4, 6, 5}, new int[]{4, 4, 4, 4, 4, 4}, 6);

    }

    private static void test(int target, int[] position, int[] speed, int expected) {
        System.out.println(target);
        System.out.println(Arrays.toString(position));
        System.out.println(Arrays.toString(speed));
        System.out.println(expected);
        int actual = carFleet(target, position, speed);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        int[][] posSpeed = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            posSpeed[i][0] = position[i];
            posSpeed[i][1] = speed[i];
        }
        Arrays.sort(posSpeed, (a1, a2) -> a2[0] - a1[0]);

        int fleets = 0;
        double leaderTimeToReach = 0;
        for (int i = 0; i < position.length; i++) {
            int spd = posSpeed[i][1];
            int distance = target - posSpeed[i][0];
            double timeToReach = distance / (double) spd;

            if (timeToReach > leaderTimeToReach) {
                fleets++;
                leaderTimeToReach = timeToReach;
            }
        }
        return fleets;
    }
}
