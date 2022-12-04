package com.leetcode.archive;

// https://leetcode.com/problems/reach-a-number/

// standing at 0 on an infinite number line
// the length of my moves will be 1, 2, 3, 4, 5, ...
// only thing i can choose is to pick the sign(move left or right)
// given a target number, what is the minimum amount of moves
// that will get me there

// 1,  2, 3,  4,  5,  6,  7,  8,        x
// 1,  3, 6, 10, 15, 21, 28, 36, (1+x)x/2
// 1, -1, 2, -2,  3, -3,  4, -4
// 1,  3, 0,  4, -1,  5, -2,  6

// find the nearest overshooting the target
// if we are spot on good
// if the difference to the target is even we can correct without sacrificing a move
// since we can flip i and correct by -2*i
// if odd then increment untill the difference is even, will happen in 2 moves at most

public class ReachANumber {
    public static void main(String[] args) {
        System.out.println(reachNumber(5));
        // 1 + 2 + 3 + 4
    }   // -1, 1, 4

    public static int reachNumber(int target) {
        target = Math.abs(target);
        long l = target;
        l = l * 8L + 1L;
        double x = (-1 + Math.sqrt(l)) / 2;
        int best = (int) Math.ceil(x);
        int difference = calcuate(target, best);
        boolean even = difference % 2 == 0;
        if (difference == 0 || even) {
            return best;
        }
        best++;
        difference = calcuate(target, best);
        even = difference % 2 == 0;
        if (difference == 0 || even) {
            return best;
        }
        return best + 1;
    }

    private static int calcuate(int target, int best) {
        return best * (best + 1) / 2 - target;
    }
}
