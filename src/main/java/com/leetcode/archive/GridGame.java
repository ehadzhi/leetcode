package com.leetcode.archive;

public class GridGame {
    public static void main(String[] args) {
        System.out.println(gridGame(new int[][]{{2, 5, 4}, {1, 5, 1}}));
    }

    public static long gridGame(int[][] grid) {
        long[][] g = new long[1][grid[0].length];
        g[1][0] = grid[1][0];
        g[0][grid[0].length - 1] = grid[0][grid[0].length - 1];
        for (int i = 1; i < grid[0].length; i++) {
            g[1][i] = grid[1][i] + g[1][i - 1];
        }
        for (int i = grid[0].length - 2; i >= 0; i--) {
            g[0][i] = grid[0][i] + g[0][i + 1];
        }

        long min = Math.max(g[0][0], g[1][grid[0].length - 1]);
        for (int i = 0; i < grid[0].length; i++) {
            long left = 0;
            long right = 0;
            if (i > 0) {
                right = g[1][i - 1];
            }
            if (i < grid[0].length - 1) {
                left = g[0][i + 1];
            }
            min = Math.min(min, Math.max(right, left));
        }
        return min;
    }
}
