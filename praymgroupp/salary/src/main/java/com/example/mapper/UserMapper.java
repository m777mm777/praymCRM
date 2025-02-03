package com.example.mapper;

import com.example.dto.request.UserRequest;
import com.example.dto.request.UserRequestUpdate;
import com.example.dto.response.UserResponse;
import com.example.model.User;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final UserRepository userRepository;

    public User toUser(UserRequest request) {
        if (request == null) {
            return null;
        }

        User user = new User();
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        user.setCity(request.getCity());
        user.setCategory(request.getCategory());
        if(request.getBankAccountNumber() != null) {
            user.setBankAccountNumber(request.getBankAccountNumber());
        }
        user.setDismissed(request.getDismissed());

        return user;
    }

    public User toUserUpdate(UserRequestUpdate request) {
        if (request == null) {
            return null;
        }

        User userOld = userRepository.getById(request.getId());

        User user = new User();
        user.setId(request.getId());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPatronymic(request.getPatronymic());
        user.setPassword(userOld.getPassword());
        user.setPhone(request.getPhone());
        user.setRole(userOld.getRole());
        user.setCity(request.getCity());
        user.setCategory(request.getCategory());
        if (!request.getOwnerLastName().equals("null")) {
            user.setOwner(userRepository.findByLastName(request.getOwnerLastName()).orElseThrow());
        }
        if(request.getBankAccountNumber() != null) {
            user.setBankAccountNumber(request.getBankAccountNumber());
        }
        user.setDismissed(request.getDismissed());

        return user;
    }

    public UserResponse toResponse(User user) {
        if (user == null) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();
        userResponse.id(user.getId());
        userResponse.name(user.getName());
        userResponse.lastName(user.getLastName());
        userResponse.patronymic(user.getPatronymic());
        userResponse.phone(user.getPhone());
        userResponse.category(user.getCategory());
        userResponse.city(user.getCity());
        if (user.getOwner() != null) {
            userResponse.ownerLastName(user.getOwner().getLastName());
        }

        if (user.getBankAccountNumber() != null) {
            userResponse.bankAccountNumber(user.getBankAccountNumber().toString());
        }

        userResponse.dismissed(user.getDismissed());

        return userResponse.build();
    }

    public List<UserResponse> toResponseCollection(List<User> users) {
        if (users == null) {
            return null;
        }
        List<UserResponse> list = new ArrayList<UserResponse>(users.size());

        for (User user : users) {
            list.add(toResponse(user));
        }

        return list;
    }

    public List<User> toUserCollection(List<UserRequest> requests) {
        if (requests == null) {
            return null;
        }
        List<User> list = new ArrayList<User>(requests.size());

        for (UserRequest request : requests) {
            list.add(toUser(request));
        }

        return list;
    }

    public List<User> toUserCollectionUpdate(List<UserRequestUpdate> requests) {
        if (requests == null) {
            return null;
        }
        List<User> list = new ArrayList<User>(requests.size());

        for (UserRequestUpdate request : requests) {
            list.add(toUserUpdate(request));
        }

        return list;
    }
}
