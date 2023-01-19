package com.leetcode.archive;

// https://leetcode.com/problems/course-schedule-ii/

import java.util.*;

public class CourseSchedule2 {
    public static void main(String[] args) {
        test(1, new int[][]{}, new int[]{0});
        test(2, new int[][]{{0, 1}}, new int[]{1, 0});
        test(2, new int[][]{{1, 0}}, new int[]{0, 1});
        test(2, new int[][]{{1, 0}, {0, 1}}, new int[]{});
        test(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}}, new int[]{0, 1, 2, 3});
    }

    private static void test(int numCourses, int[][] prerequisites, int[] expected) {
        int[] actual = findOrder(numCourses, prerequisites);
        System.out.println(numCourses);
        System.out.println(Arrays.deepToString(prerequisites));
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
        System.out.println(Arrays.equals(expected, actual));
        System.out.println();
    }

    static boolean cycle = false;

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        cycle = false;
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }

        List<Integer> topologicalSort = new ArrayList<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] sorted = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                visit(i, graph, visited, topologicalSort, sorted);
                if(cycle) {
                    return new int[]{};
                }
            }
        }

        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = topologicalSort.get(i);
        }

        return res;
    }

    private static void visit(int course, List<Integer>[] graph, boolean[] visited,
                              List<Integer> topologicalSort, boolean[] sorted) {
        if(cycle) {
            return;
        }
        visited[course] = true;
        for (int i = 0; i < graph[course].size(); i++) {
            int prereq = graph[course].get(i);
            if (!visited[prereq]) {
                visit(prereq, graph, visited, topologicalSort, sorted);
            } else {
                if (!sorted[prereq]) {
                    cycle = true;
                    return;
                }
            }
        }
        sorted[course] = true;
        topologicalSort.add(course);
    }
}
