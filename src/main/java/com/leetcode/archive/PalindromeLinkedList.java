package com.leetcode.archive;

// https://leetcode.com/problems/palindrome-linked-list/

// Given the head of a singly linked list,
// return true if it is a palindrome or false otherwise.

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {

    public boolean isPalindrome(ListNode head) {
        List<Integer> ints = new ArrayList<>();
        while (head != null) {
            ints.add(head.val);
            head = head.next;
        }

        for (int i = 0; i < ints.size() / 2; i++) {
            if (ints.get(i).compareTo(ints.get(ints.size() - 1 - i)) != 0) {
                return false;
            }
        }
        return true;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
