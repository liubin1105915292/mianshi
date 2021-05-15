package com.luki.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * auther win
 * create 2021/5/14 0014 23:09
 **/
@Controller
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandlerConfig {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindExceptionHandler(BindException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> NullException(NullPointerException e){
        log.error("发生空指针异常6666");
        ResponseEntity<String> body = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()+"空指针异常");
        return body;
    }
}