package org.keldeari.amber.controller;

import org.keldeari.amber.model.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
    
    private final MongoTemplate mongoTemplate;

    @GetMapping("/test")
    public String test() {

        User user = new User();
        user.setName("Jon");
        mongoTemplate.insert(user, "user");

        return "Hello";
    }
}
