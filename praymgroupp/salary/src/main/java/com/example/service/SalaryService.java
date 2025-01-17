package com.example.service;

import com.example.dto.request.SalaryRequest;
import com.example.dto.request.UserRequest;
import com.example.dto.response.SalaryResponse;
import com.example.dto.response.UserResponse;
import com.example.model.Salary;
import com.example.model.User;

import java.time.LocalDate;
import java.util.List;

public interface SalaryService {

    Salary createToUser(User user);

//    SalaryResponse update(Long userId, Long salaryId, SalaryRequest request);

    SalaryResponse getById(Long id);

    List<SalaryResponse> updateAll(List<SalaryRequest> requests);

    List<SalaryResponse> getAllBy(String month,String year, String responsible,String city, String formaoplaty);

    Long remove(Long id);

    List<SalaryResponse> getForTheCurrentMonth(Long requesterId);
}
