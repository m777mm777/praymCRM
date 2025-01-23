package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String name;

    private String lastName;

    private String patronymic;

    private Long phone;

    private String city;

    private String category;

    private String ownerLastName;

    private Integer bankAccountNumber;

    private Boolean dismissed;
}
