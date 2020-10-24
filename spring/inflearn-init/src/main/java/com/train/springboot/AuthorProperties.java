package com.train.springboot;

import java.time.Duration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("author")
public class AuthorProperties {

    private String name;

    private int age;
    private String fullName;
    private Duration sessionTimeout = Duration.ofSeconds(60);
}
