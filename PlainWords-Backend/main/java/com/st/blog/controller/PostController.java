package com.st.blog.controller;

//import com.blog.dto.Post;

//import com.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.st.blog.beans.Post;
import com.st.blog.exceptions.PostNotFound;
import com.st.blog.service.IPostService;

import java.util.List;

@RestController
@RequestMapping("/blog/api/posts")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

    @Autowired
    private IPostService postService;

    // Create Post
    @PostMapping("/createPost")
    public ResponseEntity<Post> createPost(@RequestBody Post postDTO) {
        Post createdPost = postService.createPost(postDTO);
        return ResponseEntity.ok(createdPost);
    }

    // Get all posts
    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Get post by ID (with comments and likes)
    @GetMapping("/getPost/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id) throws PostNotFound{
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    // Update a post
    @PutMapping("/updatePost/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable int id, @RequestBody Post post) throws PostNotFound {
        Post updatedPost = postService.updatePost(id, post);
        return ResponseEntity.ok(updatedPost);
    }

    // Delete (soft delete) a post
    @DeleteMapping("/deletePost/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id) throws PostNotFound {
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted successfully (soft delete)");
    }
}
