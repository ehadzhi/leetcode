package com.leetcode.archive;

// https://leetcode.com/problems/pacific-atlantic-water-flow/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
// The Pacific Ocean touches the island's left and top edges,
// and the Atlantic Ocean touches the island's right and bottom edges.
//
// The island is partitioned into a grid of square cells.
// You are given an m x n integer matrix heights where heights[r][c] represents
// the height above sea level of the cell at coordinate (r, c).
//
// The island receives a lot of rain, and the rain water can flow to
// neighboring cells directly north, south, east, and west if the neighboring cell's
// height is less than or equal to the current cell's height. Water can flow from any
// cell adjacent to an ocean into the ocean.
//
// Return a 2D list of grid coordinates result where result[i] = [ri, ci]
// denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
public class PacificAtlanticWaterFlow {

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[][] pacificVisited = new boolean[heights.length][heights[0].length];
        boolean[][] atlanticVisited = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            initWalk(i, 0, heights, pacificVisited, atlanticVisited, result);
            initWalk(i, heights[0].length - 1, heights, pacificVisited, atlanticVisited, result);
        }
        for (int i = 0; i < heights[0].length; i++) {
            initWalk(0, i, heights, pacificVisited, atlanticVisited, result);
            initWalk(heights.length - 1, i, heights, pacificVisited, atlanticVisited, result);
        }

        return result;
    }

    private void initWalk(int x, int y, int[][] heights,
                          boolean[][] pacificVisited, boolean[][] atlanticVisited,
                          List<List<Integer>> result) {
        boolean pacific = x == 0 || y == 0;
        boolean atlantic = x == heights.length - 1 || y == heights[0].length - 1;
        walk(x, y, pacific, atlantic, heights, pacificVisited, atlanticVisited, 0, result);

    }

    private void walk(int x, int y, boolean pacific, boolean atlantic,
                      int[][] heights, boolean[][] pacificVisited, boolean[][] atlanticVisited,
                      int prev, List<List<Integer>> result) {
        if (x < 0 || x >= heights.length
                || y < 0 || y >= heights[0].length
                || prev > heights[x][y]) {
            return;
        }
        boolean alreadyVisited = true;
        if (pacific && !pacificVisited[x][y]) {
            alreadyVisited = false;
            pacificVisited[x][y] = true;

            if (atlanticVisited[x][y]) {
                result.add(Arrays.asList(x, y));
            }
        }
        if (atlantic && !atlanticVisited[x][y]) {
            alreadyVisited = false;
            atlanticVisited[x][y] = true;

            if (pacificVisited[x][y]) {
                result.add(Arrays.asList(x, y));
            }
        }
        if (alreadyVisited) {
            return;
        }
        walk(x + 1, y, pacific, atlantic, heights, pacificVisited, atlanticVisited, heights[x][y], result);
        walk(x - 1, y, pacific, atlantic, heights, pacificVisited, atlanticVisited, heights[x][y], result);
        walk(x, y + 1, pacific, atlantic, heights, pacificVisited, atlanticVisited, heights[x][y], result);
        walk(x, y - 1, pacific, atlantic, heights, pacificVisited, atlanticVisited, heights[x][y], result);
    }
}
