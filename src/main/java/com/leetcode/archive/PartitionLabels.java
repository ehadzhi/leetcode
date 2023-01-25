package com.leetcode.archive;

// https://leetcode.com/problems/partition-labels/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// You are given a string s.
// We want to partition the string into as many parts as possible
// so that each letter appears in at most one part.
//
// Note that the partition is done so that after
// concatenating all the parts in order,
// the resultant string should be s.
//
//  Return a list of integers representing the size of these parts.
public class PartitionLabels {

    public List<Integer> partitionLabels(String s) {
        int[] firstOccurrence = new int[26];
        Arrays.fill(firstOccurrence, -1);
        int[] lastOccurrence = new int[26];
        Arrays.fill(lastOccurrence, -1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cIndex = c - 'a';
            if (firstOccurrence[cIndex] == -1) {
                firstOccurrence[cIndex] = i;
            }
            lastOccurrence[cIndex] = i;
        }
        List<Integer> res = new ArrayList<>();
        int numStartedButNotEnded = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int cIndex = c - 'a';
            if (firstOccurrence[cIndex] == i) {
                numStartedButNotEnded++;
            }
            if (lastOccurrence[cIndex] == i) {
                numStartedButNotEnded--;
            }
            if (numStartedButNotEnded == 0) {
                res.add((i - start) + 1);
                start = i + 1;
            }
        }
        return res;
    }
}
