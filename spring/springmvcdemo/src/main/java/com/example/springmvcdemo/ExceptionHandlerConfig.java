package com.example.springmvcdemo;

import com.example.springmvcdemo.sample.SampleError;
import com.example.springmvcdemo.sample.SampleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler({SampleException.class})
    public @ResponseBody
    ResponseEntity<SampleError> sampleErrorHandler(SampleException e) {
        return ResponseEntity.badRequest()
            .body(SampleError.builder()
                .key("test")
                .message("value")
                .build());
    }
}
