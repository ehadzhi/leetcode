package com.leetcode.archive;

public class MyLinkedList {
    Node first = null;

    public MyLinkedList() {

    }

    public int get(int index) {
        if (first == null) {
            return -1;
        }
        int i = 0;
        Node curr = first;
        while (curr.next != null && i < index) {
            curr = curr.next;
            i++;
        }
        if (i == index) {
            return curr.val;
        }
        return -1;
    }

    public void addAtHead(int val) {
        first = new Node(val, first);
    }

    public void addAtTail(int val) {
        if (first == null) {
            addAtHead(val);
            return;
        }
        Node curr = first;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node(val, null);
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (first == null) {
            return;
        }
        int i = 1;
        Node curr = first;
        while (curr.next != null && i < index) {
            curr = curr.next;
            i++;
        }
        if (i == index) {
            curr.next = new Node(val, curr.next);
        }
    }

    public void deleteAtIndex(int index) {
        if (first == null) {
            return;
        }
        if (index == 0) {
            first = first.next;
            return;
        }
        int i = 1;
        Node curr = first;
        while (curr.next != null && i < index) {
            curr = curr.next;
            i++;
        }
        if (i == index && curr.next != null) {
            curr.next = curr.next.next;
        }
    }

    public class Node {
        int val;
        Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
        myLinkedList.get(1);              // return 2
        myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
        myLinkedList.get(1);              // return 3
    }
}
