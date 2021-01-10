package com.df.controller.intergration;

import com.df.pojo.RestResultList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 22:10
 **/
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegrationTests {

    @Value("hello")
    private String hello;

    @Value("hello2")
    private String hello2;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void init() {
        ObjectMapper objectMapper = new ObjectMapper();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldListAllRoles() {
        ResponseEntity<RestResultList> forEntity = this.restTemplate.getForEntity("/api/role/getRoles", RestResultList.class);
        Assert.assertEquals(0, (int) Objects.requireNonNull(forEntity.getBody()).getStatus());
    }


    @Test
    public  void shouldCreateRole() {
        System.out.println(hello+"--"+hello2);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/api/role/createRole", "mfine", String.class);
        assertEquals("success", responseEntity.getBody());
    }
}
