package com.laba.store.repos;

import com.laba.store.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
    Post findByPost(String post);
}
