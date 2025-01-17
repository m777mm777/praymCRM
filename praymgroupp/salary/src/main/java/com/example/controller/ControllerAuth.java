package com.example.controller;

import com.example.dto.request.Auth;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerAuth {

    private final UserService userService;

    @PostMapping(value = "/auth")
    public String auth(@RequestBody Auth auth) {

        User user = userService.chekUser(auth.getLogin(), auth.getPassword());

        String role = user.getRole();
        String html = " ";
        switch (role) {
            case "SUPERADMIN":
                return  "home";
            case "ADMIN":
                return  "home";
        }
        return "home";
    }
}
