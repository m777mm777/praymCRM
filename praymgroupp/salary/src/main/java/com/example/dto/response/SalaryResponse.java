package com.example.dto.response;

import com.example.enums.Month;
import com.example.model.Comment;
import com.example.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class SalaryResponse {

    private Long id;

    private UserResponse owner;

    private LocalDate reportingMonth;

    private Double salary;

    private Double premiya;

    private Double fobo;

    private Double miratorg;

    private Double smety;

    private Double lenta;

    private Double avans;

    private Double zpPoKarte;

    private Double rentCar;

    private Double rentPhone;

    private Double itog;

    private UserResponse responsible;

    private CommentResponse comment;
}
