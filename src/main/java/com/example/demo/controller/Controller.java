package com.example.demo.controller;

import brave.propagation.ExtraFieldPropagation;
import com.example.demo.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {

    private final Tracer tracer;
    private final MyService myService;

    // I've rewritten this code to use the Sleuth abstraction
    @GetMapping("/")
    public String printTestString(){
        Long userId = 5L;
        String userIdKey = "key";
        tracer.createBaggage("key", userId.toString());
        Span span = tracer.currentSpan();
        span.tag(userIdKey, userId.toString());
        log.info("Controller Called");
        myService.serviceTest();

        return "Test";
    }

}
