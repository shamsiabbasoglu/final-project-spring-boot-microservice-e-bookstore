package com.microservice.paymentservice.controller;

import com.microservice.paymentservice.exception.InsufficientBalanceException;
import com.microservice.paymentservice.request.PaymentRequestDto;
import com.microservice.paymentservice.response.PaymentResponseDto;
import com.microservice.paymentservice.service.impl.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.hibernate.grammars.hql.HqlParser.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/payments")
@Slf4j
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto){
        log.info("/payments/create successful called");
        log.debug("DTO problem: {}", paymentRequestDto);
        try {
            PaymentResponseDto paymentResponseDto = paymentService.createPayment(paymentRequestDto);
            log.info("Payment created successfully {}", paymentResponseDto);
            return ResponseEntity.ok(paymentResponseDto);
        }catch (InsufficientBalanceException e){
            log.warn("An insufficient funds error occurred, user id: {}", paymentRequestDto.getCvc());
            return ResponseEntity.status(CONFLICT).body(new PaymentResponseDto());
        }catch (Exception e){
            log.error("An unexpected error occurred while creating the payment: {}", e.getMessage(), e);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new PaymentResponseDto());
        }
    }
}
