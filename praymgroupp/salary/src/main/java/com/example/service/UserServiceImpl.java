package com.example.service;

import com.example.dto.request.UserRequest;
import com.example.dto.response.UserResponse;
import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.UserMapper;
import com.example.model.User;
import com.example.repository.SalaryRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SalaryService salaryService;
    private final SalaryRepository salaryRepository;

    @Override
    public UserResponse crateUser(String ownerLastName, UserRequest request) {
        User user = userMapper.toUser(request);
        user.setOwner(userRepository.findByLastName(ownerLastName)
                .orElseThrow(() -> new ResourceNotFoundException("Ответственный не найден")));
        user.setDismissed(false);

        user = userRepository.save(user);
        salaryRepository.save(salaryService.createToUser(user));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse crateAdmin(UserRequest request) {
        User user = userMapper.toUser(request);
        user.setOwner(userRepository.findByRole("SUPERADMIN").orElseThrow());
        user.setDismissed(false);
        user = userRepository.save(user);
        salaryRepository.save(salaryService.createToUser(user));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse crateSuperAdmin(UserRequest request) {
        User user = userMapper.toUser(request);
        user.setOwner(user);
        user.setDismissed(false);
        user = userRepository.save(user);

//        salaryRepository.save(salaryService.createToUser(user));

        return userMapper.toResponse(user);

    }

    @Override
    public UserResponse getById(Long id) {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));

        return userMapper.toResponse(user);
    }

    @Override
    public User chekUser(Integer phone, String password) {

        return userRepository.findByPhoneAndPassword(phone, password)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь не найден"));
    }

    @Override
    public List<UserResponse> getAll() {

        List<User> users = userRepository.findAll();

        return userMapper.toResponseCollection(users);
    }

    @Override
    public List<String> getAdmins() {
        List<String> lastNameAdmins = new ArrayList<>();
        List<User> admins = userRepository.findAllByRole("ADMIN");

        if (!admins.isEmpty()) {
            lastNameAdmins.add("Ответственный");
            for (User user : admins) {
                lastNameAdmins.add(user.getLastName());
            }
        }

        return lastNameAdmins;
    }
}