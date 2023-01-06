package com.leetcode.archive;

// https://leetcode.com/problems/design-twitter/description/

// Design a simplified version of Twitter where users can post tweets,
// follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
//
// Implement the Twitter class:
//
// Twitter() Initializes your twitter object.
// void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId.
// Each call to this function will be made with a unique tweetId.
// List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed.
// Each item in the news feed must be posted by users who the user followed or by the user themself.
// Tweets must be ordered from most recent to least recent.
// void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
// void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.


/*
  Your Twitter object will be instantiated and called as such:
  Twitter obj = new Twitter();
  obj.postTweet(userId,tweetId);
  List<Integer> param_2 = obj.getNewsFeed(userId);
  obj.follow(followerId,followeeId);
  obj.unfollow(followerId,followeeId);
 */

import java.util.*;

public class Twitter {
    public class Tweet {
        int id;
        int userId;

        public Tweet(int id, int userId) {
            this.id = id;
            this.userId = userId;
        }
    }

    private List<Tweet> tweets = new ArrayList<>();
    private Map<Integer, Set<Integer>> userFollows = new HashMap<>();


    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        tweets.add(new Tweet(tweetId, userId));
    }

    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> following = userFollows.getOrDefault(userId, Collections.emptySet());
        List<Integer> feed = new ArrayList<>();
        for (int i = tweets.size() - 1; i >= 0; i--) {
            if (feed.size() == 10) {
                break;
            }
            Tweet tweet = tweets.get(i);
            if (tweet.userId == userId || following.contains(tweet.userId)) {
                feed.add(tweet.id);
            }
        }
        return feed;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> follows = userFollows.getOrDefault(followerId, new HashSet<>());
        if (follows.contains(followeeId)) {
            return;
        }
        follows.add(followeeId);
        userFollows.put(followerId, follows);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> follows = userFollows.get(followerId);
        if (follows == null) {
            return;
        }
        follows.remove(followeeId);
    }
}
