package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CseController {

    @GetMapping("/cseadd")
    public String addCSE(@RequestParam int a, @RequestParam int b) {
        int result = a + b;
        return "After performing the addition, the final output is " 
                + result + ". My roll number is 23MH1A05N8.";
    }
}