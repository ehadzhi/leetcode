package com.leetcode.archive;


import java.util.ArrayList;
import java.util.List;

// https://leetcode.com/problems/design-browser-history/
// One tab browser
// start on the homepage url
// can visit another url
// move back and forth in history

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
public class BrowserHistory {
    private List<String> history = new ArrayList<>();
    private int current = -1;
    private int last = -1;

    public static void main(String[] args) {
    }

    public BrowserHistory(String homepage) {
        visit(homepage);
    }

    public void visit(String url) {
        if (current == history.size() - 1) {
            history.add(url);
        } else {
            history.set(current + 1, url);
        }
        current++;
        last = current;
    }

    public String back(int steps) {
        int prev = current - steps;
        if (prev < 0) {
            prev = 0;
        }
        current = prev;
        return history.get(prev);
    }

    public String forward(int steps) {
        int next = current + steps;
        if (next > last) {
            next = last;
        }
        current = next;
        return history.get(next);
    }
}
