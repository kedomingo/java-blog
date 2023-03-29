package com.kedomingo.blog.backend.repository;

import com.kedomingo.blog.backend.entity.Post;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
