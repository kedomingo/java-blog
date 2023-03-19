package com.kedomingo.blog.backend.controller;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/")
@Slf4j
public class HelloController {

  @GetMapping(path = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary = "Hello World")
  @Timed(value = "method_call",
      extraTags = {"class", "com.kedomingo.blog.backend.controller.HelloController", "method", "hello"})
  public ResponseEntity<String> hello() {

    log.info("Hello World from HelloController");
    return new ResponseEntity<>("Hello World", HttpStatus.OK);
  }
}
