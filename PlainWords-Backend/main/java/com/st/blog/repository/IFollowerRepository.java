package com.st.blog.repository;

import com.st.blog.beans.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IFollowerRepository extends JpaRepository<Follower, Long> {
    List<Follower> findByFollowedUserId(Long followedUserId);
    List<Follower> findByFollowerUserId(Long followerUserId);
    Optional<Follower> findByFollowerUserIdAndFollowedUserId(Long followerUserId, Long followedUserId);
}
