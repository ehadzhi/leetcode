package com.leetcode.archive;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// https://leetcode.com/problems/merge-k-sorted-lists

// You are given an array of k linked-lists lists,
// each linked-list is sorted in ascending order.
//
// Merge all the linked-lists into one sorted linked-list and return it.
public class MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null)
                queue.add(lists[i]);
        }

        ListNode result = null;
        ListNode toReturn = null;
        while (!queue.isEmpty()) {
            ListNode current = queue.poll();
            if (result == null) {
                result = current;
                toReturn = result;
            } else {
                result.next = current;
                result = current;
            }
            if (current.next != null) {
                queue.add(current.next);
            }
        }
        return toReturn;
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
