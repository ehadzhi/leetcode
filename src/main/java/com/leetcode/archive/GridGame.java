package com.leetcode.archive;

// https://leetcode.com/problems/grid-game/

// You are given a 0-indexed 2D array grid of size 2 x n,
// where grid[r][c] represents the number of points at position (r, c) on the matrix.
// Two robots are playing a game on this matrix.
//
// Both robots initially start at (0, 0) and want to reach (1, n-1).
// Each robot may only move to the right ((r, c) to (r, c + 1)) or down ((r, c) to (r + 1, c)).
//
// At the start of the game, the first robot moves from (0, 0) to (1, n-1),
// collecting all the points from the cells on its path. For all cells (r, c) traversed on the path,
// grid[r][c] is set to 0. Then, the second robot moves from (0, 0) to (1, n-1), collecting the points on its path.
// Note that their paths may intersect with one another.
//
// The first robot wants to minimize the number of points collected by the second robot.
// In contrast, the second robot wants to maximize the number of points it collects. If both robots play optimally,
// return the number of points collected by the second robot.
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
