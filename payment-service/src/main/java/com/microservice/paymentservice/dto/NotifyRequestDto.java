package com.microservice.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifyRequestDto {

    private String message;

    private String recipientEmail;

    private LocalDateTime sentAt;


}
