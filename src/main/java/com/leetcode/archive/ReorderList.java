package com.leetcode.archive;

// https://leetcode.com/problems/reorder-list/

// You are given the head of a singly linked-list. The list can be represented as:
//
// L0 → L1 → … → Ln - 1 → Ln
// Reorder the list to be on the following form:
//
// L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
// You may not modify the values in the list's nodes. Only nodes themselves may be changed.

// The number of nodes in the list is in the range [1, 5 * 10^4].
// 1 <= Node.val <= 1000

// 0 1
// 0 1

// 0 1 2
// 0 2 1

// 0 1 2 3
// 0 3 1 2

// 0 1 2 3 4
// 0 4 1 3 2

// 0 1 2 3 4 5
// 0 5 1 4 2 3

// 0 1 2 3 4 5 6 7 8
// 0 8 1 7 2 6 3 5 4
// 0 1 2 3 4
// 8 7 6 5

// 9
// 4
// 0 1 2 3 4 5 6 7 8 9
// 0 9 1 8 2 7 3 6 4 5
// 0 1 2 3 4
// 9 8 7 6 5

// 0 1 2 3 4 5 6 7

public class ReorderList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(4, new ListNode(5)))));
        reorderList(head);
    }

    public static void reorderList(ListNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;

        boolean move = false;
        while (fast.next != null) {
            fast = fast.next;
            if (move) {
                slow = slow.next;
            }
            move = !move;
        }

        ListNode lastFromFirstHalf = slow;
        ListNode lastFromSecondHalf = lastFromFirstHalf.next;
        lastFromFirstHalf.next = null;

        ListNode curr = lastFromSecondHalf;
        ListNode next = curr.next;
        lastFromSecondHalf.next = null;
        while (next != null) {
            ListNode nextNext = next.next;
            next.next = curr;

            curr = next;
            next = nextNext;
        }

        ListNode reverseHead = curr;
        while (reverseHead != null) {
            ListNode newHead = head.next;
            ListNode newReverseHead = reverseHead.next;

            head.next = reverseHead;
            reverseHead.next = newHead;

            head = newHead;
            reverseHead = newReverseHead;
        }
    }

    public static class ListNode {
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
