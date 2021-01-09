package com.df.controller.intergration;

import com.df.pojo.RestResultList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 22:10
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
@ActiveProfiles("test")
public class RoleControllerIntegrationTests {


    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldListAllRoles() {
        ResponseEntity<RestResultList> forEntity = this.restTemplate.getForEntity("/api/role/getRoles", RestResultList.class);
        Assert.assertEquals(0, (int) Objects.requireNonNull(forEntity.getBody()).getStatus());
    }


    @Test
    void shouldCreateRole() {
        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity("/api/role/createRole", "mfine", String.class);
        assertEquals("success", responseEntity.getBody());
    }
}
