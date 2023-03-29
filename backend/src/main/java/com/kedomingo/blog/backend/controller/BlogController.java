package com.kedomingo.blog.backend.controller;

import com.kedomingo.blog.backend.entity.Post;
import com.kedomingo.blog.backend.service.PostService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/blog")
@Slf4j
public class BlogController {

  private final PostService postService;

  @SneakyThrows
  @PostMapping(path = "/", produces = MediaType.TEXT_PLAIN_VALUE)
  @Operation(summary = "Create a blog post")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.BlogController", "method", "create"})
  public ResponseEntity<Integer> create(@RequestBody String content) {

    int postId = postService.create(content).getId();

    return new ResponseEntity<>(postId, HttpStatus.CREATED);
  }

  @SneakyThrows
  @PostMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  @Operation(summary = "Update a blog post")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.BlogController", "method", "update"})
  public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody String content) {
    int postId = postService.update(id, content).getId();

    return new ResponseEntity<>(postId, HttpStatus.OK);
  }

  @SneakyThrows
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Find a blog post")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.BlogController", "method", "find"})
  public ResponseEntity<Post> find(@PathVariable Integer id) {
    Post post = postService.getById(id);

    return new ResponseEntity<>(post, HttpStatus.OK);
  }

  @SneakyThrows
  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "List blog posts")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.BlogController", "method", "list"})
  public ResponseEntity<Page<Post>> list(
      @RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer items
  ) {
    Page<Post> posts = postService.list(page, items);

    return new ResponseEntity<>(posts, HttpStatus.OK);
  }

  @SneakyThrows
  @DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
  @Operation(summary = "Delete a blog post")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.BlogController", "method", "delete"})
  public ResponseEntity<Integer> delete(@PathVariable Integer id) {
    postService.deleteById(id);

    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
