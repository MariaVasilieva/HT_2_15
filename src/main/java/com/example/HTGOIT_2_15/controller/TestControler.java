package com.example.HTGOIT_2_15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestControler {
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
