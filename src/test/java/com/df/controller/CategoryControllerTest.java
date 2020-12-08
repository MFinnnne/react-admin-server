package com.df.controller;

import com.df.service.CategoryService;
import com.df.utils.PageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/20 21:39
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
class CategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void init() throws Exception {
      objectMapper = new ObjectMapper();
    }


    @Test
    void findPage() throws Exception {
        ResultActions actions = this.mvc.perform(post("/api/category/findPage")
                .content(objectMapper.writeValueAsString(new PageRequest(1, 5)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        actions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print());
    }

    @Test
    void findCategoryById() throws Exception {
        this.mvc.perform(get("/api/category/findCategoryById/1")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}