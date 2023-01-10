package com.leetcode.archive;

import java.util.Arrays;

// https://leetcode.com/problems/remove-covered-intervals/

// Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri),
// remove all intervals that are covered by another interval in the list.
//
// The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
//
// Return the number of remaining intervals.

// 1 <= intervals.length <= 1000
// intervals[i].length == 2
// 0 <= li < ri <= 105
// All the given intervals are unique.

// sort by opening ascending, and when equal by closing descending
// loop and look at closing either previous with the largest end is covering it or the current should be
// the one with the largest end
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        test(new int[][]{{1, 4}, {3, 6}, {2, 8}}, 2);
        test(new int[][]{{1, 4}, {2, 3}}, 1);

    }

    private static void test(int[][] intervals, int expected) {
        int actual = removeCoveredIntervals(intervals);
        System.out.println(Arrays.deepToString(intervals));
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(actual == expected);
        System.out.println();
    }

    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> {
            int comp = Integer.compare(i1[0], i2[0]);
            if (comp == 0) {
                comp = -Integer.compare(i1[1], i2[1]);
            }
            return comp;
        });
        int covered = 0;
        int prevClosing = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int closing = intervals[i][1];

            if (prevClosing >= closing) {
                covered++;
            } else {
                prevClosing = closing;
            }
        }
        return intervals.length - covered;
    }
}
