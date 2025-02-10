package com.example.service;

import com.example.dto.request.UserRequest;
import com.example.dto.request.UserRequestUpdate;
import com.example.dto.response.UserResponse;
import com.example.model.User;

import java.util.List;

public interface UserService {

    User findById(Long id);

    User chekUser(Long phone, String password);

    List<UserResponse> getAll();

    List<String> getAdmins(Long initiatorId);

    List<UserResponse> updateAllUsersBySuperAdmin(Long initiatorId, List<UserRequestUpdate> request);

    List<UserResponse> getAllUsersByAdmin(Long initiatorId,
                                          String city,
                                          String formaoplaty,
                                          String dismissed);

    List<UserResponse> getAllUsersBySuperAdmin(Long initiatorId,
                                               String responsible,
                                               String city,
                                               String formaoplaty,
                                               String companyName,
                                               String dismissed);

    UserResponse crateUserByAdmin(Long initiatorId, UserRequest request);

    UserResponse crateUserBySuperAdmin(Long initiatorId, UserRequest request);

    UserResponse crateAdminBySuperAdmin(Long initiatorId, UserRequest request);

    UserResponse crateSuperAdminBySuperAdmin(Long initiatorId, UserRequest request);

}
