package com.example.service;

import com.example.createAndUpdate.Create;
import com.example.dto.request.UserRequest;
import com.example.dto.response.UserResponse;
import com.example.model.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    UserResponse crateUser(String ownerLastName, UserRequest request);

    UserResponse crateAdmin(UserRequest request);

    UserResponse crateSuperAdmin(UserRequest request);

    UserResponse getById(Long id);

    User chekUser(Integer phone, String password);

    List<UserResponse> getAll();

    List<String> getAdmins();

}
