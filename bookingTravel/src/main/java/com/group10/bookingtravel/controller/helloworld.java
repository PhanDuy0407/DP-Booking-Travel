package com.group10.bookingtravel.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworld {
    @GetMapping("/hello")
    public String get(){
        return "Hello world";
    }
}
