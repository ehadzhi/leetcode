package com.leetcode.archive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/find-k-closest-elements/

// Given a sorted integer array arr, two integers k and x,
// return the k closest integers to x in the array.
// The result should also be sorted in ascending order.
//
// An integer a is closer to x than an integer b if:
// |a - x| < |b - x|, or
// |a - x| == |b - x| and a < b

public class KClosestElementsInSortedArray {

    // 1 <= k <= arr.length
    // 1 <= arr.length <= 10 000
    // -10 000 <= arr[i], x <= 10 000
    public static void main(String[] args) {
        test(new int[]{1, 1, 1, 10, 10, 10}, 1, 9, Arrays.asList(10));
        test(new int[]{0}, 1, 0, Arrays.asList(0));
        test(new int[]{0}, 2, 0, Arrays.asList(0));
        test(new int[]{0}, 2, 1, Arrays.asList(0));
        test(new int[]{0}, 5, -1, Arrays.asList(0));

        test(new int[]{1, 2, 3, 4, 5}, 4, -1, Arrays.asList(1, 2, 3, 4));

        test(new int[]{1, 2, 3, 4, 5}, 1, 3, Arrays.asList(3));
        test(new int[]{1, 2, 3, 4, 5}, 2, 3, Arrays.asList(2, 3));
        test(new int[]{1, 2, 3, 4, 5}, 3, 3, Arrays.asList(2, 3, 4));
        test(new int[]{1, 2, 3, 4, 5}, 4, 3, Arrays.asList(1, 2, 3, 4));

        test(new int[]{1, 2, 4, 5, 6}, 1, 3, Arrays.asList(2));

        test(new int[]{1, 2, 3, 4, 5, 6}, 4, 2, Arrays.asList(1, 2, 3, 4));
        test(new int[]{1, 2, 3, 3, 4, 5, 6}, 4, 2, Arrays.asList(1, 2, 3, 3));

    }

    private static void test(int[] arr, int k, int x, List<Integer> expected) {
        List<Integer> actual = findClosestElements(arr, k, x);
        boolean same = expected.size() == actual.size();
        if (same) {
            for (int i = 0; i < expected.size(); i++) {
                if (!expected.get(i).equals(actual.get(i))) {
                    same = false;
                    break;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(k);
        System.out.println(x);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(same);
        System.out.println();
    }


    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
        int firstEqualOrMoreIndex = arr.length - 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= x) {
                firstEqualOrMoreIndex = i;
                break;
            }
        }
        if (arr[firstEqualOrMoreIndex] > x && firstEqualOrMoreIndex != 0
                && Math.abs(arr[firstEqualOrMoreIndex - 1] - x)
                <= Math.abs(arr[firstEqualOrMoreIndex] - x)) {
            firstEqualOrMoreIndex--;
        }

        int startIndex = firstEqualOrMoreIndex;
        int endIndex = firstEqualOrMoreIndex;
        // while range less than k and at least one is in range
        // expand to the one in range3
        // or smaller of the two
        // if equal to the left
        int rangeSize = endIndex - startIndex + 1;
        while (rangeSize < k && (startIndex >= 0 || endIndex < arr.length)) {
            if (startIndex - 1 >= 0 && endIndex + 1 < arr.length) {
                if (Math.abs(arr[startIndex - 1] - x) <= Math.abs(arr[endIndex + 1] - x)) {
                    startIndex--;
                } else {
                    endIndex++;
                }
            } else if (startIndex - 1 >= 0) {
                startIndex--;
            } else if (endIndex + 1 < arr.length) {
                endIndex++;
            } else {
                break;
            }
            rangeSize = endIndex - startIndex + 1;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
