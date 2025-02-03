package com.example.dto.request;

import com.example.createAndUpdate.Create;
import com.example.createAndUpdate.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class UserRequestUpdate {

    private Long id;

//    @Size(min = 2, max = 20, groups = {Update.class})
//    @NotBlank(groups = Create.class)
    private String name;

//    @Size(min = 2, max = 20, groups = {Update.class})
//    @NotEmpty(groups = Create.class)
    private String lastName;

//    @Size(min = 2, max = 20, groups = {Update.class})
//    @NotEmpty(groups = Create.class)
    private String patronymic;
//
//    @Size(min = 11, max = 11, groups = {Update.class})
    private Long phone;

    private String category;

    private String city;

    private String ownerLastName;
//
//    @Size(min = 20, max = 20, groups = {Update.class})
    private BigInteger bankAccountNumber;

    private Boolean dismissed;
}