package com.leetcode.archive;

// https://leetcode.com/problems/max-area-of-island/

import java.util.Arrays;

// You are given an m x n binary matrix grid. An island is a group of 1's
// (representing land) connected 4-directionally (horizontal or vertical.)
// You may assume all four edges of the grid are surrounded by water.
//
// The area of an island is the number of cells with a value 1 in the island.
//
// Return the maximum area of an island in grid. If there is no island, return 0.
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        test(new int[][]{{0, 0, 0, 0, 0, 0, 0, 0}}, 0);
        test(new int[][]{{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0}, {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}}, 6);

    }

    private static void test(int[][] grid, int expected) {
        System.out.println(Arrays.deepToString(grid));
        System.out.println(expected);
        int actual = maxAreaOfIsland(grid);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int landPatch = grid[i][j];
                if (landPatch == 1) {
                    maxArea = Math.max(maxArea, area(grid, i, j));
                }
            }
        }

        return maxArea;
    }

    private static int area(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length
                || j < 0 || j >= grid[0].length
                || grid[i][j] == 0) {
            return 0;
        }
        grid[i][j] = 0;
        return 1 + area(grid, i + 1, j)
                + area(grid, i - 1, j)
                + area(grid, i, j + 1)
                + area(grid, i, j - 1);
    }
}
