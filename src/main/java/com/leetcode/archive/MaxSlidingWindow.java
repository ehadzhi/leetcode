package com.leetcode.archive;

// https://leetcode.com/problems/sliding-window-maximum/

import java.util.*;

// You are given an array of integers nums,
// there is a sliding window of size k which is moving from the very left of the array to the very right.
// You can only see the k numbers in the window. Each time the sliding window moves right by one position.
//
// Return the max sliding window.

// with a tree map gives us the ability to both know the frequency of an element and also the largest element
// getting the largest element is 0(1) insert/delete/access is logn we need to do that n times
// so nlogn

// monotonic stack gets us to linear
// while traversing remember only the max candidates. Each new candidate will remove all smaller(non bigger) back to front
// removal is done only if the one leaving the window is the same as the first max candidate
public class MaxSlidingWindow {
    public static void main(String[] args) {
        test(new int[]{}, 0, new int[]{});
        test(new int[]{}, 1, new int[]{});
        test(new int[]{1}, 0, new int[]{});
        test(new int[]{1}, 1, new int[]{1});
        test(new int[]{1, -1}, 1, new int[]{1, -1});
        test(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3, new int[]{3, 3, 5, 5, 6, 7});
    }

    private static void test(int[] nums, int k, int[] expected) {
        int[] actual = maxSlidingWindow(nums, k);

        boolean correct = Arrays.equals(expected, actual);
        System.out.println(correct);
        System.out.println(k);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(expected));
        System.out.println(Arrays.toString(actual));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (k <= 0 || nums.length == 0) {
            return new int[0];
        }
        int[] result = new int[Math.max(nums.length - k + 1, 0)];
        Deque<Integer> maxes = new ArrayDeque<>();
        for (int endIndex = 0; endIndex < nums.length; endIndex++) {
            int num = nums[endIndex];
            if (maxes.isEmpty() || num < maxes.getLast()) {
                maxes.addLast(num);
            } else {
                while (!maxes.isEmpty() && num > maxes.getLast()) {
                    maxes.removeLast();
                }
                maxes.addLast(num);
            }

            int startIndex = endIndex - k + 1;
            if (startIndex >= 0) {
                result[startIndex] = maxes.getFirst();

                if (maxes.getFirst() == nums[startIndex]) {
                    maxes.removeFirst();
                }
            }
        }
        return result;
    }

    public static int[] maxSlidingWindowTreeMap(int[] nums, int k) {
        if (k <= 0) {
            return new int[0];
        }
        TreeMap<Integer, Integer> window = new TreeMap<>(Comparator.reverseOrder());
        int windowEndIndex = 0;
        for (int i = 0; i < k && i < nums.length; i++) {
            int num = nums[i];
            Integer freq = window.getOrDefault(num, 0);
            freq++;
            window.put(num, freq);
            windowEndIndex = i;
        }

        int windowStartIndex = 0;
        int[] result = new int[Math.max(nums.length - k + 1, 0)];
        for (; windowEndIndex < nums.length; windowEndIndex++, windowStartIndex++) {
            result[windowStartIndex] = window.firstKey();

            Integer toBeRemovedFreq = window.get(nums[windowStartIndex]);
            toBeRemovedFreq--;
            if (toBeRemovedFreq > 0) {
                window.put(nums[windowStartIndex], toBeRemovedFreq);
            } else {
                window.remove(nums[windowStartIndex]);
            }

            if (windowEndIndex < nums.length - 1) {
                Integer freq = window.getOrDefault(nums[windowEndIndex + 1], 0);
                freq++;
                window.put(nums[windowEndIndex + 1], freq);
            }
        }

        return result;
    }
}
