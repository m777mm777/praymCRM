package com.example.controller;

import com.example.createAndUpdate.Create;
import com.example.dto.request.Auth;
import com.example.dto.request.UserRequest;
import com.example.dto.response.UserResponse;
import com.example.enums.CategoryUser;
import com.example.model.User;
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
@Controller
@RequestMapping()
@RequiredArgsConstructor
public class ControllerHtml {

    private final UserService userService;

//    @RequestMapping(value = "/greeting")
//    public String helloWorldController(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "greeting";
//    }

    @RequestMapping(value = "/user-registration/{initiatorId}")
    public String createUser(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "user-registration";
    }

//    @RequestMapping(value = "/admin-registration/{initiatorId}")
//    public String createAdmin(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "admin-registration";
//    }

//    @RequestMapping(value = "/super-admin-registration/{initiatorId}")
//    public String createSuperAdmin(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "super-admin-registration";
//    }

    @RequestMapping(value = "/get-users/{initiatorId}")
    public String getUsers(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "get-users";
    }

//    @RequestMapping(value = "/get-allUsers/{initiatorId}")
//    public String getAllUsers(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
//        model.addAttribute("name", name);
//        return "get-allUsers";
//    }

    @RequestMapping(value = "/get_all_salary/{initiatorId}")
    public String getAllSalary(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "get_all_salary";
    }

    @RequestMapping(value = "/index")
    public String goToHome() {

        return "index";
    }

//    @RequestMapping(value = "/auth")
//    public String auth(@RequestBody Auth auth) {
//
//        User user = userService.chekUser(auth.getLogin(), auth.getPassword());
//
//        String role = user.getRole();
//        String html = " ";
//        switch (role) {
//            case "SUPERADMIN":
//                return  "home-add";
//            case "ADMIN":
//                return  "home";
//        }
//        return "home";
//    }

//    @RequestMapping(value = "/home/{initiatorId}")
//    public String home() {
//        return "home";
//    }

//    @RequestMapping(value = "/home-admin/{initiatorId}")
//    public String homeAdmin() {
//        return "home-admin";
//    }

    @RequestMapping(value = "/home-super-admin/{initiatorId}")
    public String homeSuperAdmin() {
        return "home-super-admin";
    }

    @RequestMapping(value = "/tr/{initiatorId}")
    public String createSuperAdminnn(@PathVariable Long initiatorId) {
        return "super-admin-registration";
    }
}
