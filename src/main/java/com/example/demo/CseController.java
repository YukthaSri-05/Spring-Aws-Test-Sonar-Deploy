package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CseController {

    @GetMapping("/cseadd")
    public int addCSE(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/rollno")
    public String getRollNoMessage() {
        return "After performing the addition, the final output is 30. My roll number is 23MH1A05N8.";
    }
}