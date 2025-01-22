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

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class ControllerSalaryAdmin {

    private final SalaryService salaryService;

//    @PostMapping("/{userId}/salary/create")
//    @ResponseStatus(HttpStatus.CREATED)
//    public SalaryResponse crateSalary(@PathVariable Long userId,
//                                          @Validated(Create.class) @RequestBody SalaryRequest request) {
//        log.info("createSalary userId {} request {}", userId, request);
//        return salaryService.create(userId, request);
//    }

    @PatchMapping("/update_all_salary")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SalaryResponse> updateAllSalary(@RequestBody List<SalaryRequest> request) {
        log.info("updateAllSalary request {}", request);
        int i = 1;
        return salaryService.updateAll(request);
    }

//    @PatchMapping("/{userId}/salary/{salaryId}")
////@PostMapping("/rrr")
//    @ResponseStatus(HttpStatus.CREATED)
//    public SalaryResponse updateSalary(@PathVariable Long userId,
//                                       @PathVariable Long salaryId,
//                                      @Validated(Create.class) @RequestBody SalaryRequest request) {
//        log.info("updateSalary userId {} salaryId {} request {}", userId, salaryId, request);
//        int i = 1;
//        return salaryService.update(userId, salaryId, request);
//    }

    @GetMapping("/tt")
    public String tt() {

        return "XyI";
    }

    @GetMapping("/sallarys")
    public List<SalaryResponse> getAllSalarysBy(@RequestParam(required = false) String month,
                                                @RequestParam(required = false) String year,
                                                @RequestParam(required = false) String responsible,
                                                @RequestParam(required = false) String city,
                                                @RequestParam(required = false) String formaoplaty) {
        log.info("getAllSalarys month {} year {} responsible {} city {} formaoplaty {}", month, year, responsible, city, formaoplaty);
        int i = 0;
//        responsible = null;
        return salaryService.getAllBy(month, year, responsible, city, formaoplaty);
    }

//    @GetMapping("/all_sallarys")
//    public List<SalaryResponse> getAllSalarys() {
//        log.info("getAllSalarys");
//        return salaryService.getAll();
//    }

    @GetMapping("/sallarys_current_month")
    public List<SalaryResponse> getForTheCurrentMonth(Long requesterId) {
        log.info("getForTheCurrentMonth requesterId {}", requesterId);
        return salaryService.getForTheCurrentMonth(requesterId);
    }
}
