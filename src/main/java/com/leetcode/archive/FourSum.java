package com.leetcode.archive;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

// https://leetcode.com/problems/4sum/

//        Given an array nums of n integers,
//        return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
//        0 <= a, b, c, d < n
//        a, b, c, and d are distinct.
//        nums[a] + nums[b] + nums[c] + nums[d] == target

// interesting task
public class FourSum {
    public static void main(String[] args) {
        assertMatch(new int[]{}, 0, emptyList());
        assertMatch(new int[]{}, 1, emptyList());
        assertMatch(new int[]{0, 1, 3}, 1, emptyList());
        assertMatch(new int[]{0, 1, 3, 4}, 1, emptyList());
        assertMatch(new int[]{0, 1, 0, 0}, 1, asList(

                asList(0, 0, 0, 1)
        ));


        assertMatch(new int[]{1, 0, -1, 0, -2, 2}, 0, asList(

                asList(-2, -1, 1, 2),
                asList(-2, 0, 0, 2),
                asList(-1, 0, 0, 1)));

        assertMatch(new int[]{2, 2, 2, 2, 2}, 8, asList(

                asList(2, 2, 2, 2)));

        assertMatch(new int[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, 8, asList(

                (asList(2, 2, 2, 2))));

        assertMatch(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296, emptyList(

        ));
    }

    private static void assertMatch(int[] nums, int target, List<List<Integer>> expected) {
        List<List<Integer>> actual = fourSum(nums, target);
        boolean t = actual.containsAll(expected);
        System.out.println(t + " " + actual);
    }

    // n + n + n^2 + n^2 ???
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Map<Integer, Integer> numToFreqMap = new HashMap<>();
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer freq = numToFreqMap.getOrDefault(num, 0);
            if (freq < 4) {
                freq++;
                numToFreqMap.put(num, freq);
                size++;
            }
        }
        nums = new int[size];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : numToFreqMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                nums[index] = entry.getKey();
                index++;
            }
        }
        Map<Integer, List<List<Integer>>> sumLookup = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                List<List<Integer>> entries = sumLookup.getOrDefault(sum, new ArrayList<>());
                List<Integer> entry = asList(i, j);
                Collections.sort(entry);
                entries.add(entry);
                sumLookup.put(sum, entries);
            }
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[i] + nums[j];
                long matchingSum = (long) target - (long) sum;
                if (matchingSum > Integer.MAX_VALUE || matchingSum < Integer.MIN_VALUE) {
                    continue;
                }
                List<List<Integer>> entries = sumLookup.get((int) matchingSum);
                if (entries == null) {
                    continue;
                }
                for (int k = 0; k < entries.size(); k++) {
                    List<Integer> entry = entries.get(k);
                    if (j < entry.get(0)) {
                        List<Integer> quadriplet = Arrays.asList(
                                nums[i], nums[j], nums[entry.get(0)], nums[entry.get(1)]);
                        Collections.sort(quadriplet);
                        result.add(quadriplet);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    //  nlogn+n+n^2+n^2 ???
    public static List<List<Integer>> fourSumWithSortingAndInPlaceDeduplication(int[] nums, int target) {
        if (nums.length == 0) {
            return emptyList();
        }
        Arrays.sort(nums);
        int numRepeats = 0;
        int offset = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                numRepeats = 0;
            } else {
                numRepeats++;
                if (numRepeats >= 4) {
                    offset++;
                }
            }
            nums[i - offset] = nums[i];
        }
        int numsLength = nums.length - offset;
        Map<Integer, List<List<Integer>>> sumLookup = new HashMap<>();
        for (int i = 0; i < numsLength; i++) {
            for (int j = i + 1; j < numsLength; j++) {
                int sum = nums[i] + nums[j];
                List<List<Integer>> entries = sumLookup.getOrDefault(sum, new ArrayList<>());
                List<Integer> entry = asList(i, j);
                entries.add(entry);
                sumLookup.put(sum, entries);
            }
        }
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < numsLength; i++) {
            for (int j = i + 1; j < numsLength; j++) {
                int sum = nums[i] + nums[j];
                long matchingSum = (long) target - (long) sum;
                if (matchingSum > Integer.MAX_VALUE || matchingSum < Integer.MIN_VALUE) {
                    continue;
                }
                List<List<Integer>> entries = sumLookup.get((int) matchingSum);
                if (entries == null) {
                    continue;
                }
                for (int k = 0; k < entries.size(); k++) {
                    List<Integer> entry = entries.get(k);
                    if (j < entry.get(0)) {
                        List<Integer> quadriplet = Arrays.asList(
                                nums[i], nums[j], nums[entry.get(0)], nums[entry.get(1)]);
                        result.add(quadriplet);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}
