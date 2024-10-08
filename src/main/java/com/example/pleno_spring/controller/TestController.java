package com.example.pleno_spring.controller;

import com.example.pleno_spring.model.Test1;
import com.example.pleno_spring.repository.Test1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @Autowired
    private Test1Repository test1Repository;

    @GetMapping("/test")
    public String test() {
        return "{\"message\": \"Hello\"}";
    }

    @PostMapping("/test1/{name}")
    public ResponseEntity<Test1> createTest1(@PathVariable String name) {
        Test1 test1 = new Test1();
        test1.setName(name);
        Test1 savedTest1 = test1Repository.save(test1);
        return ResponseEntity.ok(savedTest1);
    }
}