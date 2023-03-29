package com.kedomingo.blog.backend.service;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

import com.kedomingo.blog.backend.entity.Post;
import com.kedomingo.blog.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository repository;

  public Post create(String content) {
    var clean = escapeHtml4(content);
    var post = Post.builder().content(clean).build();

    return repository.save(post);
  }
}
