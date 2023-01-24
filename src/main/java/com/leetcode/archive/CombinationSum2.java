package com.leetcode.archive;

import java.util.*;

// https://leetcode.com/problems/combination-sum-ii/

// Given a collection of candidate numbers (candidates) and a target number (target),
// find all unique combinations in candidates where the candidate numbers sum to target.
//
// Each number in candidates may only be used once in the combination.
//
//Note: The solution set must not contain duplicate combinations.
public class CombinationSum2 {

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{2}, 1));
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
        System.out.println(combinationSum2(
                new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                10));
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        int[] freq = new int[candidates.length];
        int curr = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[curr] != candidates[i]) {
                curr++;
                candidates[curr] = candidates[i];
            }
            freq[curr]++;
        }

        List<List<Integer>> res = new ArrayList<>();
        find(candidates, freq, curr, target, new ArrayList<>(), res);

        return res;
    }

    private static void find(int[] candidates, int[] freq, int end,
                             int target, List<Integer> current, List<List<Integer>> res) {
        if (end < 0) {
            return;
        }

        int index = Arrays.binarySearch(candidates, 0, end + 1, target);
        int indexOfFirstSmaller;
        if (index >= 0) {
            List<Integer> toAdd = new ArrayList<>(current);
            toAdd.add(target);
            res.add(toAdd);
            indexOfFirstSmaller = index - 1;
        } else {
            int insertionPointOfFirstGreater = Math.abs(index) - 1;
            indexOfFirstSmaller = insertionPointOfFirstGreater - 1;
        }
        for (int i = indexOfFirstSmaller; i >= 0; i--) {
            for (int j = 1; j <= freq[i]; j++) {
                int newTarget = target - j * candidates[i];
                if (newTarget == 0) {
                    List<Integer> toAdd = new ArrayList<>(current);
                    for (int k = 0; k < j; k++) {
                        toAdd.add(candidates[i]);
                    }
                    res.add(toAdd);
                    break;
                }
                if (newTarget < 0) {
                    break;
                }
                for (int k = 0; k < j; k++) {
                    current.add(candidates[i]);
                }
                find(candidates, freq, i - 1, newTarget, current, res);
                for (int k = 0; k < j; k++) {
                    current.remove(current.size() - 1);
                }
            }
        }
    }
}
