package com.microservice.paymentservice.service.impl;

import com.microservice.paymentservice.entity.Payment;
import com.microservice.paymentservice.repositroy.PaymentRepo;
import com.microservice.paymentservice.request.PaymentRequestDto;
import com.microservice.paymentservice.response.PaymentResponseDto;
import com.microservice.paymentservice.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService implements IPaymentService {

    private final PaymentRepo paymentRepo;
    private final ModelMapper modelMapper;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        log.info("Create payment request");
        try {
            Payment payment = converToEntity(paymentRequestDto);
            log.debug("Transformed payment to entity {}",payment);
            payment.setPaymentDate(LocalDateTime.now());
            log.debug("PaymentDate set {}",payment.getPaymentDate());
            Payment savedPayment = paymentRepo.save(payment);
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


/*
@Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
    ikinci metod, copy ile
//        Payment payment = converToEntity(paymentRequestDto);
//        BeanUtils.copyProperties(paymentRequestDto, payment);
//
//        Payment savedPayment = paymentRepo.save(payment);
//
//        return convertToResponseDto(savedPayment);
        Payment payment = new Payment();
        payment.setName(paymentRequestDto.getName());
        payment.setSurname(paymentRequestDto.getSurname());
        payment.setEmail(paymentRequestDto.getEmail());
        payment.setAddress(paymentRequestDto.getAddress());
        payment.setBillValue(paymentRequestDto.getBillValue());
        payment.setCardNumber(paymentRequestDto.getCardNumber());
        payment.setCardHolder(paymentRequestDto.getCardHolder());
        payment.setDateValue(paymentRequestDto.getDateValue());
        payment.setCvc(paymentRequestDto.getCvc());
        payment.setPaymentDate(LocalDateTime.now());
        Payment savedPayment = paymentRepo.save(payment);
        return convertToResponseDto(savedPayment);


    }
 */
