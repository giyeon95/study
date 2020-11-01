package com.example.springmvcdemo.sample;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "kiyeon_kim");
        return "hello";
    }

    @GetMapping(value = "/exception")
    public @ResponseBody
    ResponseEntity<SampleError> hello() {
        throw new SampleException();
    }

}
