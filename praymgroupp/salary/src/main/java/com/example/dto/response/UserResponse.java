package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

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

    private String bankAccountNumber;

    private Boolean dismissed;
}
