package com.df.service;

import com.df.pojo.Products;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author MFine
 * @version 1.0
 * @date 2020/11/18 23:43
 **/
@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
@Transactional
class ProductsServiceTest {

    @Autowired
    private ProductsService productsService;

    @Test
    void updateProductById() {
        int id = productsService.updateProductById(new Products(2, "1.jpg", 1,
                UUID.randomUUID().toString().replace("-", ""), "小米9",
                "手机", "2390", "5", "0", "", 0));
        assertThat(id).isEqualTo(1);
    }
}