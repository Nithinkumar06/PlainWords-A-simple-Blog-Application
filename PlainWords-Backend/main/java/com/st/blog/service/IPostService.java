package com.st.blog.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.st.blog.beans.Post;
import com.st.blog.exceptions.PostNotFound;
@Component
public interface IPostService {
    Post createPost(Post post);
    List<Post> getAllPosts();
    Post getPostById(int postId) throws PostNotFound;
    Post updatePost(int postId, Post postDTO) throws PostNotFound;
    void deletePost(int postId) throws PostNotFound;
}
