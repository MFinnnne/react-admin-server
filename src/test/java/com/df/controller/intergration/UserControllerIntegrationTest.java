package com.df.controller.intergration;

import com.df.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/21 0:01
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldGetAllUsers() {
        ResponseEntity<List<User>> forEntity = this.restTemplate.exchange("/api/user/getUsers", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        System.out.println(Objects.requireNonNull(forEntity.getBody()).toString());
        Assertions.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void shouldUpdateUser() {
        User user = new User(null, "admin", "admin", "13584574374", "lxemyf@gmail.com", null, LocalDateTime.now(), 0);
        ResponseEntity<String> entity = this.restTemplate.exchange("/api/user/update/1", HttpMethod.PUT, new HttpEntity<>(user,null), String.class);
        Assertions.assertTrue(Objects.requireNonNull(entity.getBody()).contains("success"));
    }
}
