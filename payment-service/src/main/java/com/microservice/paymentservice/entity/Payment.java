package com.microservice.paymentservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;

    private Integer billValue;

    @Column(name = "card_number")
    private String cardNumber;
    private String cardHolder;
    @Column(name = "date_value")
    private String dateValue;
    private String cvc;
    private String status;
    private LocalDateTime paymentDate;


}
