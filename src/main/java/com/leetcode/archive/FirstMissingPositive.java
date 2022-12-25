package com.leetcode.archive;

// https://leetcode.com/problems/first-missing-positive/description/
public class FirstMissingPositive {
    public static void main(String[] args) {
        firstMissingPositive(new int[]{1, 1});
        firstMissingPositive(new int[]{3, 4, -1, 1});
    }

    public static int firstMissingPositive(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0 && nums[i] < nums.length + 1) {
                temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
                if (nums[i] > 0 && nums[i] < nums.length + 1
                        && nums[i] != i + 1 && nums[nums[i] - 1] != nums[i]) {
                    i--;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }
}
