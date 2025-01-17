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
//    private Month reportingMonth;

    private double salary;

    private double premiya;

    private double fobo;

    private double miratorg;

    private double smety;

    private double lenta;

    private double avans;

    private double zpPoKarte;

    private double rentCar;

    private double rentPhone;

    private double itog;

    private UserResponse responsible;

    private CommentResponse comment;
}
