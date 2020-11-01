package org.acme.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/springweb/hello")
public class ExampleController {

    @GetMapping
    public String hello() {
        return "hello";
    }
}
