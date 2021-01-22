package com.df.controller;

import com.df.pojo.User;
import com.df.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @MockBean
    private UserService userService;

    @BeforeEach
    public void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
    }

    @Test
    public void loginTestWithCorrectlyPWD() throws Exception {

        ResultActions actions = this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User(null, "admin", "admin", "13584574374", "lxemyf@gmail.com", null, null, 0)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.data.id", Matchers.equalTo(1)));
    }

    @Test
    public void loginTestWithWrongPWD() throws Exception {

        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User()))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void loginTestWithNullParam() throws Exception {
        this.mvc.perform(post("/api/user/login")
                .content(objectMapper.writeValueAsString(new User())))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    void getAllUsers() throws Exception {
        given(userService.findAll()).willReturn(new ArrayList<>());
        this.mvc.perform(get("/api/user/getUsers").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        given(userService.updateByPrimaryKeySelective(BDDMockito.any())).willReturn(1);
        User user = new User(null, "233333", "mfine", "21321321", "213213", "213112", LocalDateTime.now(), 0);
        ResultActions actions = this.mvc.perform(put("/api/user/update/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON));
        actions.andDo(print());

    }

    @Test
    void deleteUser() throws Exception {
        given(userService.deleteByPrimaryKey(BDDMockito.anyInt())).willReturn(1);
        ResultActions actions = this.mvc.perform(delete("/api/user/delete/1").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andExpect(result -> {
            Assertions.assertTrue(result.getResponse().getContentAsString().contains("success"));
        });
    }

    @Test
    void addUser() throws Exception {
        given(userService.insertSelective(BDDMockito.any())).willReturn(1);
        User user = new User(null, "233333", "mfine", "21321321", "213213", "213112", LocalDateTime.now(), 0);
        ResultActions actions = this.mvc.perform(post("/api/user/add").content(objectMapper.writeValueAsString(user)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andExpect(result -> Assertions.assertTrue(result.getResponse().getContentAsString().contains("success")));
    }
}