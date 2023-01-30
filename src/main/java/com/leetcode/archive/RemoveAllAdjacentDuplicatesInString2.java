package com.leetcode.archive;

// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

// You are given a string s and an integer k,
// a k duplicate removal consists of choosing k adjacent and equal
// letters from s and removing them, causing the left and the right
// side of the deleted substring to concatenate together.
//
// We repeatedly make k duplicate removals on s until we no longer can.
//
// Return the final string after all such duplicate removals have been made.
// It is guaranteed that the answer is unique.

// 1 <= s.length <= 105
// 2 <= k <= 104
// s only contains lowercase English letters.
public class RemoveAllAdjacentDuplicatesInString2 {

    public static void main(String[] args) {
        test("a", 2, "a");
        test("aa", 2, "");
        test("aaa", 2, "a");
        test("abcd", 2, "abcd");
        test("deeedbbcccbdaa", 3, "aa");
        test("pbbcggttciiippooaais", 2, "ps");
    }

    private static void test(String s, int k, String expected) {
        System.out.println(s);
        String actual = removeDuplicates(s, k);
        System.out.println(k);
        System.out.println(expected);
        System.out.println(actual);
        System.out.println(expected.equals(actual));
        System.out.println();
    }

    public static String removeDuplicates(String s, int k) {

        Node start = new Node(s.charAt(0), null, null);
        Node curr = start;
        for (int i = 1; i < s.length(); i++) {
            curr.next = new Node(s.charAt(i), curr, null);
            curr = curr.next;
        }

        Node repeatStart = start;
        curr = repeatStart.next;
        int repeats = 1;
        while (curr != null) {
            if (repeatStart.val == curr.val) {
                repeats++;
                if (repeats == k) {
                    if (repeatStart.prev == null && curr.next == null) {
                        return "";
                    }
                    if (repeatStart.prev != null)
                        repeatStart.prev.next = curr.next;
                    if (curr.next != null)
                        curr.next.prev = repeatStart.prev;

                    curr = curr.next;
                    if (curr == null) {
                        break;
                    }
                    if (curr.prev == null) {
                        start = curr;
                    }
                    repeats = 0;
                    repeatStart = curr;
                    while (repeatStart.prev != null && repeatStart.prev.val == curr.val && repeats != k) {
                        repeats++;
                        repeatStart = repeatStart.prev;
                    }
                } else {
                    curr = curr.next;
                }
            } else {
                repeatStart = curr;
                repeats = 1;
                curr = curr.next;
            }
        }


        StringBuilder sb = new StringBuilder();
        curr = start;
        while (curr != null) {
            sb.append(curr.val);
            curr = curr.next;
        }
        return sb.toString();
    }

    public static class Node {
        char val;
        Node prev;
        Node next;

        public Node(char val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }
}
