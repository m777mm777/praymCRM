package com.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalarySearchFilter {

    private LocalDate localDate;

    private String responsible;

    private String city;

    private String formaoplaty;
}