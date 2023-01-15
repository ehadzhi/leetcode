package com.leetcode.archive;

// https://leetcode.com/problems/minimum-path-sum/

// Given a m x n grid filled with non-negative numbers,
// find a path from top left to bottom right,
// which minimizes the sum of all numbers along its path.
//
// Note: You can only move either down or right at any point in time.

// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 200
// 0 <= grid[i][j] <= 100

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumPathSum {

    public static void main(String[] args) {
        test(new int[][]{{1, 2, 3}, {4, 5, 6}}, 12);
        test(new int[][]{{0, 1}, {1, 0}}, 1);
    }

    private static void test(int[][] grid, int expected) {
        int actual = minPathSumDijkstra(grid);
        System.out.println(Arrays.deepToString(grid));
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int minPathSum(int[][] grid) {
        for (int i = 1; i < grid.length; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < grid[0].length; i++) {
            grid[0][i] += grid[0][i - 1];
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    public static int minPathSumDijkstra(int[][] grid) {
        PriorityQueue<Path> paths = new PriorityQueue<>(Comparator.comparingInt(p -> p.val));
        paths.add(new Path(0, 0, grid[0][0]));

        while (!paths.isEmpty()) {
            Path path = paths.poll();
            if (path.i == grid.length - 1
                    && path.j == grid[0].length - 1) {
                return path.val;
            }
            if (path.i < grid.length - 1 && grid[path.i + 1][path.j] != -1) {
                paths.offer(new Path(path.i + 1, path.j, path.val + grid[path.i + 1][path.j]));
                grid[path.i + 1][path.j] = -1;
            }
            if (path.j < grid[0].length - 1 && grid[path.i][path.j + 1] != -1) {
                path.j++;
                path.val += grid[path.i][path.j];
                paths.offer(path);
                grid[path.i][path.j] = -1;
            }
        }
        return 0;
    }

    static class Path {
        int i;
        int j;
        int val;

        public Path(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
}
