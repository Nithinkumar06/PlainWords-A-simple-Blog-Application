package com.st.blog.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.blog.beans.Post;
import com.st.blog.exceptions.PostNotFound;
import com.st.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        post.setDeleted(false);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findByIsDeletedFalse();
    }

    @Override
    public Post getPostById(int postId) throws PostNotFound {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound("Post not found with ID: " + postId));
        if (post.isDeleted()) {
            throw new PostNotFound("Post is deleted");
        }
        return post;
    }

    @Override
    public Post updatePost(int postId, Post updatedPostData) throws PostNotFound{
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound("Post not found with ID: " + postId));
        
        if (post.isDeleted()) {
            throw new PostNotFound("Post is deleted");
        }

        post.setTitle(updatedPostData.getTitle());
        post.setContent(updatedPostData.getContent());
        post.setUpdatedAt(LocalDateTime.now());

        return postRepository.save(post);
    }

    @Override
    public void deletePost(int postId) throws PostNotFound {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFound("Post not found with ID: " + postId));

        post.setDeleted(true); // soft delete
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
    }
}
