package com.example.controller;

import com.example.createAndUpdate.Create;
import com.example.dto.request.UserRequest;
import com.example.dto.response.UserResponse;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/super-admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/create-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateAdmin(@Validated(Create.class) @RequestBody UserRequest request) {
        int i = 10;
        log.info("crateAdmin request {}", request);
        return userService.crateAdmin(request);
    }

    @GetMapping("/tr")
    @ResponseStatus(HttpStatus.CREATED)
    public String tt() {

        return "XyI";
    }

    @PostMapping("/create-super-admin")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateSuperAdmin(@Validated(Create.class) @RequestBody UserRequest request) {
        log.info("crateSuperAdmin request {}", request);
        return userService.crateSuperAdmin(request);
    }

}
