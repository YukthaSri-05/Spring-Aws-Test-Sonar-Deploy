package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CseController {

    @GetMapping("/cseadd")
    public int addCSE(@RequestParam int a,@RequestParam int b) {
        return a+b;
    }
    
}