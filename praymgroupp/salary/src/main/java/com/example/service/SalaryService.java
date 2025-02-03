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

    List<SalaryResponse> updateAllSalaryByAdmin(Long initiatorId, List<SalaryRequest> requests);

    List<SalaryResponse> updateAllSalaryBySuperAdmin(Long initiatorId, List<SalaryRequest> requests);

    List<SalaryResponse> getAllBySuperAdmin(Long initiatorId,
                                            String month,
                                            String year,
                                            String responsible,
                                            String city,
                                            String formaoplaty,
                                            String dismissed);

    List<SalaryResponse> getAllSalarysByAdmin(Long initiatorId, String city, String formaoplaty);
}
