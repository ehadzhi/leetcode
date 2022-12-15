package com.leetcode.archive;

// https://leetcode.com/problems/brick-wall/

import java.util.*;

// There is a rectangular brick wall in front of you with n rows of bricks.
// The ith row has some number of bricks each of the same height (i.e., one unit)
// but they can be of different widths. The total width of each row is the same.
//
// Draw a vertical line from the top to the bottom and cross the least bricks.
// If your line goes through the edge of a brick, then the brick is not considered as crossed.
// You cannot draw a line just along one of the two vertical edges of the wall,
// in which case the line will obviously cross no bricks.
//
// Given the 2D array wall that contains the information about the wall,
// return the minimum number of crossed bricks after drawing such a vertical line.
public class BrickWall {

    public static void main(String[] args) {
        List<List<Integer>> wall;

        wall = Arrays.asList(
                Arrays.asList(1)
        );
        test(wall, 1);

        wall = Arrays.asList(
                Arrays.asList(1, 2, 2, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 2),
                Arrays.asList(2, 4),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 1, 1)
        );

        test(wall, 2);

        wall = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(1),
                Arrays.asList(1)
        );
        test(wall, 3);
    }

    private static void test(List<List<Integer>> wall, int expected) {
        int actual = leastBricks(wall);

        System.out.println(wall);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected == actual);
        System.out.println();
    }

    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> row = wall.get(i);
            int rowWidth = 0;
            for (int j = 0; j < row.size() - 1; j++) {
                rowWidth += row.get(j);
                result.put(rowWidth, 1 + result.getOrDefault(rowWidth, 0));
            }
        }
        Integer max = result.values().stream().max(Comparator.naturalOrder()).orElse(0);
        return wall.size() - max;
    }
}
