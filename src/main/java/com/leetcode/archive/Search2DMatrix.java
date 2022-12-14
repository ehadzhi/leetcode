package com.leetcode.archive;

import java.util.Arrays;

// https://leetcode.com/problems/search-a-2d-matrix/

// Write an efficient algorithm that searches for a value target in an m x n integer matrix.
// This matrix has the following properties:
//
// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] m = new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};

        test(m, 3, true);
        test(m, 13, false);
        test(new int[][]{{1}}, 0, false);
        test(new int[][]{{1}}, 1, true);
        test(new int[][]{{1, 3}}, 3, true);
    }

    private static void test(int[][] m, int target, boolean expected) {
        boolean actual = searchMatrix(m, target);
        for (int[] a : m) {
            System.out.print(Arrays.toString(a));
            System.out.print(", ");
        }
        System.out.println();
        System.out.println(target);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            int x1 = mid / m;
            int x2 = mid - (mid / m) * m;
            if (matrix[x1][x2] == target) {
                return true;
            }
            if (matrix[x1][x2] > target) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }
}
