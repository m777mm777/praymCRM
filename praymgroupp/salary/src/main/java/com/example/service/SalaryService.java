package com.example.service;

import com.example.dto.request.SalaryRequest;
import com.example.dto.request.UserRequest;
import com.example.dto.response.SalaryResponse;
import com.example.dto.response.UserResponse;
import com.example.model.Salary;
import com.example.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SalaryService {

    Salary createToUser(User user);

    List<SalaryResponse> updateAllSalaryByAdmin(UUID initiatorId, List<SalaryRequest> requests);

    List<SalaryResponse> updateAllSalaryBySuperAdmin(UUID initiatorId, List<SalaryRequest> requests);

    List<SalaryResponse> getAllBySuperAdmin(UUID initiatorId,
                                            String month,
                                            String year,
                                            String responsible,
                                            String city,
                                            String formaoplaty,
                                            String companyName,
                                            String dismissed);

    List<SalaryResponse> getAllSalarysByAdmin(UUID initiatorId, String city, String formaoplaty);
}
