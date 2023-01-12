package com.leetcode.archive;

// https://leetcode.com/problems/eliminate-maximum-number-of-monsters/

// You are playing a video game where you are defending your city from a group of n monsters.
// You are given a 0-indexed integer array dist of size n,
// where dist[i] is the initial distance in kilometers of the ith monster from the city.
//
// The monsters walk toward the city at a constant speed.
// The speed of each monster is given to you in an integer array speed of size n,
// where speed[i] is the speed of the ith monster in kilometers per minute.
//
// You have a weapon that, once fully charged, can eliminate a single monster.
// However, the weapon takes one minute to charge.
// The weapon is fully charged at the very start.
//
// You lose when any monster reaches your city.
// If a monster reaches the city at the exact moment the weapon is fully charged,
// it counts as a loss, and the game ends before you can use your weapon.
//
// Return the maximum number of monsters that you can eliminate before you lose,
// or n if you can eliminate all the monsters before they reach the city.


import java.util.Arrays;

// dist, speed, adjusted dist for speed 1
// 1 1 1
// 2 1 2
// 3 1 3
// 4 1 4

// 1 2 1
// 2 2 1
// 3 2 2
// 4 2 2
// 5 2 3

// 1 3 1
// 2 3 1
// 3 3 1
// 4 3 2
// 5 3 2
// 6 3 2

// convert all distances as if the speed is one, then sort
// traverse, if a monster at day i is i distance away then we're losing
public class EliminateMaximumNumberOfMonsters {
    public static void main(String[] args) {

    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        for (int i = 0; i < dist.length; i++) {
            dist[i] = (dist[i] - 1 + speed[i]) / speed[i];
        }
        Arrays.sort(dist);
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] <= i) {
                return i;
            }
        }
        return dist.length;
    }
}
