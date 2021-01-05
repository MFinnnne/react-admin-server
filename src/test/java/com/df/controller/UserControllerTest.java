package com.df.controller;

import com.df.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    }

    @Test
    public void loginTestWithCorrectlyPWD() throws Exception {

        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User(null,"admin","admin")))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"flag\":true,\"status\":0,\"message\":\"\",\"data\":{\"id\":1,\"password\":null,\"name\":\"admin\"}}"))
                .andDo(print());
    }

    @Test
    public void loginTestWithWrongPWD() throws Exception {

        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User(null,"admin","admin")))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.password",Matchers.nullValue()))
                .andDo(print());
    }

    @Test
    public void loginTestWithNullParam() throws Exception {
        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User(null, null, null))))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

}