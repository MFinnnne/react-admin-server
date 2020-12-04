package com.df.controller;

import com.df.pojo.Products;
import com.df.service.ProductsService;
import com.df.utils.PageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/21 22:49
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
@Rollback
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductsService productsService;

    private ObjectMapper objectMapper;


    @BeforeEach
    public void generateTestData() {
        objectMapper = new ObjectMapper();
        Integer count = productsService.countByIdGreaterThan(0);
        if (count != 0) {
            return;
        }
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米8",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米9",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米10",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米11",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米12",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米13",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米14",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米15",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米16",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米17",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米18",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米19",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米20",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米21",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
        productsService.insert(new Products(null, "", 1, UUID.randomUUID().toString().replace("-", ""), "小米22",
                "一部手机而已", "2480", "0", "5", "没啥好说的", 0));
    }

    @Test
    public void findAll() throws Exception {
        ResultActions actions = this.mockMvc.perform(post("/api/products/list").content(objectMapper.writeValueAsString(new PageRequest(1, 3)))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.list").isNotEmpty());
    }

    @Test
    void searchByDesc() throws Exception {

        ResultActions actions = this.mockMvc.perform(get("/api/products/searchByDesc/一部手机而已/1/3").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.list[0].desc", Matchers.containsString("一部手机而已")));
    }


    @Test
    void searchByName() throws Exception {
        ResultActions actions = this.mockMvc.perform(get("/api/products/searchByName/小米8/1/2").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.list[0].name", Matchers.containsString("小米8")));
    }
}