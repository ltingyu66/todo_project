package com.fortinet.fpc.todo.test_connection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class restTest {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }

}
