package com.example.springboot_backend.client;

import com.example.springboot_backend.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="address-service", url="http://localhost:8081")
public interface AddressFeignClient {

    @GetMapping("/api/address/{employeeId}")
    Address getAddress(@PathVariable("employeeId") Long employeeId);

}
