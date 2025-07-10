package com.st.blog.service;

import java.util.List;

import com.st.blog.beans.Like;
import com.st.blog.exceptions.LikeNotFound;

public interface ILikeService {
    Like addLike(Like like);
    Like updateLike(Long likeId, Like like) throws LikeNotFound;
    void deleteLike(Long likeId) throws LikeNotFound;
    Like getLikeById(Long likeId) throws LikeNotFound;
    List<Like> getAllLikes() throws LikeNotFound;
}
