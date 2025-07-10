package com.st.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.st.blog.beans.Comment;
import com.st.blog.exceptions.CommentsNotFound;
import com.st.blog.service.ICommentService;

@RestController
@RequestMapping("/blog/api/comments")
@CrossOrigin(origins = "*") // Optional - allow frontend calls from all origins
public class CommentController {

    @Autowired
    private ICommentService commentService;

    // Add a new comment
    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.addComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    // Update a comment by ID
    @PutMapping("/update/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId,
                                                 @RequestBody Comment updatedComment) {
        try {
            Comment updated = commentService.updateComment(commentId, updatedComment);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (CommentsNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a comment by ID
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentId) {
        try {
            commentService.deleteComment(commentId);
            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } catch (CommentsNotFound e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Get a comment by ID
    @GetMapping("/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long commentId) {
        try {
            Comment comment = commentService.getCommentById(commentId);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (CommentsNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get all comments
    @GetMapping("/getAll")
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            List<Comment> commentList = commentService.getAllComments();
            return new ResponseEntity<>(commentList, HttpStatus.OK);
        } catch (CommentsNotFound e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
}
