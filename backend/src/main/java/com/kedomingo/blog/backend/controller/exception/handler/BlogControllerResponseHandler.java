package com.kedomingo.blog.backend.controller.exception.handler;

import com.kedomingo.blog.backend.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BlogControllerResponseHandler {

  @ExceptionHandler(value = {PostNotFoundException.class})
  protected ResponseEntity<Object> handlePostNotFoundException(PostNotFoundException ex, WebRequest request) {
    return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
  }

  private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
    return ResponseEntity.status(apiError.getStatus())
        .contentType(MediaType.APPLICATION_JSON)
        .body(apiError);
  }
}
