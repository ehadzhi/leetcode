package com.leetcode.archive;
//https://leetcode.com/problems/top-k-frequent-elements/

// input is an array [3, 4, 5, 6, 5, 0, 2] and an integer k
// return the k most frequent digits, don't need to be in order

// crate frequency table
// use quick select on the frequencies to find the kth one
// print the key which values are larger than the kth

// looked at the description once again -10^4 <= nums[i] <= 10^4
// so we can have only 2*10^4 ~ 20 000 unique numbers so freq table can be just an array

import java.util.*;
import java.util.stream.Collectors;

public class TopKFrequentElements {
    public static void main(String... p) {
        System.out.println(Arrays.toString(topKFrequent(
                new int[]{1, 1, 1, 2, 2, 3}, 2)));
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freqTable = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer freq = freqTable.getOrDefault(nums[i], 0);
            freqTable.put(nums[i], freq + 1);
        }

        ArrayList<Integer> frequencies = new ArrayList<>(freqTable.values());
        int kthBiggestFrequency = quickSelect(frequencies, frequencies.size() - k);
        List<Integer> kthMostFrequent = freqTable.entrySet().stream()
                .filter(entry -> entry.getValue() >= kthBiggestFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        int[] toReturn = new int[kthMostFrequent.size()];
        for (int i = 0; i < kthMostFrequent.size(); i++) {
            toReturn[i] = kthMostFrequent.get(i);
        }
        return toReturn;
    }

    private static int quickSelect(List<Integer> list, int index) {
        int leftIndex = 0;
        int rightIndex = list.size() - 1;
        int pivotIndex = -1;
        while (leftIndex != rightIndex) {
            pivotIndex = pivot(list, leftIndex, rightIndex);
            if (pivotIndex == index) {
                return list.get(pivotIndex);
            }
            if (index < pivotIndex) {
                rightIndex = pivotIndex - 1;
            } else {
                leftIndex = pivotIndex + 1;
            }
        }
        return list.get(index);
    }

    private static int pivot(List<Integer> list, int leftIndex, int rightIndex) {
        int rand = (int) ((Math.random() * (rightIndex - leftIndex)) + leftIndex);
        int pivot = list.get(rand);
        int pivotIndex = rightIndex;
        list.set(rand, list.get(rightIndex));
        list.set(rightIndex, pivot);
        rightIndex--;
        while (leftIndex < rightIndex) {
            Integer left = list.get(leftIndex);
            Integer right = list.get(rightIndex);
            if (left > pivot && right <= pivot) {
                list.set(leftIndex, right);
                list.set(rightIndex, left);
                continue;
            }
            if (left <= pivot) {
                leftIndex++;
                continue;
            }
            rightIndex--;
        }
        if (pivot <= list.get(leftIndex)) {
            list.set(pivotIndex, list.get(leftIndex));
            list.set(leftIndex, pivot);
            pivotIndex = leftIndex;
        }
        return pivotIndex;
    }

}
