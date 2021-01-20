package com.df.controller.intergration;

import com.df.pojo.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

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


        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        ParameterizedTypeReference<List<User>> type = new ParameterizedTypeReference<List<User>>() {
        };
        ResponseEntity<List<User>> forEntity = this.restTemplate.exchange("/api/user/getUser", HttpMethod.GET, HttpEntity.EMPTY, type);
        Assertions.assertTrue(forEntity.getStatusCode().is2xxSuccessful());
    }
}
