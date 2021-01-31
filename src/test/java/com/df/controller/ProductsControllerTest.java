package com.df.controller;

import com.df.pojo.Products;
import com.df.pojo.RestResultTest;
import com.df.service.ProductsService;
import com.df.utils.PageRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/21 22:49
 **/
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@Rollback
class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductsService productsService;

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper objectMapper;


    @BeforeEach
    public void generateTestData() {
        objectMapper = new ObjectMapper();
        Integer count = productsService.count();
        if (count > 0) {
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
        ResultActions actions = this.mockMvc.perform(get("/api/products/searchByName")
                .param("name", "小米9")
                .param("pageNum", "1")
                .param("pageSize", "3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print()).andExpect(jsonPath("$.list[0].name", Matchers.containsString("小米9")));
    }

    @Test
    void updateStatus() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("status", 1);
        ResultActions actions = this.mockMvc.perform(put("/api/products/updateStatus/1")
                .content(objectMapper.writeValueAsString(map))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        actions.andExpect(status().isOk()).andReturn().getResponse().setCharacterEncoding("UTF-8");
        actions.andDo(print());
    }

    @Test
    void updateImages() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-TP-DeviceID", UUID.randomUUID().toString());
        Map<String, List<String>> param = new HashMap<>();
        List<String> images = new ArrayList<>();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypes);
        images.add("1.jpg");
        images.add("2.jpg");
        param.put("images", images);
        ResponseEntity<RestResultTest> responseEntity = restTemplate.exchange("/api/products/updateImages/1"
                , HttpMethod.PUT
                , new HttpEntity<>(param, headers)
                , RestResultTest.class);
        Products products = productsService.selectByPrimaryKey(1);
        Assert.assertEquals(products.getImages(), "1.jpg,2.jpg");
    }

    @Test
    void addProduct() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-TP-DeviceID", UUID.randomUUID().toString());
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypes);
        ResponseEntity<RestResultTest> responseEntity = restTemplate.postForEntity("/api/products/addProduct", new Products(
                        null, "123.jpg", 1, UUID.randomUUID().toString().replace("-", ""), "mate 40",
                        "华为手机", "5999", "0", "5", null, 0)
                , RestResultTest.class);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getData()).isEqualTo(1);
    }

    @Test
    void updateProduct() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-TP-DeviceID", UUID.randomUUID().toString());
        Map<String, Products> param = new HashMap<>();
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypes);
        Products products = new Products(2, "1.jpg", 1,
                UUID.randomUUID().toString().replace("-", ""), "小米9",
                "手机", "2390", "5", "0", "", 0);
        param.put("products", products);
        ResponseEntity<RestResultTest> responseEntity = restTemplate.exchange("/api/products/updateProduct/2", HttpMethod.PUT, new HttpEntity<>(products, headers), RestResultTest.class);
        assertThat(Objects.requireNonNull(responseEntity.getBody()).getData()).isEqualTo(1);
    }
}