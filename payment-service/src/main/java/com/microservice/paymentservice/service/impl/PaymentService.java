package com.microservice.paymentservice.service.impl;

import com.microservice.paymentservice.dto.NotifyRequestDto;
import com.microservice.paymentservice.dto.UserDto;
import com.microservice.paymentservice.entity.Payment;
import com.microservice.paymentservice.exception.InsufficientBalanceException;
import com.microservice.paymentservice.feignCLient.NotifyServiceClient;
import com.microservice.paymentservice.feignCLient.UserServiceClient;
import com.microservice.paymentservice.repositroy.PaymentRepo;
import com.microservice.paymentservice.request.PaymentRequestDto;
import com.microservice.paymentservice.response.PaymentResponseDto;
import com.microservice.paymentservice.service.IPaymentService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Getter
@Setter
@Data
@Slf4j
public class PaymentService implements IPaymentService {

    private final PaymentRepo paymentRepo;
    private final ModelMapper modelMapper;
    private final UserServiceClient userServiceClient;
    private final NotifyServiceClient notifyServiceClient;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        log.info("Create payment request");
        UserDto user = userServiceClient.getUserById(paymentRequestDto.getUserId());
        if (user == null || user.getBalance() < paymentRequestDto.getBillValue()) {
            log.error("User not found for ID: {}", paymentRequestDto.getUserId());
            throw new InsufficientBalanceException("Balansda pul yoxdur!");
        }
        try {
            Payment payment = converToEntity(paymentRequestDto);
            log.debug("Transformed payment to entity {}",payment);
            payment.setPaymentDate(LocalDateTime.now());
            log.debug("PaymentDate set {}",payment.getPaymentDate());
            Payment savedPayment = paymentRepo.save(payment);

            NotifyRequestDto notifyRequestDto = new NotifyRequestDto(
                    "Ödəniş edildi!",
                    user.getEmail(),
                    LocalDateTime.now()
            );
            notifyServiceClient.sendNotification(notifyRequestDto);

            log.info("Payment saved");
            return convertToResponseDto(savedPayment);
        }catch (Exception e){
            log.error("createPayment error {}",e.getMessage());
            throw e;
        }
    }


    @Override
    public Payment converToEntity(PaymentRequestDto paymentRequestDto) {
        log.debug("call to ConvertToEntity method {}",paymentRequestDto);
        Payment payment = modelMapper.map(paymentRequestDto, Payment.class);
        log.debug("setting Payment with ModelMapper {}",payment);
        return payment;
    }


    @Override
    public PaymentResponseDto convertToResponseDto(Payment payment) {
        log.debug("call to convertToResponseDto. Payment: {}", payment);
        PaymentResponseDto paymentResponseDto = modelMapper.map(payment, PaymentResponseDto.class);
        paymentResponseDto.setStatus("Əla!");
        paymentResponseDto.setMessage("Ödəniş edildi");
        log.debug("convert To ResponseDto : {}", paymentResponseDto);
        return paymentResponseDto;
    }

}

