package com.leetcode.archive;

// https://leetcode.com/problems/product-of-array-except-self/
// division should work fine but we need to worry about zeros
// and the description says to not use division

import java.util.Arrays;
import java.util.stream.Collectors;


// prefix and suffix products
public class ProductOfArrayExceptSelf {
    public static void main(String... args) {
        System.out.println(
                Arrays.stream(productExceptSelf(new int[]{1, 2, 3, 4}))
                        .boxed().collect(Collectors.toList()));
    }

    public static int[] productExceptSelf(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int[] answer = new int[nums.length];
        answer[0] = nums[0];
        for (int i = 1; i < nums.length - 1; i++) {
            answer[i] = nums[i] * answer[i - 1];
        }
        answer[nums.length - 1] = answer[nums.length - 2];
        for (int i = nums.length - 2; i >= 1; i--) {
            answer[i] = answer[i - 1] * nums[i + 1];
            nums[i] = nums[i] * nums[i + 1];
        }
        answer[0] = nums[1];
        return answer;
    }

    public static int[] productExceptSelfNotInPlace(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int[] precompute1 = new int[nums.length];
        precompute1[0] = nums[0];
        int[] precompute2 = new int[nums.length];
        precompute2[nums.length - 1] = nums[nums.length - 1];

        for (int i = 1; i < nums.length; i++) {
            precompute1[i] = nums[i] * precompute1[i - 1];
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            precompute2[i] = nums[i] * precompute2[i + 1];
        }
        int[] answer = new int[nums.length];
        for (int i = 1; i < nums.length - 1; i++) {
            answer[i] = precompute1[i - 1] * precompute2[i + 1];
        }
        answer[0] = precompute2[1];
        answer[answer.length - 1] = precompute1[answer.length - 2];
        return answer;
    }
}
