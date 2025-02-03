package com.example.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Auth {

    @Size(min = 6, max = 20)
    @NotEmpty
    private Long phone;

    @Size(min = 6, max = 20)
    private String password;
}
