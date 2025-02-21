package com.example.dto.request;

import com.example.model.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SalaryRequest {

    private UUID id;

    private UUID ownerId;

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

    private UUID commentId;

    private String comment;
}
