package com.leetcode.archive;

// https://leetcode.com/problems/reverse-nodes-in-k-group/

// Given the head of a linked list, reverse the nodes of the list k at a time,
// and return the modified list.
//
// k is a positive integer and is less than or equal to the length of the linked list.
// If the number of nodes is not a multiple of k then left-out nodes, in the end,
// should remain as it is.
//
// You may not alter the values in the list's nodes, only nodes themselves may be changed.

// The number of nodes in the list is n.
// 1 <= k <= n <= 5000
// 0 <= Node.val <= 1000
//
//Follow-up: Can you solve the problem in O(1) extra memory space?
public class ReverseNodesInKGroup {

    public static void main(String[] args) {
        ListNode res = reverseKGroup(new ListNode(1, new ListNode(2,
                new ListNode(3, new ListNode(4, new ListNode(5, null))))), 2);

        print(res);

    }

    private static void print(ListNode res) {
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
        System.out.println();
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int length = 0;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        ListNode groupSmallest = head;
        ListNode groupStart = head;
        ListNode temp;
        int numGroups = length / k;
        ListNode prevGroupEnd = null;
        for (int i = 0; i < numGroups; i++) {
            for (int j = 1; j < k; j++) {
                temp = groupSmallest.next;
                groupSmallest.next = groupSmallest.next.next;
                temp.next = groupStart;
                groupStart = temp;
            }
            if (i == 0) {
                head = groupStart;
            }
            if (prevGroupEnd != null) {
                prevGroupEnd.next = groupStart;
            }
            prevGroupEnd = groupSmallest;
            groupSmallest = groupSmallest.next;
            groupStart = groupSmallest;
        }
        return head;
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
