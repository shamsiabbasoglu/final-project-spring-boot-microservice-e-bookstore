package com.microservice.paymentservice.feignCLient;


import com.microservice.paymentservice.dto.NotifyRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "${notification.service.url}")
public interface NotifyServiceClient {
    @PostMapping("/sendNotify")
    void sendNotification(@RequestBody NotifyRequestDto notifyRequestDto);

}
