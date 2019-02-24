package com.neverland.hellospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Pefan_Li
 * Created Time 2019-2-24 21:35.
 */
@RestController
public class Hello {
    @GetMapping("/hello")
    public String hello(){
        return "hello spring";
    }
}
