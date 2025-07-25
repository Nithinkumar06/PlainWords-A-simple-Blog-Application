package com.st.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.st.blog.beans.Like;

public interface ILikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByLikeId(Long likeId);
}
