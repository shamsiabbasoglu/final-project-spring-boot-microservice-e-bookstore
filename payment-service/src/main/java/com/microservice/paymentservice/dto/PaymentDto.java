package com.microservice.paymentservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    @NotBlank(message = "Ad mütləqdir!")
    @Size(max = 225)
    private String name;
    private String number;
    private String email;
    private String address;

    private Integer billValue;

    private String cardNumber;
    private String cardHolder;
    private String dateValue;
    private String cvc;




}
