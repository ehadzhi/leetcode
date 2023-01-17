package com.leetcode.archive;

// https://leetcode.com/problems/boats-to-save-people/

import java.util.Arrays;
import java.util.Comparator;

// You are given an array people where people[i] is the weight of the ith person,
// and an infinite number of boats where each boat can carry a maximum weight of limit.
// Each boat carries at most two people at the same time, provided the sum of the weight of
// those people is at most limit.
//
// Return the minimum number of boats to carry every given person.
// 1 <= people.length <= 5 * 104
// 1 <= people[i] <= limit <= 3 * 104
public class NumRescueBoats {

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int low = 0;
        int high = people.length - 1;

        int boats = 0;
        while (low <= high) {
            int slimPerson = people[low];
            int fatPerson = people[high];
            if(slimPerson+fatPerson<=limit) {
                low++;
            }
            high--;
            boats++;
        }

        return boats;
    }
}
