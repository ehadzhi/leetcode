package com.leetcode.archive;

// https://leetcode.com/problems/add-two-numbers/

// input two reversed numbers where each integer is a node of a single linked list
//  0 + 0 = 0
// 899 + 1 = 900
// input is 9 -> 9 -> 8 , 1 output should be 0 -> 0 -> 9

// 1985 + 85345 = 87330
// 5 -> 8 -> 9 -> 1
// 0 -> 3 -> 3 -> 7 -> 8

// just look at it as prev
// 1985 + 85345 = 87330
//         null <- 1 <- 9 <- 8 <- 5
// null <-    8 <- 5 <- 3 <- 4 <- 5
//
// input is non-empty with no leading zeros
public class AddTwoNumbers {

    public static void main(String[] args) {
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int extra = 0;

        ListNode toReturn = new ListNode(0, null);
        ListNode pointerNode = toReturn;
        while (l1 != null || l2 != null) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sum = l1Val + l2Val + extra;
            extra = sum / 10;
            int digit = sum % 10;
            pointerNode.next = new ListNode(digit, null);
            pointerNode = pointerNode.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (extra > 0) {
            pointerNode.next = new ListNode(extra, null);
        }

        return toReturn.next;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
