package com.example.dto.request;

import com.example.createAndUpdate.Create;
import com.example.createAndUpdate.Update;
import com.example.enums.CategoryUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserRequest {

    @Size(min = 2, max = 20, groups = {Create.class, Update.class})
    @NotBlank(groups = Create.class)
    private String name;

    @Size(min = 2, max = 20, groups = {Create.class, Update.class})
    @NotEmpty(groups = Create.class)
    private String lastName;

    @Size(min = 6, max = 250, groups = {Create.class, Update.class})
    @NotEmpty(groups = Create.class)
    @Email(groups = {Create.class, Update.class})
    private String email;

    @Size(min = 6, max = 25, groups = {Create.class, Update.class})
    @NotEmpty(groups = Create.class)
    private String password;

//    @Size(min = 11, max = 11, groups = {Create.class, Update.class})
//    @NotEmpty(groups = Create.class)
    private Long phone;

    private String role;

    private String city;

    private String category;

    private Long ownerId;
}
