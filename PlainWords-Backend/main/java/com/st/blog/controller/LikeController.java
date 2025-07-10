package com.st.blog.controller;

import com.st.blog.beans.Like;
import com.st.blog.exceptions.LikeNotFound;
import com.st.blog.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api/likes")
@CrossOrigin(origins = "http://localhost:3000")
public class LikeController {

    @Autowired
    private ILikeService likeService;

    @PostMapping("/add")
    public ResponseEntity<Like> addLike(@RequestBody Like like) {
        Like savedLike = likeService.addLike(like);
        return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
    }

    @PutMapping("/update/{likeId}")
    public ResponseEntity<Like> updateLike(@PathVariable Long likeId, @RequestBody Like like) {
        try {
            Like updatedLike = likeService.updateLike(likeId, like);
            return new ResponseEntity<>(updatedLike, HttpStatus.OK);
        } catch (LikeNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable Long likeId) {
        try {
            likeService.deleteLike(likeId);
            return new ResponseEntity<>("Like deleted successfully", HttpStatus.OK);
        } catch (LikeNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{likeId}")
    public ResponseEntity<Like> getLikeById(@PathVariable Long likeId) {
        try {
            Like like = likeService.getLikeById(likeId);
            return new ResponseEntity<>(like, HttpStatus.OK);
        } catch (LikeNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Like>> getAllLikes() {
        try {
            List<Like> likeList = likeService.getAllLikes();
            return new ResponseEntity<>(likeList, HttpStatus.OK);
        } catch (LikeNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
