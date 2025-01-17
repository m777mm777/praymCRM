package com.example.dto.response;

import com.example.enums.CategoryUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponseSmall {

    private Long id;

    private String name;

    private String lastName;

    private String category;

}