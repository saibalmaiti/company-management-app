package com.cts.CompanyManagementApp.model;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDtoTests {
    UserDto userDto = Mockito.mock(UserDto.class);
    @Test
    void testUserName() {
        when(userDto.getEmpName()).thenReturn("Saibal Maiti");
        assertEquals("Saibal Maiti", userDto.getEmpName());
    }
    @Test
    void testPassword() {
        when(userDto.getPassword()).thenReturn("Saibal23$");
        assertEquals("Saibal23$", userDto.getPassword());
    }
}
