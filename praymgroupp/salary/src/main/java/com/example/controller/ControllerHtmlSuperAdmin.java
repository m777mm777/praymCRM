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
public class ControllerHtmlSuperAdmin {

    private final UserService userService;

    @RequestMapping(value = "/super-admin/user-registration/h/{initiatorId}")
    public String createUser() {
        return "user-registration-by-super-admin";
    }

    @RequestMapping(value = "/super-admin/admin-registration/h/{initiatorId}")
    public String createAdmin() {
        return "admin-registration-bu-super-admin";
    }

    @RequestMapping(value = "/super-admin/super-admin-registration/h/{initiatorId}")
    public String createSuperAdminnn() {
        return "super-admin-registration";
    }

    @RequestMapping(value = "/super-admin/get-all-users-by-super-admin/h/{initiatorId}")
    public String getAllUsers() {
        return "get-all-users-by-super-admin";
    }

    @RequestMapping(value = "/super-admin/get-all-salary/h/{initiatorId}")
    public String getAllSalary() {
        return "get-all-salary-by-super-admin";
    }

    @RequestMapping(value = "/super-admin/home-super-admin/h/{initiatorId}")
    public String homeSuperAdmin() {
        return "home-super-admin";
    }

}
