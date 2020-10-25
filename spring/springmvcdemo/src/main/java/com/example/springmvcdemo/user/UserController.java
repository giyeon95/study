package com.example.springmvcdemo.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String getHello() {
        return "hello";
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return user;
    }

}
