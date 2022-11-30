package com.cts.CompanyManagementApp.controller;

import com.cts.CompanyManagementApp.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpServletRequest httpServletRequest) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(httpServletRequest.getHeader("authorization").substring(7));
        headers.setContentType(MediaType.APPLICATION_JSON);
        log.info(headers.toString());
        String baseUrl = "http://localhost:8082/api/v1/user/logout";
        try {
            HttpEntity<String> request =
                    new HttpEntity<>("", headers);
            ResponseEntity<String> response = restTemplate.postForEntity(baseUrl,request, String.class);
            return response;
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Failed to sign out");
        }
    }

}
