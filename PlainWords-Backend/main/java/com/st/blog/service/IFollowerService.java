package com.st.blog.service;

import com.st.blog.beans.Follower;
import com.st.blog.exceptions.FollowerNotFound;

import java.util.List;

public interface IFollowerService {
    Follower followUser(Follower follower);
    void unfollowUser(Long followerUserId, Long followedUserId) throws FollowerNotFound;
    List<Follower> getFollowersOfUser(Long userId);
    List<Follower> getFollowingsOfUser(Long userId);
    List<Follower> getAllFollowers();
}
