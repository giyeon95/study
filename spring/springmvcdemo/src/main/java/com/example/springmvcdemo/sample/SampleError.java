package com.example.springmvcdemo.sample;

import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SampleError {

    private String key;
    private String message;

    @Builder
    private SampleError(String key, String message) {
        this.key = Objects.requireNonNullElse(key, "Default Key");
        this.message = Objects.requireNonNullElse(message, "defaultMessage");
    }
}
