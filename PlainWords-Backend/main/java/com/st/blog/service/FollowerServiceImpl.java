package com.st.blog.service;

import com.st.blog.beans.Follower;
import com.st.blog.exceptions.FollowerNotFound;
import com.st.blog.repository.IFollowerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FollowerServiceImpl implements IFollowerService {

    @Autowired
    private IFollowerRepository followerRepository;

    @Override
    public Follower followUser(Follower follower) {
        follower.setFollowedAt(LocalDateTime.now());
        return followerRepository.save(follower);
    }

    @Override
    public void unfollowUser(Long followerUserId, Long followedUserId) throws FollowerNotFound {
        Optional<Follower> follower = followerRepository.findByFollowerUserIdAndFollowedUserId(followerUserId, followedUserId);
        if (follower.isPresent()) {
            followerRepository.delete(follower.get());
        } else {
            throw new FollowerNotFound("Follower relationship not found.");
        }
    }

    @Override
    public List<Follower> getFollowersOfUser(Long userId) {
        return followerRepository.findByFollowedUserId(userId);
    }

    @Override
    public List<Follower> getFollowingsOfUser(Long userId) {
        return followerRepository.findByFollowerUserId(userId);
    }

    @Override
    public List<Follower> getAllFollowers() {
        return followerRepository.findAll();
    }
}
