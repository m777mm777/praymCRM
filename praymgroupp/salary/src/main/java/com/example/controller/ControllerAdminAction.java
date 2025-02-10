package com.example.controller;

import com.example.createAndUpdate.Create;
import com.example.dto.request.SalaryRequest;
import com.example.dto.request.UserRequest;
import com.example.dto.response.SalaryResponse;
import com.example.dto.response.UserResponse;
import com.example.service.SalaryService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class ControllerAdminAction {

    private final UserService userService;
    private final SalaryService salaryService;

    @GetMapping("/all-users/{initiatorId}")
    public List<UserResponse> getAllUsersByAdmin(@PathVariable UUID initiatorId,
                                                 @RequestParam(required = false) String city,
                                                 @RequestParam(required = false) String formaoplaty,
                                                 @RequestParam(required = false) String dismissed) {
        log.info("getAllUsersByAdmin initiatorId {} city {} formaoplaty {} dismissed {}",
                initiatorId, city, formaoplaty, dismissed);
        return userService.getAllUsersByAdmin(initiatorId, city, formaoplaty, dismissed);
    }

    @PostMapping("/create-user/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateUserByAdmin(@PathVariable UUID initiatorId,
                                  @Validated(Create.class) @RequestBody UserRequest request) {
        log.info("crateUserByAdmin initiatorId {} request {}", initiatorId, request);
        return userService.crateUserByAdmin(initiatorId, request);
    }

    @GetMapping("/all-salarys-by-admin/{initiatorId}")
    public List<SalaryResponse> getAllSalarysBy(@PathVariable UUID initiatorId,
                                                @RequestParam(required = false) String city,
                                                @RequestParam(required = false) String formaoplaty) {
        log.info("getAlSalarys initiatorId {} city {} formaoplaty {}", initiatorId, city, formaoplaty);
        return salaryService.getAllSalarysByAdmin(initiatorId, city, formaoplaty);
    }

    @PatchMapping("/update-all-salary-by-admin/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SalaryResponse> updateAllSalaryByAdmin(@PathVariable UUID initiatorId, @RequestBody List<SalaryRequest> request) {
        log.info("updateAllSalary initiatorId {} request {}", initiatorId, request);
        return salaryService.updateAllSalaryByAdmin(initiatorId, request);
    }
}