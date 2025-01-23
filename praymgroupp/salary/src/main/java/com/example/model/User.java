package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long phone;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String category;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @Column(nullable = false)
    private Boolean dismissed;

    @Column(name = "bank_account_number")
    private Integer bankAccountNumber;

}

