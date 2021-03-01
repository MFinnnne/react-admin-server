package com.df.controller;

import com.df.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/12 20:58
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void init(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }

    @Test
    public void loginTestWithCorrectlyPWD() throws Exception {

        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User(1,"admin","admin",null,null,null, LocalDateTime.now(),0)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag",Matchers.is(true)))
                .andDo(print());
    }

    @Test
    public void loginTestWithWrongPWD() throws Exception {

        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User(null,"admin1","admin",null,null,null, LocalDateTime.now(),0)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.flag",Matchers.is(false)))
                .andDo(print());
    }

    @Test
    public void loginTestWithNullParam() throws Exception {
        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}