package com.example.controller;

import com.example.dto.response.UserResponse;
import com.example.exceptions.ConflictServerError;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ControllerSuperAdmin {

    private final UserService userService;

    @GetMapping(value = "/super-admin/user-registration/{initiatorId}")
    public String userRegistrationH(@PathVariable Long initiatorId) {

        User user = userService.findById(initiatorId);

        String role = user.getRole();

        if(user.getRole().equals("SUPERADMIN")) {
            return "super-admin/user-registration/h/" + initiatorId;
        } else {
            throw new ConflictServerError("Доступ запрещен");
        }
    }

    @GetMapping(value = "/super-admin/admin-registration/{initiatorId}")
    public String adminRegistrationH(@PathVariable Long initiatorId) {

        User user = userService.findById(initiatorId);

        String role = user.getRole();

        if(user.getRole().equals("SUPERADMIN")) {
            return "super-admin/admin-registration/h/" + initiatorId;
        } else {
            throw new ConflictServerError("Доступ запрещен");
        }
    }

//    @GetMapping(value = "/super-admin/super-admin-registration/{initiatorId}")
//    public String superAdminRegistrationH(@PathVariable Long initiatorId) {
//
//        if (initiatorId == 1919L) {
//            return "super-admin/super-admin-registration/h/" + initiatorId;
//        } else {
//            return "Хо-Хо-Хо";
//        }
//    }


    @GetMapping("/super-admin/get-all-users-by-super-admin/{initiatorId}")
    public String getAllUsersH(@PathVariable Long initiatorId) {

        User user = userService.findById(initiatorId);

        String role = user.getRole();

        if(user.getRole().equals("SUPERADMIN")) {
            return "super-admin/get-all-users-by-super-admin/h/" + initiatorId;
        } else {
            throw new ConflictServerError("Доступ запрещен");
        }

    }

    @RequestMapping(value = "/super-admin/get_all_salary/{initiatorId}")
    public String getAllUsers(@PathVariable Long initiatorId) {

        User user = userService.findById(initiatorId);

        if(user.getRole().equals("SUPERADMIN")) {
            return "super-admin/get_all_salary/h/" + initiatorId;
        } else {
            throw new ConflictServerError("Доступ запрещен");
        }
    }

    @RequestMapping(value = "/super-admin/home-super-admin/{initiatorId}")
    public String getHome(@PathVariable Long initiatorId) {

        User user = userService.findById(initiatorId);

        if(user.getRole().equals("SUPERADMIN")) {
            return "super-admin/home-super-admin/h/" + initiatorId;
        } else {
            throw new ConflictServerError("Доступ запрещен");
        }
    }

    @GetMapping("/get-admins/{initiatorId}")
    public List<String> getAdmins(@PathVariable Long initiatorId) {
        log.info("getAdmins initiatorId {}", initiatorId);
        return userService.getAdmins(initiatorId);
    }

}