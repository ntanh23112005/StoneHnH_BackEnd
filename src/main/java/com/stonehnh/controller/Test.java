package com.stonehnh.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/test")
public class Test {

    @GetMapping
    public String test() {
        String name = String.valueOf(UUID.randomUUID());
        return name;
    }
}
