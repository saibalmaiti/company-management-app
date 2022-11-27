package com.cts.CompanyManagementApp.controller;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/auth/v1/user")
@Slf4j
public class ConsumingController {
    @PostMapping("/login")
    public ResponseEntity<?> loinUser(@RequestBody UserDto userDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String baseUrl = "http://localhost:8082/auth/v1/user/login";
        Map<String, String> map = null;

        try{
            map = (Map<String, String>) restTemplate.
                    postForEntity(baseUrl, userDto, Map.class).getBody();
            return ResponseEntity.ok().body(map);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Response object is null");
    }
}
