package com.df.controller.intergration;

import com.df.pojo.RestResultList;
import com.df.pojo.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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
        ResponseEntity<RestResultList> forEntity = this.restTemplate.exchange("/api/role/getRoles", HttpMethod.GET, null, new ParameterizedTypeReference<RestResultList>() {
        });
        Assert.assertEquals(0, (int) Objects.requireNonNull(forEntity.getBody()).getStatus());
    }


    @Test
    public void shouldCreateRoleByName() {
        System.out.println(hello);
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/api/role/createRoleByName", "mfine", String.class);
        assertEquals("success", responseEntity.getBody());
    }

    @Test
    void shouldCreateRole() {

        Role role = new Role(null,"mfine",
                LocalDateTime.now(), "", 0, null, "admin");
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/api/role/createRole", role, String.class);
        assertEquals("success", responseEntity.getBody());
    }

    @Test
    void shouldUpdate(){

        Role role = new Role(null,"mfine",
                LocalDateTime.now(), "/home,/products", 0, LocalDateTime.now(), "admin");
        ResponseEntity<String> entity= this.restTemplate.exchange("/api/role/updateRole/1", HttpMethod.PUT, new HttpEntity<>(role, null), String.class);
        Assertions.assertEquals("success", entity.getBody());
    }

    @Test
    void shouldGetRoleById(){
        ResponseEntity<Role> entity = this.restTemplate.getForEntity("/api/role/get/1", Role.class);
        Assertions.assertNotNull(entity.getBody());
    }

}
