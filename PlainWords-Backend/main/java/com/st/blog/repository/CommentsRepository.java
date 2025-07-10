package com.st.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.st.blog.beans.Comment;

public interface CommentsRepository extends JpaRepository<Comment,Integer>{
	Optional<Comment> findBycommentId(Long commentId);

}
