package com.leetcode.archive;

import java.util.*;

// https://leetcode.com/problems/repeated-dna-sequences/
public class RepeatedDnaSequence {
    public static void main(String[] args) {
        test("AAAAAAAAAAAAA", Arrays.asList("AAAAAAAAAA"));
        test("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT", Arrays.asList("AAAAACCCCC", "CCCCCAAAAA"));

    }

    private static void test(String s, List<String> expected) {
        List<String> actual = findRepeatedDnaSequences(s);
        System.out.println(s);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.containsAll(actual));
        System.out.println();
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        Set<String> memory = new HashSet<>();
        Set<String> toReturn = new HashSet<>();
        String seq = "";
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            seq = seq + c;
            if (i < 9) {
                continue;
            }
            if (seq.length() == 11) {
                seq = seq.substring(1);
            }
            if (memory.contains(seq)) {
                toReturn.add(seq);
                continue;
            }
            memory.add(seq);
        }

        return new ArrayList<>(toReturn);
    }

    // A = 0 - 00
    // T = 1 - 01
    // C = 2 - 10
    // G = 3 - 11
    // A sequence is 10 letters, so 20 bits, it fits in 3 bytes (24 bits)
    // int is 4 bytes
    // storing 10^5 ints < 1 MB
    // with each letter we 00 bits 19 and 20, shift 2 and 'and' with the newcommer
    public static List<String> findRepeatedDnaSequencesBitwise(String s) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> memory = new HashSet<>();
        List<String> toReturn = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            id &= ~0b10000000000000000000;
            id &= ~0b1000000000000000000;

            id = id << 2;

            if (c == 'A') {
            } else if (c == 'T') {
                id |= 0b1;
            } else if (c == 'C') {
                id |= 0b10;
            } else {
                id |= 0b1;
                id |= 0b10;
            }
            if (i < 9) {
                continue;
            }
            if (res.contains(id)) {
                continue;
            }
            if (memory.contains(id)) {
                res.add(id);
                memory.remove(id);
                toReturn.add(s.substring(i - 9, i + 1));
            }
            memory.add(id);
        }

        return toReturn;
    }
}
