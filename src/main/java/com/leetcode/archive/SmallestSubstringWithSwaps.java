package com.leetcode.archive;

import java.util.*;

// https://leetcode.com/problems/smallest-string-with-swaps/

// You are given a string s, and an array of pairs of indices in the string pairs where
// pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
//
// You can swap the characters at any pair of indices in the given pairs any number of times.
//
// Return the lexicographically smallest string that s can be changed to after using the swaps.
public class SmallestSubstringWithSwaps {

    public static void main(String[] args) {
        List<List<Integer>> pairs = Arrays.asList(
                Arrays.asList(0, 3),
                Arrays.asList(1, 2)
        );
        test("dcab", pairs, "bacd");

        pairs = Arrays.asList(
                Arrays.asList(0, 3),
                Arrays.asList(1, 2),
                Arrays.asList(0, 2)
        );
        test("dcab", pairs, "abcd");

        pairs = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 2)
        );
        test("cba", pairs, "abc");
    }

    private static void test(String s, List<List<Integer>> pairs, String expected) {
        String actual = smallestStringWithSwaps(s, pairs);
        System.out.println(s);
        System.out.println(pairs);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    static class UnionFind {
        int[] component;
        int[] rank;

        public UnionFind(int size) {
            this.component = new int[size];
            for (int i = 0; i < size; i++) {
                component[i] = i;
            }
            this.rank = new int[size];
            Arrays.fill(rank, 1);
        }

        int find(int i) {
            if (component[i] == i) {
                return i;
            }
            int res = find(component[i]);
            component[i] = res;
            return res;
        }

        int union(int a, int b) {
            int aComp = find(a);
            int bComp = find(b);
            if (rank[aComp] >= rank[bComp]) {
                rank[aComp] += rank[bComp];
                component[bComp] = aComp;
                return aComp;
            }
            rank[bComp] += rank[aComp];
            component[aComp] = bComp;
            return bComp;
        }
    }

    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind uf = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> chars = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            chars.putIfAbsent(uf.find(i), new PriorityQueue<>());
            chars.get(uf.find(i)).offer(s.charAt(i));
        }
        StringBuilder builder = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            builder.append(chars.get(uf.find(i)).poll());
        }
        return builder.toString();
    }
}
