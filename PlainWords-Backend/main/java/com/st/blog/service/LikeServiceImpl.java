package com.st.blog.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.st.blog.beans.Like;
import com.st.blog.exceptions.LikeNotFound;
import com.st.blog.repository.ILikeRepository;

@Service
public class LikeServiceImpl implements ILikeService {

    @Autowired
    private ILikeRepository likeRepository;

    @Override
    public Like addLike(Like like) {
        like.setCreatedAt(LocalDateTime.now());
        like.setUpdatedAt(LocalDateTime.now());
        return likeRepository.save(like);
    }

    @Override
    public Like updateLike(Long likeId, Like like) throws LikeNotFound {
        Optional<Like> existingLike = likeRepository.findById(likeId);
        if (existingLike.isPresent()) {
            Like l = existingLike.get();
            l.setPostId(like.getPostId());
            l.setUserId(like.getUserId());
            l.setIsDeleted(like.getIsDeleted());
            l.setUpdatedAt(LocalDateTime.now());
            return likeRepository.save(l);
        } else {
            throw new LikeNotFound("Like with ID " + likeId + " not found");
        }
    }

    @Override
    public void deleteLike(Long likeId) throws LikeNotFound {
        Optional<Like> like = likeRepository.findById(likeId);
        if (like.isPresent()) {
            likeRepository.deleteById(likeId);
        } else {
            throw new LikeNotFound("Like with ID " + likeId + " not found");
        }
    }

    @Override
    public Like getLikeById(Long likeId) throws LikeNotFound {
        return likeRepository.findById(likeId)
                .orElseThrow(() -> new LikeNotFound("Like with ID " + likeId + " not found"));
    }

    @Override
    public List<Like> getAllLikes() throws LikeNotFound {
        List<Like> likes = likeRepository.findAll();
        if (likes.isEmpty()) {
            throw new LikeNotFound("No likes found");
        }
        return likes;
    }
}
