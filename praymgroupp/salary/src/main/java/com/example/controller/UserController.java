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

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user/{ownerLastName}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateUser(@PathVariable String ownerLastName,
                                  @Validated(Create.class) @RequestBody UserRequest request) {
        log.info("crateUser ownerLastName {} request {}", ownerLastName, request);
        return userService.crateUser(ownerLastName, request);
    }

    @GetMapping("/get-admins")
    public List<String> getAdmins() {
        int i = 0;
//        log.info("crateUser ownerLastName {} request {}", ownerLastName, request);

        List<String> strings = userService.getAdmins();
        return strings;
//        return userService.getAdmins();
    }
}