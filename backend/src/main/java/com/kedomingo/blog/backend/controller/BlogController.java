package com.kedomingo.blog.backend.controller;

import com.kedomingo.blog.backend.service.PostService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/blog")
@Slf4j
public class BlogController {

  private final PostService postService;

  @SneakyThrows
  @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Create a blog post")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.BlogController", "method", "hello"})
  public ResponseEntity<Integer> create(@RequestBody String content) {

    int postId = postService.create(content).getId();

    return new ResponseEntity<>(postId, HttpStatus.CREATED);
  }
}
