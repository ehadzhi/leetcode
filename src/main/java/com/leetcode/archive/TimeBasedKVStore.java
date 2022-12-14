package com.leetcode.archive;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

// https://leetcode.com/problems/time-based-key-value-store/
public class TimeBasedKVStore {
    private Map<String, TreeMap<Integer, String>> store = new HashMap<>();

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer, String> valHistory = store.getOrDefault(key, new TreeMap<>());
        valHistory.put(timestamp, value);
        store.put(key, valHistory);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> valHistory = store.get(key);
        if (valHistory != null) {
            Map.Entry<Integer, String> entry = valHistory.floorEntry(timestamp);
            if (entry != null) {
                return entry.getValue();
            }
        }
        return "";
    }
}
