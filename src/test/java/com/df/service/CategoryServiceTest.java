package com.df.service;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/20 21:34
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CategoryServiceTest {

}