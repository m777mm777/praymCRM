package com.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class UserResponse {

    private UUID id;

    private String name;

    private String lastName;

    private String patronymic;

    private Long phone;

    private String city;

    private String category;

    private String ownerLastName;

    private String bankAccountNumber;

    private Boolean dismissed;

    private String companyName;
}
