package com.leetcode.archive;

import java.util.Arrays;
import java.util.Comparator;

// https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

// There are some spherical balloons taped onto a flat wall that represents the XY-plane.
// The balloons are represented as a 2D integer array points where
// points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend.
// You do not know the exact y-coordinates of the balloons.
//
// Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis.
// A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
// There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely,
// bursting any balloons in its path.
//
// Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

// greedy task, sort the intervals by openings when the first non overlapping all others appears increase the counter
public class MinArrowShots {

    public static void main(String[] args) {
        test(new int[][]{{-2147483648, 2147483647}}, 1);
        test(new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}}, 2);
        test(new int[][]{{1, 2}, {3, 4}, {5, 6}, {7, 8}}, 4);
        test(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}}, 2);

    }

    private static void test(int[][] points, int expected) {
        int actual = findMinArrowShots(points);

        Arrays.stream(points).forEach(point -> System.out.print(Arrays.toString(point)));
        System.out.println();
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
        System.out.println();
    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(p -> p[0]));

        int result = 0;
        int smallestClosing = points[0][1];
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0];
            int x2 = points[i][1];
            if (x1 > smallestClosing) {
                result++;
                smallestClosing = x2;
            } else {
                smallestClosing = Math.min(x2, smallestClosing);
            }
        }
        return result + 1;
    }
}
