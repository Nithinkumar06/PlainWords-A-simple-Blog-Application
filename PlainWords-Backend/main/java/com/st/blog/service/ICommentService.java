package com.st.blog.service;

import java.util.List;
import java.util.Optional;

import com.st.blog.beans.Comment;
import com.st.blog.exceptions.CommentsNotFound;

public interface ICommentService {
    Comment addComment(Comment comment);
    Comment updateComment(Long commentId, Comment updatedComment) throws CommentsNotFound;
    void deleteComment(Long commentId) throws CommentsNotFound;
    Comment getCommentById(Long commentId)  throws CommentsNotFound;
    List<Comment> getAllComments() throws CommentsNotFound;
}

