package com.leetcode.archive;

// https://leetcode.com/problems/decode-string/

// Given an encoded string, return its decoded string.
//
// The encoding rule is: k[encoded_string],
// where the encoded_string inside the square brackets is being repeated exactly k times.
// Note that k is guaranteed to be a positive integer.
//
// You may assume that the input string is always valid; there are no extra white spaces,
// brackets are well-formed, etc. Furthermore,
// you may assume that the original data does not contain any digits
// and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
//
// The test cases are generated so that the length of the output will never exceed 105.

// "medium"
public class DecodeString {
    public static void main(String[] args) {
        test("3[a]2[bc]", "aaabcbc");
        test("3[a2[c]]", "accaccacc");
        test("2[abc]3[cd]ef", "abcabccdcdcdef");
    }

    private static void test(String s, String expected) {
        System.out.println(s);
        System.out.println(expected);
        String actual = decodeString(s);
        System.out.println(actual);
        System.out.println(actual.equals(expected));
        System.out.println();
    }

    static class Node {
        char val;
        Node next;
        Node prev;

        public Node(char val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }

        public void insertAfter(char c) {
            Node toInsert = new Node(c, next, this);
            if (next != null) {
                next.prev = toInsert;
            }
            next = toInsert;
        }

        public void deleteMe() {
            if (prev != null) {
                prev.next = next;
            }
            if (next != null) {
                next.prev = prev;
            }
        }
    }


    public static String decodeString(String s) {
        Node first = null;
        Node current = null;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (current == null) {
                current = new Node(c, null, null);
            } else {
                current.insertAfter(c);
                current = current.next;
            }
            if (i == 0) {
                first = current;
            }
        }

        current = first;
        while (current != null) {
            char c = current.val;
            if (Character.isDigit(c)) {
                StringBuilder numString = new StringBuilder();
                while (Character.isDigit(current.val)) {
                    numString.append(current.val);
                    current.deleteMe();
                    if (current == first) {
                        first = current.next;
                    }
                    current = current.next;
                }
                int num = Integer.parseInt(numString.toString());

                // eat starting brace
                current.deleteMe();
                if (current == first) {
                    first = current.next;
                }
                current = current.next;

                // find closing brace
                int openBraces = 1;
                Node end = current;
                while (true) {
                    if (end.val == '[') {
                        openBraces++;
                    } else if (end.val == ']') {
                        openBraces--;
                        if (openBraces == 0) {
                            break;
                        }
                    }
                    end = end.next;
                }

                Node newEnd = end;
                for (int i = 1; i < num; i++) {
                    Node index = current;
                    while (index != end) {
                        newEnd.insertAfter(index.val);
                        newEnd = newEnd.next;
                        index = index.next;
                    }
                }
                // eat ending brace
                end.deleteMe();
            } else {
                current = current.next;
            }
        }

        StringBuilder result = new StringBuilder();
        current = first;
        while (current != null) {
            result.append(current.val);
            current = current.next;
        }
        return result.toString();
    }

    public static void print(Node current) {
        StringBuilder result = new StringBuilder();
        while (current != null) {
            result.append(current.val);
            current = current.next;
        }
        System.out.println("res: ");
        System.out.println(result);
        System.out.println();
    }
}
