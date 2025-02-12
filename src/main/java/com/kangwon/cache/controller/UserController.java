package com.kangwon.cache.controller;

import com.kangwon.cache.domain.entity.RedisHashUser;
import com.kangwon.cache.domain.entity.User;
import com.kangwon.cache.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser3(id);
    }

    @GetMapping("/redishash-users/{id}")
    public RedisHashUser getUser2(@PathVariable Long id) {
        return userService.getUser2(id);
    }

    @GetMapping("/")
    String home() {
        return "OK";
    }
}