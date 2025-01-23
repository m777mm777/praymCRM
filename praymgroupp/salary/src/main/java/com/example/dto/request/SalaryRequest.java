package com.example.dto.request;

import com.example.model.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class SalaryRequest {

    private Long id;

    private Long ownerId;

    private Double salary;

    private LocalDate reportingMonth;

    private Double premiya;

    private Double fobo;

    private Double miratorg;

    private Double smety;

    private Double lenta;

    private Double avans;

    private Double zpPoKarte;

    private Double rentCar;

    private Double rentPhone;

    private String responsible;

    private Long commentId;

    private String comment;
}
