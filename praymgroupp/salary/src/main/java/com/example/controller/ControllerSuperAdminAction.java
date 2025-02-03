package com.example.controller;

import com.example.createAndUpdate.Create;
import com.example.createAndUpdate.Update;
import com.example.dto.request.SalaryRequest;
import com.example.dto.request.UserRequest;
import com.example.dto.request.UserRequestUpdate;
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

@Slf4j
@RestController
@RequestMapping(path = "/super-admin")
@RequiredArgsConstructor
public class ControllerSuperAdminAction {

    private final UserService userService;
    private final SalaryService salaryService;

    @PostMapping("/create-user/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateUserBySuperAdmin(@PathVariable Long initiatorId,
                                         @Validated(Create.class) @RequestBody UserRequest request) {
        log.info("crateUserByAdmin initiatorId {} request {}", initiatorId, request);
        return userService.crateUserBySuperAdmin(initiatorId, request);
    }

    @PostMapping("/create-admin/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateAdminBySuperAdmin(@PathVariable Long initiatorId,
                                              @Validated(Create.class) @RequestBody UserRequest request) {
        log.info("crateAdminBySuperAdmin initiatorId {} request {}", initiatorId, request);
        return userService.crateAdminBySuperAdmin(initiatorId, request);
    }

    @PostMapping("/create-super-admin/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse crateSuperAdminBySuperAdmin(@PathVariable Long initiatorId,
                                               @Validated(Create.class) @RequestBody UserRequest request) {
        log.info("crateSuperAdminBySuperAdmin initiatorId {} request {}", initiatorId, request);
        return userService.crateSuperAdminBySuperAdmin(initiatorId, request);
    }

    @GetMapping("/all-users-by-super-admin/{initiatorId}")
    public List<UserResponse> getAllUsersBySuperAdmin(@PathVariable Long initiatorId,
                                                      @RequestParam(required = false) String responsible,
                                                      @RequestParam(required = false) String city,
                                                      @RequestParam(required = false) String formaoplaty,
                                                      @RequestParam(required = false) String dismissed) {
        log.info("getAllUsersBySuperAdmin initiatorId {} responsible {} city {} formaoplaty {} dismissed {}",
                initiatorId, responsible, city, formaoplaty, dismissed);
        return userService.getAllUsersBySuperAdmin(initiatorId, responsible, city, formaoplaty, dismissed);
    }

    @PostMapping("/update-all-users/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UserResponse> updateAllUsersBySuperAdmin(@Validated(Update.class) @PathVariable Long initiatorId,
                                                             @RequestBody List<UserRequestUpdate> request) {

        log.info("updateAllUsersBySuperAdmin initiatorId {} request {}", initiatorId, request);
        return userService.updateAllUsersBySuperAdmin(initiatorId, request);
    }

    @GetMapping("/sallarys-by-super-admin/{initiatorId}")
    public List<SalaryResponse> getAllSalarysBy(@PathVariable Long initiatorId,
                                                @RequestParam(required = false) String month,
                                                @RequestParam(required = false) String year,
                                                @RequestParam(required = false) String responsible,
                                                @RequestParam(required = false) String city,
                                                @RequestParam(required = false) String formaoplaty,
                                                @RequestParam(required = false) String dismissed) {
        log.info("getAllSalarysBy initiatorId {} month {} year {} responsible {} city {} formaoplaty {} dismissed {}",
                initiatorId, month, year, responsible, city, formaoplaty, dismissed);
        return salaryService.getAllBySuperAdmin(initiatorId, month, year, responsible, city, formaoplaty, dismissed);
    }

    @PatchMapping("/update-all-salary-by-super-admin/{initiatorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SalaryResponse> updateAllSalaryBySuperAdmin(@PathVariable Long initiatorId, @RequestBody List<SalaryRequest> request) {
        log.info("updateAllSalary initiatorId {} request {}", initiatorId, request);
        return salaryService.updateAllSalaryBySuperAdmin(initiatorId, request);
    }

    @GetMapping("/get-admins/{initiatorId}")
    public List<String> getAdmins(@PathVariable Long initiatorId) {
        log.info("getAdmins initiatorId {}", initiatorId);
        return userService.getAdmins(initiatorId);
    }
}