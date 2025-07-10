package com.st.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.st.blog.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
//    List<Post> findByUserIdAndIsDeletedFalse(int userId);
    List<Post> findByIsDeletedFalse(); // for fetching only active (non-deleted) posts
}
