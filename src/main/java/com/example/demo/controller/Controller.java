package com.example.demo.controller;

import brave.Span;
import brave.Tracer;
import brave.propagation.ExtraFieldPropagation;
import com.example.demo.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Controller {

    private final Tracer tracer;
    private final MyService myService;

    @GetMapping("/")
    public String printTestString(){
        Long userId = 5L;
        String userIdKey = "key";
        ExtraFieldPropagation.set(userIdKey, userId.toString());
        Span span = tracer.currentSpan();
        span.tag(userIdKey, userId.toString());
        log.info("Controller Called");
        myService.serviceTest();

        return "Test";
    }

}
