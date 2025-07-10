package com.st.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.blog.beans.Comment;
import com.st.blog.exceptions.CommentsNotFound;
import com.st.blog.repository.CommentsRepository;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentsRepository.save(comment);
    }

    @Override
    public Comment updateComment(Long commentId, Comment updatedComment) throws CommentsNotFound {
        Optional<Comment> existingCommentOpt = commentsRepository.findBycommentId(commentId);

        if (existingCommentOpt.isPresent()) {
            Comment existingComment = existingCommentOpt.get();
            existingComment.setContent(updatedComment.getContent());
            existingComment.setIsDeleted(updatedComment.getIsDeleted());
            existingComment.setUpdatedAt(updatedComment.getUpdatedAt());
            return commentsRepository.save(existingComment);
        } else {
            throw new CommentsNotFound("Comment not found with ID: " + commentId);
        }
    }

    @Override
    public void deleteComment(Long commentId) throws CommentsNotFound {
        Optional<Comment> commentOpt = commentsRepository.findBycommentId(commentId);
        if (commentOpt.isPresent()) {
            commentsRepository.deleteById(commentId.intValue());
        } else {
            throw new CommentsNotFound("Comment not found with ID: " + commentId);
        }
    }

    @Override
    public Comment getCommentById(Long commentId) throws CommentsNotFound {
        return commentsRepository.findBycommentId(commentId)
                .orElseThrow(() -> new CommentsNotFound("Comment not found with ID: " + commentId));
    }

    @Override
    public List<Comment> getAllComments() throws CommentsNotFound {
        List<Comment> commentsList = commentsRepository.findAll();
        if (commentsList.isEmpty()) {
            throw new CommentsNotFound("No comments found.");
        }
        return commentsList;
    }
}
