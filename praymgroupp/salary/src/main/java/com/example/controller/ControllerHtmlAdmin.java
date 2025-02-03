package com.example.controller;

import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ControllerHtmlAdmin {

    private final UserService userService;

    @RequestMapping(value = "/admin/user-registration/h/{initiatorId}")
    public String createUser() {
        return "user-registration-by-admin";
    }

    @RequestMapping(value = "/admin/get-all-users/h/{initiatorId}")
    public String getAllUsers() {
        return "get-all-users-by-admin";
    }

    @RequestMapping(value = "/admin/get-all-salary/h/{initiatorId}")
    public String getAllSalary() {
        return "get-all-salary-by-admin";
    }

    @RequestMapping(value = "admin/home-admin/h/{initiatorId}")
    public String homeAdmin() {
        return "home-admin";
    }

}