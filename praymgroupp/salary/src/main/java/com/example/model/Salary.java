package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "salary")
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @Column(name = "reporting_month", nullable = false)
    private LocalDate reportingMonth;

    @Column
    private Double salary;

    @Column
    private Double premiya;

    @Column
    private Double fobo;

    @Column
    private Double miratorg;

    @Column
    private Double smety;

    @Column
    private Double lenta;

    @Column
    private Double avans;

    @Column(name = "zp_po_karte")
    private Double zpPoKarte;

    @Column(name = "rent_car")
    private Double rentCar;

    @Column(name = "rent_phone")
    private Double rentPhone;

    @OneToOne()
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;
}
