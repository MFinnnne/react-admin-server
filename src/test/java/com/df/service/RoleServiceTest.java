package com.df.service;

import com.df.pojo.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 20:55
 **/
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAll() {
        int count = roleService.count();
        List<Role> roles = roleService.findAll();
        assertNotEquals(roles, null);
        assertEquals(count, roles.size());
    }


    @Test
    void addRole() {
        int mFine = roleService.addRole("MFine");
        assertEquals(mFine,1);
    }


}