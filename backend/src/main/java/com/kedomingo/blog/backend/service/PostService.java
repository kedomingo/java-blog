package com.kedomingo.blog.backend.service;

import static org.apache.commons.text.StringEscapeUtils.escapeHtml4;

import com.kedomingo.blog.backend.entity.Post;
import com.kedomingo.blog.backend.exception.PostNotFoundException;
import com.kedomingo.blog.backend.repository.PostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  public static final int DEFAULT_PAGE = 0;
  public static final int DEFAULT_POST_ITEMS_PER_PAGE = 10;
  private final PostRepository repository;

  public Post create(String content) {
    var clean = escapeHtml4(content);
    var post = Post.builder().content(clean).build();

    return repository.save(post);
  }

  public Post update(Integer id, String content) throws PostNotFoundException {
    var post = getById(id);

    post.setContent(escapeHtml4(content));

    return repository.save(post);
  }

  public void deleteById(Integer id) throws PostNotFoundException {
    var post = getById(id);

    repository.delete(post);
  }

  public Post getById(Integer id) throws PostNotFoundException {
    var post = repository.findById(id);
    if (post.isEmpty()) {
      throw new PostNotFoundException("Post with id " + id + " was not found");
    }
    return post.get();
  }

  public Page<Post> list(Integer page, Integer items) {
    if (page == null) {
      page = DEFAULT_PAGE;
    }
    if (items == null) {
      items = DEFAULT_POST_ITEMS_PER_PAGE;
    }
    var pageRequest = PageRequest.of(page, items);

    return repository.findAll(pageRequest);
  }
}
