package com.st.blog.controller;

import com.st.blog.beans.Follower;
import com.st.blog.exceptions.FollowerNotFound;
import com.st.blog.service.IFollowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api/followers")
@CrossOrigin(origins = "http://localhost:3000")
public class FollowerController {

    @Autowired
    private IFollowerService followerService;

    @PostMapping("/follow")
    public ResponseEntity<Follower> followUser(@RequestBody Follower follower) {
        Follower savedFollower = followerService.followUser(follower);
        return new ResponseEntity<>(savedFollower, HttpStatus.CREATED);
    }

    @DeleteMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestParam Long followerUserId, @RequestParam Long followedUserId)  throws FollowerNotFound{
        try {
            followerService.unfollowUser(followerUserId, followedUserId);
            return new ResponseEntity<>("Unfollowed successfully", HttpStatus.OK);
        } catch (FollowerNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<Follower>> getFollowersOfUser(@PathVariable Long userId) {
        List<Follower> followers = followerService.getFollowersOfUser(userId);
        return new ResponseEntity<>(followers, HttpStatus.OK);
    }

    @GetMapping("/followings/{userId}")
    public ResponseEntity<List<Follower>> getFollowingsOfUser(@PathVariable Long userId) {
        List<Follower> followings = followerService.getFollowingsOfUser(userId);
        return new ResponseEntity<>(followings, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Follower>> getAllFollowers() {
        List<Follower> allFollowers = followerService.getAllFollowers();
        return new ResponseEntity<>(allFollowers, HttpStatus.OK);
    }
}
