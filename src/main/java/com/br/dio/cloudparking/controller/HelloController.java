package com.br.dio.cloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore // notacao do swagger springfox
public class HelloController {

    @GetMapping
    public String message(){
        return "Hello Devs";
    }
}
