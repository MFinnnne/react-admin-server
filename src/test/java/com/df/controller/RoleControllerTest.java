package com.df.controller;

import com.df.pojo.Role;
import com.df.service.RoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/1/7 21:30
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Rollback
@Transactional
class RoleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RoleService roleService;


    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void shouldNotListRoles() throws Exception {

        given(roleService.findAll()).willReturn(new ArrayList<>());
        ResultActions actions = this.mvc.perform(get("/api/role/getRoles"));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.data.length()").value(Matchers.is(0)));
    }

    @Test
    void shouldListAllRoles() throws Exception {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        roles.add(new Role());
        given(roleService.findAll()).willReturn(roles);
        ResultActions actions = this.mvc.perform(get("/api/role/getRoles"));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.data.length()").value(Matchers.is(2)));
    }


    @Test
    void shouldThrowException() throws Exception {

        given(roleService.findAll()).willReturn(null);
        ResultActions actions = this.mvc.perform(get("/api/role/getRoles"));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andExpect(jsonPath("$.data.length()").value(Matchers.is(0))).andDo(print());
    }

    @Test
    void shouldCreateRole() throws Exception {
        HashMap<String, String> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name","mfine");
        given(roleService.addRole("mfine")).willReturn(1);
        ResultActions actions = this.mvc.perform(post("/api/role/createRole").content("mfine").contentType(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print());
    }
}