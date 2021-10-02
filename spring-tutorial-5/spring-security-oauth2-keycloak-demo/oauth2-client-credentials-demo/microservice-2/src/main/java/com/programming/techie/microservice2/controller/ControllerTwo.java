package com.programming.techie.microservice2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mcs2")
public class ControllerTwo {

    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.OK)
    public String helloRestTemplate() {
        return "Hello mcs 2";
    }

}