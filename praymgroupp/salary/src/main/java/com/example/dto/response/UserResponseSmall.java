package com.example.dto.response;

import com.example.enums.CategoryUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserResponseSmall {

    private UUID id;

    private String name;

    private String lastName;

    private String category;

}