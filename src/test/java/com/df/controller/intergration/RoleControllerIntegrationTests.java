package com.df.controller.intergration;

import com.df.pojo.RestResultList;
import com.df.pojo.Role;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 22:10
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegrationTests {

    @Value("hello")
    private String hello;


    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void shouldListAllRoles() {
        ResponseEntity<RestResultList> forEntity = this.restTemplate.getForEntity("/api/role/getRoles", RestResultList.class);
        Assert.assertEquals(0, (int) Objects.requireNonNull(forEntity.getBody()).getStatus());
    }


    @Test
    public void shouldCreateRoleByName() {
        System.out.println(hello);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/api/role/createRoleByName", "mfine", String.class);
        assertEquals("success", responseEntity.getBody());
    }

    @Test
    void shouldCreateRole() throws JsonProcessingException {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        Role role = new Role(null, "mfine",
                LocalDateTime.now(), "", 0, null, "admin");
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/api/role/createRole", role, String.class);
        assertEquals("success", responseEntity.getBody());
    }
}
