package com.microservice.paymentservice.feignCLient;

import com.microservice.paymentservice.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {

    @GetMapping("/{user-id}")
    UserDto getUserById(@PathVariable("user-id") Long userId);

}
