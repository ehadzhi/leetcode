package com.leetcode.archive;

// https://leetcode.com/problems/asteroid-collision/description/

import java.util.Arrays;

// We are given an array asteroids of integers representing asteroids in a row.
//
// For each asteroid, the absolute value represents its size,
// and the sign represents its direction (positive meaning right, negative meaning left).
// Each asteroid moves at the same speed.
//
// Find out the state of the asteroids after all collisions.
// If two asteroids meet, the smaller one will explode.
// If both are the same size, both will explode.
// Two asteroids moving in the same direction will never meet.
public class AsteroidCollision {

    public static void main(String[] args) {
        test(new int[]{8, -8}, new int[]{});
        test(new int[]{8, 8}, new int[]{8, 8});
        test(new int[]{-10, -10,}, new int[]{-10, -10});
        test(new int[]{10, 2, -5}, new int[]{10});
        test(new int[]{5, 10, -5}, new int[]{5, 10});
    }

    private static void test(int[] asteroids, int[] expected) {
        int[] actual = asteroidCollision(asteroids);
        System.out.println(Arrays.toString(asteroids));
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
        System.out.println(Arrays.equals(expected, actual));
        System.out.println();
    }

    static class Node {
        int val;
        Node prev;

        public Node(int val, Node prev) {
            this.val = val;
            this.prev = prev;
        }
    }

    public static int[] asteroidCollision(int[] asteroids) {
        Node n = null;
        int resultSize = 0;
        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];
            if (asteroid > 0) {
                n = new Node(asteroid, n);
                resultSize++;
            } else {
                int magnitude = asteroid * -1;
                while (n != null && n.val > 0 && magnitude > n.val) {
                    n = n.prev;
                    resultSize--;
                }
                if (n == null || n.val < 0) {
                    n = new Node(asteroid, n);
                    resultSize++;
                } else if (magnitude == n.val) {
                    n = n.prev;
                    resultSize--;
                }
            }
        }
        int[] result = new int[resultSize];
        for (int i = resultSize - 1; i >= 0; i--) {
            result[i] = n.val;
            n = n.prev;
        }
        return result;
    }
}
