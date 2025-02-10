package com.example.service;

import com.example.dto.request.UserRequest;
import com.example.dto.request.UserRequestUpdate;
import com.example.dto.response.UserResponse;
import com.example.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User findById(UUID id);

    User chekUser(Long phone, String password);

    List<UserResponse> getAll();

    List<String> getAdmins(UUID initiatorId);

    List<UserResponse> updateAllUsersBySuperAdmin(UUID initiatorId, List<UserRequestUpdate> request);

    List<UserResponse> getAllUsersByAdmin(UUID initiatorId,
                                          String city,
                                          String formaoplaty,
                                          String dismissed);

    List<UserResponse> getAllUsersBySuperAdmin(UUID initiatorId,
                                               String responsible,
                                               String city,
                                               String formaoplaty,
                                               String companyName,
                                               String dismissed);

    UserResponse crateUserByAdmin(UUID initiatorId, UserRequest request);

    UserResponse crateUserBySuperAdmin(UUID initiatorId, UserRequest request);

    UserResponse crateAdminBySuperAdmin(UUID initiatorId, UserRequest request);

    UserResponse crateSuperAdminBySuperAdmin(UUID initiatorId, UserRequest request);

}
