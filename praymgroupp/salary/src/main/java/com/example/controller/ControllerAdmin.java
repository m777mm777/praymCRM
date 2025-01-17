package com.example.controller;

import com.example.createAndUpdate.Create;
import com.example.dto.request.UserRequest;
import com.example.dto.response.UserResponse;
import com.example.enums.CategoryUser;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class ControllerAdmin {

    private final UserService userService;

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
//        log.info("GetAllUsers ids {}", ids, from, size);
        return userService.getAll();
    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public String crateUser(@RequestBody String request) {
//        log.info("create User {}", request);
//        String f = request;
//        return f;
//    }

//    @PatchMapping("/{userId}")
//    public UserResponse updateUser(@PathVariable Long userId,
//                                   @Valid @RequestBody UserRequest request) {
//        log.info("UpdateUser userId {} request {}", userId, request);
//        return userService.update(userId, request);
//    }
//
    @GetMapping()
    public UserResponse getById() {
//        log.info("GetById id {}", id);
        return userService.getById(1L);
    }
//
//    @GetMapping()
//    public List<UserResponse> getAllUsers() {
////        log.info("GetAllUsers ids {}", ids, from, size);
//        return userService.getAll();
//    }
//
//    @DeleteMapping("/{userId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public Long removeById(@PathVariable Long userId) {
//        log.info("removeById userId {}", userId);
//        return userService.remove(userId);
//    }

}
