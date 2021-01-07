package com.df.controller.intergration;

import com.df.pojo.RestResultList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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

    @Test
    void shouldListAllRoles() {
        ResponseEntity<RestResultList> forEntity = this.restTemplate.getForEntity("/api/role/getRoles", RestResultList.class);
        Assert.assertEquals(0, (int) Objects.requireNonNull(forEntity.getBody()).getStatus());
    }
}
