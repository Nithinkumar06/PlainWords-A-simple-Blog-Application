package com.st.blog.exceptions;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFound.class)
    public ResponseEntity<String> Exception1(PostNotFound exception) {
        return new ResponseEntity<>("custom exception"+exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(CommentsNotFound.class)
    public ResponseEntity<String> Exception2(CommentsNotFound exception) {
        return new ResponseEntity<>("custom exception"+exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(LikeNotFound.class)
    public ResponseEntity<String> Exception3(LikeNotFound exception) {
        return new ResponseEntity<>("custom exception"+exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<String> Exception4(UserNotFound exception) {
        return new ResponseEntity<>("custom exception"+exception.getMessage(), HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(FollowerNotFound.class)
    public ResponseEntity<String> Exception5(FollowerNotFound exception) {
        return new ResponseEntity<>("custom exception"+exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}