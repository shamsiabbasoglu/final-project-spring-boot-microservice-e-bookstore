package com.microservice.paymentservice.service;

import com.microservice.paymentservice.entity.Payment;
import com.microservice.paymentservice.request.PaymentRequestDto;
import com.microservice.paymentservice.response.PaymentResponseDto;

public interface IPaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);

    Payment converToEntity(PaymentRequestDto paymentRequestDto);

    PaymentResponseDto convertToResponseDto(Payment payment);
}
