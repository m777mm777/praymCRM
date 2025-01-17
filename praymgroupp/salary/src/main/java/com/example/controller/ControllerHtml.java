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
//@RestController
@RequestMapping()
@RequiredArgsConstructor
public class ControllerHtml {

    private final UserService userService;

    @RequestMapping(value = "/greeting")
    public String helloWorldController(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

    @RequestMapping(value = "/user-registration")
    public String createUser(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "user-registration";
    }

    @RequestMapping(value = "/admin-registration")
    public String createAdmin(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "admin-registration";
    }

    @RequestMapping(value = "/super-admin-registration")
    public String createSuperAdmin(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "super-admin-registration";
    }

    @RequestMapping(value = "/get-users")
    public String getUsers(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "get-users";
    }

    @RequestMapping(value = "/get-allUsers")
    public String getAllUsers(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "get-allUsers";
    }

    @RequestMapping(value = "/get_all_salary")
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

    @RequestMapping(value = "/home")
    public String homeAdd() {
        return "home";
    }
}
